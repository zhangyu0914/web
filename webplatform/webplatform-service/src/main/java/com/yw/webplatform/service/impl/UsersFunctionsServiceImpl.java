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
import com.yw.common.beansUtils.dto.FunctionDto;
import com.yw.common.beansUtils.dto.UsersFunctionsDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IUsersFunctionsService;
import com.yw.webplatform.api.IUsersRolesService;


/**
 *<pre>
 * 功       能: 权限表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@Service("usersFunctionsService")
public class UsersFunctionsServiceImpl extends BaseMapperImpl  implements IUsersFunctionsService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IUsersRolesService usersRolesService;//
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午3:11:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(UsersFunctionsEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if(!StringUtils.isBlank(entity) && !StringUtils.isBlank(entity.getType())){
			
			if(entity.getType().intValue() == 0){
				entity.setFunctionCodeTemp("-1");//这样就可以显示根节点
				entity.setFunctionType(0);//只查询父级菜单
			}else if(entity.getType().intValue() == 1){
				entity.setFunctionType(3);//只查询子级菜单
			}
		}
		List<UsersFunctionsEntity> list = this.usersFunctionsEntityMapper.findAll(entity);
		if (StringUtils.isBlank(list)
				|| list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		List<UsersFunctionsDto> dtoList = new ArrayList<UsersFunctionsDto>();
		for (UsersFunctionsEntity data : list) {
			dtoList.add(new UsersFunctionsDto(data));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS).setData(dtoList);
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
	public ResultUtil insertFunction(UsersFunctionsEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getFunctionName(),
						entity.getDisplayOrder(),
						entity.getUri(),
						entity.getFunctionLevel(),
						entity.getFunctionType())) {			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("functionName,displayOrder,functionLevel,functionType");
		}
		if (!StringUtils.isBlank(this.findOne(new UsersFunctionsEntity(null,null, entity.getUri())))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_FUNCTION_EXISTS);//权限已存在，请勿重复添加
		}
		UsersFunctionsEntity newEntity=new UsersFunctionsEntity();
		if(StringUtils.isBlank(entity.getParentCode())){
			
			newEntity.setFunctionCode("0");
			entity.setParentEntity(newEntity);
		}else{
			newEntity.setFunctionCode(entity.getParentCode());
			entity.setParentEntity(newEntity);
		}
		int functionCode = Integer.valueOf(this.usersFunctionsEntityMapper.getMaxCode(null));
		entity.setFunctionCode(functionCode+"");
		entity.setTid(UUIDUtil.getUUID());
		if (this.usersFunctionsEntityMapper.insert(entity)> 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
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
	public ResultUtil deleteFunctions(UsersFunctionsEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getTid())) {	
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		boolean result = false;
		for (String tid : entity.getTid().split(",")) {
			
			if (StringUtils.isBlank(this.findById(tid))) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
			this.usersRolesFunctionsEntityMapper.deleteFunctions(tid);//解除与角色的关系
			
			UsersFunctionsEntity functions = this.findById(tid);
			if (StringUtils.isBlank(functions)) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
			UsersFunctionsEntity parentFunctions = new UsersFunctionsEntity();
			parentFunctions.setParentFunctionCode(functions.getFunctionCode());
			List<UsersFunctionsEntity> parentList = this.usersFunctionsEntityMapper.findAll(parentFunctions);
			
			if (!StringUtils.isBlankOr(parentList)
					&& !parentList.isEmpty()) {//是否有子级，如果有，解决与子级关系
				for(UsersFunctionsEntity fe : parentList){
					
					UsersFunctionsEntity updateFunctions = new UsersFunctionsEntity();
					updateFunctions.setTid(fe.getTid());
					updateFunctions.setParentFunctionCode("0");//关联到根节点
					this.usersFunctionsEntityMapper.updateParentCodeById(updateFunctions);
				}
			}
			if (this.usersFunctionsEntityMapper.delete(tid) < 1) {
				result = false;
				throw new Exception("删除失败");
			}else{
				result = true;
			}
			if (result) {
				return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
			}
		}
		return  resultUtil;
	}
	
	/**
	 *<pre>
	 * 说      明:  修改权限数据
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
	public ResultUtil updateFunctions(UsersFunctionsEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getTid())) {	
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		UsersFunctionsEntity ufe = this.findById(entity.getTid());
		if (StringUtils.isBlank(ufe)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);//没找到相符的数据
		}
		UsersFunctionsEntity newEntity=new UsersFunctionsEntity();
		if(!StringUtils.isBlank(entity.getParentCode()) 
				&& !ufe.getParentCode().equals(entity.getParentCode())){
			
			newEntity.setFunctionCode(entity.getParentCode());
			entity.setParentEntity(newEntity);
		}
		entity.setUri(entity.getUri());
		if (this.usersFunctionsEntityMapper.updateById(entity) == 0) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	

	/**
	 *<pre>
	 * 说   明: 
	 * @param entity
	 * @return
	 * @throws Exception
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22下午6:00:00
	 *</pre>
	 */
	@Override
	public Map<String, List<FunctionDto>> findByUserId(UserInfoEntity entity) throws Exception {
		List<UsersFunctionsEntity> feList =  this.usersFunctionsEntityMapper.findByUserId(entity);
		if (!StringUtils.isBlankOr(feList)
				&& !feList.isEmpty()) {
			
			Map<String, UsersFunctionsEntity> dataMap = new HashMap<String, UsersFunctionsEntity>();
			for (UsersFunctionsEntity function : feList) {
				this.findFunction(function, dataMap);
			}
			feList = new ArrayList<UsersFunctionsEntity>(dataMap.values());
		}
		Map<String, List<FunctionDto>> map = new HashMap<String, List<FunctionDto>>();
		for (UsersFunctionsEntity function : feList) {
			
			
			if (!(function.getParentCode().equals("0") && function.getFunctionLevel() != 1)) {//不能为根目录，且包含只有1级的菜单
				
				if (map.containsKey(function.getParentEntity().getUri())) {
					
					List<FunctionDto> dataList = map.get(function.getParentEntity().getUri());
					dataList.add(new FunctionDto(function));
					if (function.getFunctionLevel() == 1) {
						map.put(function.getUri(), dataList);//使用子级
					}else{
						map.put(function.getParentEntity().getUri(), dataList);
					}
				}else{
					List<FunctionDto> dataList = new ArrayList<FunctionDto>();
					dataList.add(new FunctionDto(function));
					if (function.getFunctionLevel() == 1) {
						map.put(function.getUri(), dataList);//使用子级
					}else{
						map.put(function.getParentEntity().getUri(), dataList);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param fe
	 * @param dataMap
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-6下午2:09:12
	 *</pre>
	 */
	public void findFunction(UsersFunctionsEntity fe, Map<String, UsersFunctionsEntity> dataMap)
			throws Exception {
		
		if (!dataMap.containsKey(fe.getFunctionCode()) && !fe.getFunctionCode().equals("0")) {
			
			dataMap.put(fe.getFunctionCode(), fe);//不存在，则添加进去
			UsersFunctionsEntity parentFunction = new UsersFunctionsEntity();
			parentFunction.setFunctionCode(fe.getParentEntity().getFunctionCode());
			UsersFunctionsEntity parentEntity = this.findOne(parentFunction);
			if (!StringUtils.isBlankOr(parentEntity)
					&& !StringUtils.isBlankOr(parentEntity.getFunctionCode())
					&& !parentEntity.getParentCode().equals("0")) {
				this.findFunction(parentEntity, dataMap);
			}
		}
	}
	
	
	/**
	 *<pre>
	 * 说   明: 
	 * @param entity
	 * @return
	 * @throws Exception
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22下午2:19:53
	 *</pre>
	 */
	@Override
	public ResultUtil findParent(UsersFunctionsEntity entity,String rolesId) throws Exception{
		
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity) || StringUtils.isBlank(entity.getFunctionCode())) {
			if (StringUtils.isBlank(rolesId)) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("functionCode");
			}
		}else if (StringUtils.isBlank(rolesId)) {
			if (StringUtils.isBlank(entity) || StringUtils.isBlank(entity.getFunctionCode())) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("rolesId");
			}
		}
		List<UsersFunctionsEntity> list = new ArrayList<UsersFunctionsEntity>();
		if(StringUtils.isBlank(rolesId)){
			
			list =this.usersFunctionsEntityMapper.findAll(new UsersFunctionsEntity(0, entity.getFunctionCode()));//只查询父级菜单
			if(!StringUtils.isBlankOr(entity, entity.getFunctionCode())){
				UsersFunctionsEntity function=this.findOne(entity);
				Map<String, UsersFunctionsEntity> data = new HashMap<String, UsersFunctionsEntity>();
				data.put(function.getParentEntity().getFunctionCode(), function);
				for(UsersFunctionsEntity fe:list){
					if (data.containsKey(fe.getFunctionCode())) {
						fe.setChecked(true);	//默认选中
					}
				}			
			}
		}else{
			
			list =this.usersFunctionsEntityMapper.findAll(new UsersFunctionsEntity(3, null));//只查询子级菜单
			UsersRolesEntity re=this.usersRolesService.findOne(new UsersRolesEntity(rolesId));
			if(re!=null){
				List<UsersFunctionsEntity> feList=re.getFunctionsList();
				if (!StringUtils.isBlankOr(feList)) {
					Map<String, UsersFunctionsEntity> data = new HashMap<String, UsersFunctionsEntity>();
					for (UsersFunctionsEntity fe : feList) {
						data.put(fe.getTid(), fe);
					}
					for (UsersFunctionsEntity fe : list) {
						if (data.containsKey(fe.getTid())) {
							fe.setChecked(true);	//默认选中
						}
					}
				}
			}
		}
		
		Collections.sort(list, new Comparator<UsersFunctionsEntity>() {
			@Override
			public int compare(UsersFunctionsEntity o1, UsersFunctionsEntity o2) {
				if (!o1.getChecked()) {
					return 1;
				}
				return 0;
			}
		});
		List<UsersFunctionsDto> dataList = new ArrayList();
		for (UsersFunctionsEntity usersFunctionsEntity : list) {
			dataList.add(new UsersFunctionsDto(usersFunctionsEntity));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
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
	public Integer insert(UsersFunctionsEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.usersFunctionsEntityMapper.insert(entity);
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
	public Integer update(UsersFunctionsEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.usersFunctionsEntityMapper.updateById(entity);
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
	public ResultUtil findPage(UsersFunctionsEntity entity, InterfacePage<UsersFunctionsEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.usersFunctionsEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.usersFunctionsEntityMapper.findAll(entity));
		List<UsersFunctionsDto> listDto = new ArrayList<UsersFunctionsDto>();
		UsersFunctionsDto dto = null;
		for (UsersFunctionsEntity ce : page.getList()) {
			
			dto = new UsersFunctionsDto(ce);
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
	public UsersFunctionsEntity findOne(UsersFunctionsEntity entity)
			throws Exception {
		
		List<UsersFunctionsEntity> list = this.usersFunctionsEntityMapper.findAll(entity);
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
	public UsersFunctionsEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<UsersFunctionsEntity> list = this.usersFunctionsEntityMapper.findAll(
				new UsersFunctionsEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
