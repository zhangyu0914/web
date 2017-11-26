package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.UserInfoDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UserLogEntity;
import com.yw.common.beansUtils.entity.UsersRolesUsersEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.SignUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.UserInfoUserStateEnum;
import com.yw.common.beansUtils.utils.enums.UserLogVisitTypeEnum;
import com.yw.common.beansUtils.utils.enums.UsersStateEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IUserInfoService;
import com.yw.webplatform.api.IUserLogService;
import com.yw.webplatform.api.IUsersFunctionsService;


/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:18
 * Q    Q: 308053847
 *</pre>
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseMapperImpl  implements IUserInfoService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IUserLogService userLogService;//
	@Autowired
	private IUsersFunctionsService usersFunctionsService;//
	
	private static ExecutorService es = null;

	/**
	 * <pre>
	 * 说       明: 设置线程池
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月8日下午5:21:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static ExecutorService getExecutorInstance() {
		if (es == null) {
			es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
		}
		return es;
	}
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月8日下午4:57:06
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil login(final UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getUserAccount(), 
				entity.getUserPwd())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"userAccount,userPwd");// 缺少参数
		}
		
		UserInfoEntity loginUser = new UserInfoEntity();
		loginUser.setUserAccount(entity.getUserAccount());
		loginUser = this.findOne(loginUser);
		if (StringUtils.isBlank(loginUser)) {// 判断密码是否正确

			log.error("此用户不存在");
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_USERS_NOT_FOUND); // 密码输入错误
		}
		if (loginUser.getUserState().equals(UserInfoUserStateEnum.DISABLED.getCode())) {// 判断用户状态

			log.error("此用户已被禁用");
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_USERS_USER_STATE_DISBLAED); // 此用户已被禁用
		}
		
		String[] saltArray = StringUtils.AddSalt(entity.getUserAccount(), entity.getUserPwd(), loginUser.getUserSalt());
		String saltPwd = saltArray[0];

		if (StringUtils.isBlankOr(loginUser.getUserPwd()) || !loginUser.getUserPwd().equals(saltPwd)) {// 判断密码是否正确

			log.error("请输入正确的密码！！！");
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_USERS_LOGIN_ERROR); // 密码输入错误
		}
		
		List<UserLogEntity> userLogList = this.userLogEntityMapper.findAll(new UserLogEntity(null, UserLogVisitTypeEnum.THE_LOGIN.getCode()));
		if (!StringUtils.isBlank(userLogList)
				&& !userLogList.isEmpty()) {
			for (UserLogEntity userLogEntity : userLogList) {
				
				userLogEntity.setVisitType(UserLogVisitTypeEnum.QUIT.getCode());
				this.userLogEntityMapper.updateById(userLogEntity);
			}
		}
		
		final String token = UUIDUtil.getUUID();
		loginUser.setToken(token);
		UserInfoDto uid = new UserInfoDto(loginUser);
		uid.setMyFunction(this.usersFunctionsService.findByUserId(loginUser));
		entity.setTid(loginUser.getTid());
		
		getExecutorInstance().execute(new Runnable() {
			@Override
			public void run() {
				try {
					UserLogEntity ule = new UserLogEntity();
					ule.setLastLoginToken(token);
					ule.setFkUserTid(entity.getTid());
					ule.setLastLoginTime(DateUtils.getTimestamp());
					ule.setVisitType(UserLogVisitTypeEnum.THE_LOGIN.getCode());
					ule.setTokenTimeout(DateUtils.getDateAfterHour(null, 
								Integer.valueOf(ConfigurationEnum.REDIS_TIMEOUT.getValue()) / 60 / 60));
					
					userLogService.insert(ule);
				} catch (Exception e) {
					log.error("登录信息失败！！！", e);
				}
			}
		});
		return resultUtil.setData(uid).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 退出
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午1:44:44
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil loginOut(final UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(entity.getToken())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"token");// 缺少参数
		}
		UserLogEntity ule = this.userLogService.findOne(new UserLogEntity(null, null, entity.getToken()));
		if (StringUtils.isBlank(ule)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		ule.setVisitType(UserLogVisitTypeEnum.QUIT.getCode());
		this.userLogEntityMapper.updateById(ule);
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil insertUserInfo(UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getUserAccount(),
						entity.getUserPwd(),
						entity.getDisplayName(),
						entity.getRolesIds())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("userAccount,userPwd,displayName,rolesIds");
		}
		if (!StringUtils.isBlank(this.findOne(new UserInfoEntity(null, entity.getUserAccount())))) {
			//用户名已存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_USERS_REGIST_USER_EXISTS);
		}
		// 保存用户主记录
		String[] saltArray = SignUtil.AddSalt(entity.getUserAccount(),entity.getUserPwd(), null);
		entity.setTid(UUIDUtil.getUUID());
		entity.setUserPwd(saltArray[0]);
		entity.setUserSalt(saltArray[1]);
		entity.setUserState(UsersStateEnum.IS_USE.getCode());
		if (this.userInfoEntityMapper.insert(entity) < 0) {

			log.info("添加用户主记录失败");
			return resultUtil;
		}
		// 关联角色
		for (String rolesId : entity.getRolesIds().split(",")) {
			UsersRolesUsersEntity ru=new UsersRolesUsersEntity();
			
			ru.setTid(UUIDUtil.getUUID());
			ru.setTRolesTid(rolesId);
			ru.setTUsersTid(entity.getTid());
			if(this.usersRolesUsersEntityMapper.insert(ru)<1){
				throw new Exception("添加用户角色失败");
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  删除数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil deleteUserInfo(UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		String[] tidArray = entity.getTid().split(",");
		List<UsersRolesUsersEntity> rolesUsers = null;
		for (String tid : tidArray) {
			
			if (StringUtils.isBlank(this.findById(tid))) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
			this.usersRolesUsersEntityMapper.delete(tid);
			this.userInfoEntityMapper.delete(tid);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 修改用户信息
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午5:27:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil updateUserInfo(UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getTid())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		if (StringUtils.isBlank(this.findById(entity.getTid()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);//没找到相符的数据
		}
		if (!StringUtils.isBlankOr(entity.getUserPwdNew())) {
			
			String[] saltArray = SignUtil.AddSalt(entity.getUserAccount(), entity.getUserPwdNew(), null);
			entity.setUserPwd(saltArray[0]);
			entity.setUserSalt(saltArray[1]);
		}
		if (this.userInfoEntityMapper.updateById(entity) == 0) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
		}
		if (!StringUtils.isBlank(entity.getRolesIds())
				&& entity.getRolesIds().split(",").length > 0) {
			
			this.usersRolesUsersEntityMapper.delete(entity.getTid());//解绑之前的角色
			for (String rid : entity.getRolesIds().split(",")) {
				
				if (this.usersRolesUsersEntityMapper.insert(
						new UsersRolesUsersEntity(UUIDUtil.getUUID(),entity.getTid(),rid)) == 0) {
					break;
				}
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(UserInfoEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.userInfoEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(UserInfoEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.userInfoEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Override
	public ResultUtil<List<UserInfoDto>> findPage(UserInfoEntity entity, InterfacePage<UserInfoEntity> page) throws Exception {
		ResultUtil<List<UserInfoDto>> resultUtil = new ResultUtil<List<UserInfoDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.userInfoEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.userInfoEntityMapper.findAll(entity));
		List<UserInfoDto> listDto = new ArrayList<UserInfoDto>();
		UserInfoDto dto = null;
		for (UserInfoEntity ce : page.getList()) {
			
			dto = new UserInfoDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Override
	public UserInfoEntity findOne(UserInfoEntity entity)
			throws Exception {
		
		List<UserInfoEntity> list = this.userInfoEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Override
	public UserInfoEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<UserInfoEntity> list = this.userInfoEntityMapper.findAll(
				new UserInfoEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 校验用户是否存在
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月1日 下午4:38:34
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil userCheck(UserInfoEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getUserAccount())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("userAccount");
		}
		
		UserInfoEntity user=new UserInfoEntity();
		user.setUserAccount(entity.getUserAccount());
		if(StringUtils.isBlank(findOne(user))){
			//此用户不存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_USERS_NOT_FOUND);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
}
