package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.UsersRolesDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.entity.UsersRolesFunctionsEntity;
import com.yw.common.beansUtils.entity.UsersRolesUsersEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IUserInfoService;
import com.yw.webplatform.api.IUsersRolesService;


/**
 *<pre>
 * 功       能: 角色表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@Service("usersRolesService")
public class UsersRolesServiceImpl extends BaseMapperImpl  implements IUsersRolesService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IUserInfoService userInfoService;//

	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil insertRoles(UsersRolesEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getRoleName(), 
						entity.getFunctionsIds())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("roleName,functionsIds");
		}
		if (!StringUtils.isBlank(this.findOne(new UsersRolesEntity(entity.getRoleName(), null)))) {
			//此角色已经存在，请误重复添加
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_FUNCTION_ROLES_EXISTS);
		}
		boolean result = true;
		entity.setTid(UUIDUtil.getUUID());
		entity.setRoleCode(Integer.valueOf(this.usersRolesEntityMapper.getMaxCode()) + "");
		entity.setRoleName(entity.getRoleName());
		entity.setRoleDescribe(entity.getRoleDescribe());
		result=this.usersRolesEntityMapper.insert(entity) > 0 ? true : false;
		if(result){
			
			for (String functionId:entity.getFunctionsIds().split(",")) {
				
				UsersRolesFunctionsEntity rf=new UsersRolesFunctionsEntity();
				rf.setTid(UUIDUtil.getUUID());
				rf.setTRolesTid(entity.getTid());
				rf.setTFunctionsTid(functionId);
				if (this.usersRolesFunctionsEntityMapper.insert(rf)<1) {
					result = false;
					throw new Exception("角色添加失败");
				}
			}
			if (result) {
				return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午4:48:44
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(UsersRolesEntity entity, String usersId) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		List<UsersRolesEntity> list=this.usersRolesEntityMapper.findAll(entity);
		if(!StringUtils.isBlank(usersId)){
			UserInfoEntity ue=new UserInfoEntity();
			ue.setTid(usersId);
			List<UserInfoEntity> listUsers =this.userInfoEntityMapper.findAll(ue);
			if(listUsers != null){
				
				List<UsersRolesEntity> rolesList=listUsers.get(0).getRolesList();
				if (!StringUtils.isBlankOr(rolesList)) {
					Map<String, UsersRolesEntity> data = new HashMap<String, UsersRolesEntity>();
					for (UsersRolesEntity re : rolesList) {
						data.put(re.getTid(), re);
					}
					for (UsersRolesEntity re : list) {
						if (data.containsKey(re.getTid())) {
							re.setChecked(true);	//默认选中
						}
					}
				}
			}
		}
		Collections.sort(list, new Comparator<UsersRolesEntity>() {
			@Override
			public int compare(UsersRolesEntity o1, UsersRolesEntity o2) {
				if (!o1.getChecked()) {
					return 1;
				}
				return 0;
			}
		});
		List<UsersRolesDto> dataList = new ArrayList();
		for (UsersRolesEntity data : list) {
			dataList.add(new UsersRolesDto(data));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
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
	public ResultUtil deleteRoles(UsersRolesEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		String[] tidArray = entity.getTid().split(",");
		List<UsersRolesFunctionsEntity> roles = null;
		boolean result = true;
		for (String tid : tidArray) {
			
			if (StringUtils.isBlank(this.findById(tid))) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
			this.usersRolesFunctionsEntityMapper.delete(tid);//角色与权限解绑
			UsersRolesUsersEntity rue = new UsersRolesUsersEntity();
			rue.setTRolesTid(tid);
			this.usersRolesUsersEntityMapper.deleteByFK(rue);//角色与用户解绑
			if(this.usersRolesEntityMapper.delete(tid) == 0){//删除角色
				result = false;
				throw new Exception("删除失败");
			}
			if (result) {
				return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
			}
		}
		return resultUtil;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午5:36:33
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil updateRoles(UsersRolesEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		if (StringUtils.isBlank(this.findById(entity.getTid()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);//没找到相符的数据
		}
		if (this.usersRolesEntityMapper.updateById(entity) == 0) {//更新客户信息
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
		}
		//更新角色信息:先删除之前的信息，再重新添加
		boolean deleteFlag=this.usersRolesFunctionsEntityMapper.delete(entity.getTid()) >= 0 ? true : false;
		if(deleteFlag && !StringUtils.isBlank(entity.getFunctionsIds())){
			
			if (!StringUtils.isBlankOr(entity.getFunctionsIds())) {//没权限时,删除此角色下所有权限
				
				for (String tid : entity.getFunctionsIds().split(",")) {
					
					UsersRolesFunctionsEntity rolesFunctions = new UsersRolesFunctionsEntity();
					
					rolesFunctions.setTid(UUIDUtil.getUUID());
					rolesFunctions.setTFunctionsTid(tid);
					rolesFunctions.setTRolesTid(entity.getTid());
					if (this.usersRolesFunctionsEntityMapper.insert(rolesFunctions)<1) {
						throw new Exception("修改角色信息失败");
					}
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
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(UsersRolesEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.usersRolesEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(UsersRolesEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.usersRolesEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(UsersRolesEntity entity, InterfacePage<UsersRolesEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.usersRolesEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.usersRolesEntityMapper.findAll(entity));
		List<UsersRolesDto> listDto = new ArrayList<UsersRolesDto>();
		UsersRolesDto dto = null;
		for (UsersRolesEntity ce : page.getList()) {
			
			dto = new UsersRolesDto(ce);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Override
	public UsersRolesEntity findOne(UsersRolesEntity entity)
			throws Exception {
		
		List<UsersRolesEntity> list = this.usersRolesEntityMapper.findAll(entity);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Override
	public UsersRolesEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<UsersRolesEntity> list = this.usersRolesEntityMapper.findAll(
				new UsersRolesEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
