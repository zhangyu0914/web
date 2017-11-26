package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppAccountDto;
import com.yw.common.beansUtils.dto.AppSecretDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppSecretEntity;
import com.yw.common.beansUtils.entity.importFile.PushAppDataEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppAccountStatusEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAppService;


/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@Service("appAccountService")
public class AppAccountServiceImpl extends BaseMapperImpl  implements IAppAccountService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IAppService appService;//
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
	public ResultUtil insertAppAccount(AppAccountEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getFkOrganizationTid(),
						entity.getFkAppLicenseTid(),
						entity.getAppId(),
						entity.getAppVersion(),
						entity.getAppInstance(),
						entity.getAppKey())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("fkOrganizationTid,fkAppLicenseTid,"
					+ "appId,appVersion,appInstance,appKey");
		}
		if (!StringUtils.isBlank(this.findOne(new AppAccountEntity(entity.getFkAppLicenseTid(), entity.getAppId())))) {
			//应用ID已经绑定了应用账号，请误重复创建
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_APPID_EXISTS);
		}
		if (!StringUtils.isBlank(this.findOne(new AppAccountEntity(null, entity.getAppInstance(), null)))) {
			//应用账号已存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_APP_ACCOUNT_EXISTS);
		}
		if (entity.getAppKey().length() != Integer.valueOf(ConfigurationEnum.APP_KEY_MAX_COUNT.getValue())) {
			//密钥长度不合法
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_LENGTH_ILLEGAL);
		}
		entity.setAccountstatus(AppAccountStatusEnum.ENABLED.getCode());
		entity.setTid(UUID.randomUUID().toString());
		if (this.appAccountEntityMapper.insert(entity) >0) {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(entity.getAppInstance());
			map.put("id", list);
			PushAppDataEntity pushApp = new PushAppDataEntity("app_instance_add", map);
			
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "app_instance", pushApp);
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil;
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
	public Integer insert(AppAccountEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.appAccountEntityMapper.insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer delete(String tid) throws Exception {

		return this.appAccountEntityMapper.delete(tid);
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
	public Integer update(AppAccountEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.appAccountEntityMapper.updateById(entity);
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
	public ResultUtil findPage(AppAccountEntity entity, InterfacePage<AppAccountEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.appAccountEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appAccountEntityMapper.findAll(entity));
		List<AppAccountDto> listDto = new ArrayList<AppAccountDto>();
		AppAccountDto dto = null;
		for (AppAccountEntity ce : page.getList()) {
			
			dto = new AppAccountDto(ce);
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
	public AppAccountEntity findOne(AppAccountEntity entity)
			throws Exception {
		
		List<AppAccountEntity> list = this.appAccountEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	/**
	 *<pre>
	 * 说       明: 查询数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public List<AppAccountEntity> findAll(AppAccountEntity entity)
			throws Exception {
		
		return this.appAccountEntityMapper.findAll(entity);
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
	public AppAccountEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AppAccountEntity> list = this.appAccountEntityMapper.findAll(
				new AppAccountEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:24:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<AppAccountEntity> findAppAccount(String[] ids) throws Exception {
		return this.appAccountEntityMapper.findAppAccount(ids);
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
	public ResultUtil authMQTT(String app_id) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlankOr(app_id)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("app_id");
		}
		List<AppAccountEntity> data = this.appAccountEntityMapper.findAppAccount(new String[]{app_id});
		if (StringUtils.isBlankOr(data)
				|| data.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_CPLUSPLUS_MQTT_AUTH);//应用ID或密钥错误
		}
		if (!StringUtils.isBlankOr(data)) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月13日上午11:36:35
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil removeAppAccount(AppAccountEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		
		for(InterfaceLogEntity interfaceLog : this.interfaceLogEntityMapper.findAll(new InterfaceLogEntity(null, entity.getTid()))){
			this.interfaceEntityMapper.delete(interfaceLog.getTid());
		}
		for(AppAccountInterfaceEntity interfaceLog : this.appAccountInterfaceEntityMapper.findAll(new AppAccountInterfaceEntity(entity.getTid(), null))){
			this.appAccountInterfaceEntityMapper.delete(interfaceLog.getTid());
		}
		AppAccountEntity aae = this.findById(entity.getTid());
		List list = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		list.add(aae.getAppId());
		map.put("id", list);
		PushDataEntity pushModel = new PushDataEntity("user_remove", map);
		
		boolean redisDel = RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "user", pushModel);
		if (redisDel) {
			if (this.appAccountEntityMapper.delete(entity.getTid()) > 0) {
				return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
			}
		}
		return resultUtil;
	}
}
