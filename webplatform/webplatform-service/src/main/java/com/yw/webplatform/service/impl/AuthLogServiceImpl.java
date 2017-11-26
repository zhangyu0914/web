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
import com.yw.common.beansUtils.dto.AuthLogDto;
import com.yw.common.beansUtils.dto.platformapp.AuthDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.AuthLogAuthStatusEnum;
import com.yw.common.beansUtils.utils.enums.AuthLogOnlineStatusEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAuthLogService;


/**
 *<pre>
 * 功       能: 认证记录
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@Service("authLogService")
public class AuthLogServiceImpl extends BaseMapperImpl  implements IAuthLogService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IAppAccountService appAccountService;//
	
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
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AppResultUtil auth(final PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getApp_instance(), 
				entity.getAppkey())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,appkey");// 缺少参数
		}
		
		AppAccountEntity loginUser = new AppAccountEntity();
		loginUser.setAppInstance(entity.getApp_instance());
		loginUser = this.appAccountService.findOne(loginUser);
		if (StringUtils.isBlank(loginUser)) {

			log.error("此应用实例不存在");
			final AuthLogEntity log = new AuthLogEntity();
			log.setTid(UUIDUtil.getUUID());
			log.setAppInstance(entity.getApp_instance());
			log.setAppKey(entity.getAppkey());
			log.setAuthStatus(AuthLogAuthStatusEnum.FAILURE.getCode());
			log.setAuthMsg("此应用实例不存在");
			getExecutorInstance().execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						authLogEntityMapper.insert(log);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_INSTANCE_IS_NO_EXISTS);//应用实例不存在
		}
		
		if (!loginUser.getAppKey().equals(entity.getAppkey())) {// 判断密码是否正确

			log.error("应用账号或密码错误");
			final AuthLogEntity log = new AuthLogEntity();
			log.setTid(UUIDUtil.getUUID());
			log.setAppId(loginUser.getAppId());
			log.setAppInstance(entity.getApp_instance());
			log.setAppKey(entity.getAppkey());
			log.setAuthStatus(AuthLogAuthStatusEnum.FAILURE.getCode());
			log.setAuthMsg("应用账号或密码错误");
			getExecutorInstance().execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						authLogEntityMapper.insert(log);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_AC_FAILURE);//应用账号或密码错误
		}
		
		List<AuthLogEntity> authLogList = this.authLogEntityMapper.findAll(new AuthLogEntity(loginUser.getAppId(),
				entity.getApp_instance(), AuthLogOnlineStatusEnum.ONLINE.getCode()));
		if (!StringUtils.isBlank(authLogList)
				&& !authLogList.isEmpty()) {//其他的踢下线
			for (AuthLogEntity authLogEntity : authLogList) {
				
				authLogEntity.setOnlineStatus(AuthLogOnlineStatusEnum.OFFLINE.getCode());
				this.authLogEntityMapper.updateById(authLogEntity);
			}
		}
		
		AuthDto authLog = new AuthDto();
		authLog.setApp_instance(entity.getApp_instance());
		authLog.setApp_name(loginUser.getAppName());
		authLog.setOrg_name(loginUser.getOrgName());
		authLog.setAuth_status(AuthLogAuthStatusEnum.SUCCESS.getCode());
		authLog.setAuth_msg(AuthLogAuthStatusEnum.SUCCESS.getName());
		authLog.setOnline_status(AuthLogOnlineStatusEnum.ONLINE.getCode());
		authLog.setToken(UUIDUtil.getUUID());
		authLog.setToken_timeout(DateUtils.getDateAfterHour(null, Integer.valueOf(ConfigurationEnum.AUTH_TOKEN_HOUR.getValue())));
		if (this.insert(new AuthLogEntity(loginUser.getAppId(), authLog)) > 0) {
			return resultUtil.setData(authLog).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月26日下午4:00:47
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil updateOnlineForTimeOUt(AuthLogEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		Integer result = this.authLogEntityMapper.updateOnlineForTimeOUt(entity);
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(AuthLogEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.authLogEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(AuthLogEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.authLogEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(AuthLogEntity entity, InterfacePage<AuthLogEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.authLogEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.authLogEntityMapper.findAll(entity));
		List<AuthLogDto> listDto = new ArrayList<AuthLogDto>();
		AuthLogDto dto = null;
		for (AuthLogEntity ce : page.getList()) {
			
			dto = new AuthLogDto(ce);
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public AuthLogEntity findOne(AuthLogEntity entity)
			throws Exception {
		
		List<AuthLogEntity> list = this.authLogEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 检查TOKEN有效性
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public AuthLogEntity findVaildToken(AuthLogEntity entity)throws Exception {
		
		List<AuthLogEntity> list = this.authLogEntityMapper.findVaildToken(entity);
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public AuthLogEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AuthLogEntity> list = this.authLogEntityMapper.findAll(
				new AuthLogEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 根据TOKEN获取登录记录
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月10日下午5:27:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AuthLogEntity findAuthLogByToken(String token) throws Exception {
		
		if (StringUtils.isBlank(token)) {
			return null;
		}
		return this.findOne(new AuthLogEntity(null, null, token));
	}
}
