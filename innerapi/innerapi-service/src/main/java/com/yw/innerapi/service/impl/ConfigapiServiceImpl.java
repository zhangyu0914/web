package com.yw.innerapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppInstanceDevDto;
import com.yw.common.beansUtils.dto.AppSecretDto;
import com.yw.common.beansUtils.dto.ConfigapiDto;
import com.yw.common.beansUtils.dto.ProfileDto;
import com.yw.common.beansUtils.dto.importFile.AppInfoDto;
import com.yw.common.beansUtils.dto.importFile.ImportLicenseDto;
import com.yw.common.beansUtils.dto.importFile.ImportModelDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppInstanceDevEntity;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.ConfigapiEntity;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.PushMQTTInfoEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.entity.importFile.EpEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppSecretEntity;
import com.yw.common.beansUtils.entity.importFile.GetLicenseEntty;
import com.yw.common.beansUtils.entity.importFile.GetModelEntity;
import com.yw.common.beansUtils.entity.importFile.GetProfileEntity;
import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;
import com.yw.common.beansUtils.entity.importFile.ImportDeviceStatusEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.entity.importFile.ImportModelEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.entity.importFile.PushModelDataEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.innerapi.mapper.BaseMapperImpl;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAppDeviceService;
import com.yw.webplatform.api.IAttributeService;
import com.yw.webplatform.api.IDeviceAttributeService;
import com.yw.webplatform.api.IDeviceLicenseService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IIoDataService;
import com.yw.webplatform.api.ILicenseService;
import com.yw.webplatform.api.IModelService;
import com.yw.webplatform.api.IOrganizationService;
import com.yw.webplatform.api.ISnListService;
import com.yw.webplatform.api.ITradeService;
import com.yw.webplatform.api.IWarningService;


/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13:01:21
 * Q    Q: 308053847
 *</pre>
 */
@Service("configapiService")
public class ConfigapiServiceImpl extends BaseMapperImpl  implements IConfigapiService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IDeviceAttributeService deviceAttributeService;//
	@Autowired
	private IDeviceService deviceService;//
	@Autowired
	private IDeviceLicenseService deviceLicenseService;//
	@Autowired
	private IOrganizationService organizationService;//
	@Autowired
	private IModelService modelService;//
	@Autowired
	private ITradeService tradeService;//
	@Autowired
	private IAppAccountService appAccountService;//
	@Autowired
	private ILicenseService licenseService;//
	@Autowired
	private IAttributeService attributeService;//
	@Autowired
	private IAppDeviceService appDeviceService;//
	@Autowired
	private IIoDataService ioDataService;//
	@Autowired
	private IWarningService warningService;//警告服务层
	@Autowired
	private ISnListService snListService;
	
	
	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil modelGet(GetModelEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		List<ImportModelDto> dataList = new ArrayList<ImportModelDto>();
		
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getData())
				&& !entity.getData().isEmpty()) {
			
			for (String str : entity.getData()) {
				
				ImportModelEntity importModel = JavaBeanUtil.jsonToJavaBean(str, ImportModelEntity.class);
				if (!StringUtils.isBlank(importModel.getId())) {
					
					List<ImportModelEntity> modelList = this.configapiEntityMapper.findModel(importModel);
					for (ImportModelEntity model : modelList) {
						dataList.add(new ImportModelDto(model));
					}
				}
			}
		}else{
			List<ImportModelEntity> modelList = this.configapiEntityMapper.findModel(null);
			for (ImportModelEntity model : modelList) {
				dataList.add(new ImportModelDto(model));
			}
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	

	/**
	 * <pre>
	 * 说       明: 获取应用配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月24日 下午3:04:59
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil appInstanceGet(GetAppEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		String[] ids = null;
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getId())
				&& entity.getId().length > 0) {
			
			List<String> list = new ArrayList<String>();;
			for(String str : entity.getId()){
				
				if(!StringUtils.isBlank(str)){
					list.add(str);
				}
			}
			ids =  (String[])list.toArray(new String[list.size()]);
		}
		List<ImportAppEntity> modelList = this.configapiEntityMapper.findApp(ids);
		List<AppInfoDto> dataList = new ArrayList<AppInfoDto>();
		for (ImportAppEntity app : modelList) {
			if (!StringUtils.isBlank(app)
					&& !StringUtils.isBlank(app.getInstance_id())) {
				dataList.add(new AppInfoDto(app));
			}
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	

	/**
	 * <pre>
	 * 说       明: 获取许可证数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil licenseGet(GetLicenseEntty entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		String[] ids = null;
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getId())
				&& entity.getId().length > 0) {
			
			List<String> list = new ArrayList<String>();;
			for(String str : entity.getId()){
				
				if(!StringUtils.isBlank(str)){
					list.add(str);
				}
			}
			ids =  (String[])list.toArray(new String[list.size()]);
		}
		List<ImportLicenseEntity> modelList = this.configapiEntityMapper.findLicense(ids);
		List<ImportLicenseDto> dataList = new ArrayList<ImportLicenseDto>();
		for (ImportLicenseEntity model : modelList) {
			dataList.add(new ImportLicenseDto(model));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 更新PROFILE
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:49:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil removeModel(String id, Integer version) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(id) || StringUtils.isBlankOr(
				id,
				version)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"id,version");// 缺少参数
		}
		List<ModelEntity> meList = this.modelService.findAll(new ModelEntity(id, version));
		if (StringUtils.isBlank(meList)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		if (this.modelService.delete(meList.get(0).getTid()) > 0) {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("version", version);
			list.add(map);
			PushModelDataEntity pushModel = new PushModelDataEntity("model_remove", list);
			
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "model", pushModel);
			return resultUtil.setData(pushModel).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月26日上午10:54:36
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil removeAppInstance(String id) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(id) || StringUtils.isBlankOr(id)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"id");// 缺少参数
		}
		List<AppAccountEntity> meList = this.appAccountService.findAll(new AppAccountEntity(null, id, null));
		if (StringUtils.isBlank(meList)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		if (this.appAccountService.delete(meList.get(0).getTid()) > 0) {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			list.add(map);
			PushModelDataEntity pushModel = new PushModelDataEntity("app_instance_remove", list);
			
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "app_instance", pushModel);
			return resultUtil.setData(pushModel).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}
	

	/**
	 * <pre>
	 * 说       明: 删除许可证
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:49:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil removeLicense(String id) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(id) || StringUtils.isBlankOr(id)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"id");// 缺少参数
		}
		
		List<LicenseEntity> meList = this.licenseService.findAll(new LicenseEntity(null, id));
		if (StringUtils.isBlank(meList)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		
		if (this.licenseService.delete(meList.get(0).getTid()) > 0) {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(id);
			map.put("id", list);
			PushDataEntity pushModel = new PushDataEntity("license_remove", map);
			
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
			return resultUtil.setData(pushModel).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}
	
	/**
	 * <pre>
	 * 说       明: 属性删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:49:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil removeProfile(String profileId) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(profileId)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"profileId");// 缺少参数
		}
		List<AttributeEntity> meList = this.attributeService.findAll(new AttributeEntity(profileId,null,null));
		if (StringUtils.isBlank(meList)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		if (this.attributeService.delete(meList.get(0).getTid()) > 0) {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(profileId);
			map.put("id", list);
			PushDataEntity pushModel = new PushDataEntity("profile_remove", map);
			
//			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
			return resultUtil.setData(pushModel).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}
	
	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil profileGet(GetProfileEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		String[] ids = null;
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getId())
				&& entity.getId().length > 0) {
			
			List<String> list = new ArrayList<String>();;
			for(String str : entity.getId()){
				
				if(!StringUtils.isBlank(str)){
					list.add(str);
				}
			}
			ids =  (String[])list.toArray(new String[list.size()]);
		}
		AttributeEntity attribute = new AttributeEntity();
		attribute.setId(ids);
		List<AttributeEntity> modelList = this.attributeService.findByProfileId(attribute);
		List<ProfileDto> dataList = new ArrayList<ProfileDto>();
		for (AttributeEntity model : modelList) {
			dataList.add(new ProfileDto(model));
		}
		if (StringUtils.isBlank(dataList) || dataList.isEmpty()) {
			return resultUtil.setData(new String[]{}).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	

	/**
	 * <pre>
	 * 说       明: 获取实例绑定数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil appInstanceDevGet(AppInstanceDevEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		List<AppInstanceDevEntity> modelList = this.configapiEntityMapper.findAppInstanceDevGet(entity);
		List<AppInstanceDevDto> dataList = new ArrayList<AppInstanceDevDto>();
		
		for (AppInstanceDevEntity devices : modelList) {
			dataList.add(new AppInstanceDevDto(devices));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 调试使用
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月26日上午9:58:14
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil initData(String type) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		String data = null;
		if(type.equals("model")){
			
			{//型号
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "8001.B610");
				map.put("version", 0);
				list.add(map);
				PushModelDataEntity pushModel = new PushModelDataEntity("model_add", list);
				data = JavaBeanUtil.javaBeanToString(pushModel);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "model", pushModel);
			}
		}else if(type.equals("model_remove")){
			{//型号
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "8001.B610");
				map.put("version", 0);
				list.add(map);
				PushModelDataEntity pushModel = new PushModelDataEntity("model_remove", list);
				data = JavaBeanUtil.javaBeanToString(pushModel);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "model", pushModel);
			}
		}else if(type.equals("app_instance")){
			
			{//应用
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				
				list.add("test");
				map.put("id", list);
				PushDataEntity pushData = new PushDataEntity("app_instance_add", map);
				
				data = JavaBeanUtil.javaBeanToString(pushData);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "app_instance", pushData);
			}
		}else if(type.equals("app_instance_remove")){
			
			{//应用
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				list.add("test");
				map.put("id", list);
				PushDataEntity pushData = new PushDataEntity("app_instance_remove", map);
				data = JavaBeanUtil.javaBeanToString(pushData);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "app_instance", pushData);
			}
		}else if(type.equals("license")){
			
			{//许可证
				List list = new ArrayList();
				list.add("LIC_1001");
				PushModelDataEntity pushModel = new PushModelDataEntity("license_add", list);
				
				data = JavaBeanUtil.javaBeanToString(pushModel);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
			}
		}else if(type.equals("license_remove")){
			{//许可证
				List list = new ArrayList();
				Map map = new HashMap();
				list.add("LIC_1001");
				map.put("id",list);
				PushDataEntity pushData = new PushDataEntity("license_remove", map);
				
				data = JavaBeanUtil.javaBeanToString(pushData);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushData);
			}
		}else if(type.equals("profile")){
			
			{//属性/PROFILE ID
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				list.add("200001");
				map.put("id", list);
				PushDataEntity pushModel = new PushDataEntity("profile_add", map);
				
				data = JavaBeanUtil.javaBeanToString(pushModel);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
			}
		}else if(type.equals("profile_remove")){
			{//属性/PROFILE ID
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				list.add("200001");
				map.put("id", list);
				PushDataEntity pushModel = new PushDataEntity("profile_remove", map);
				
				data = JavaBeanUtil.javaBeanToString(pushModel);
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
			}
		}
		return resultUtil.setData(data).setCode(ErrorTypeEnum.SUCCESS);
	}
	

	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param :tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public ResultUtil appSecretGet(GetAppSecretEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		String[] ids = null;
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getId())
				&& entity.getId().length > 0) {
			
			List<String> list = new ArrayList<String>();;
			for(String str : entity.getId()){
				
				if(!StringUtils.isBlank(str)){
					list.add(str);
				}
			}
			ids =  (String[])list.toArray(new String[list.size()]);
		}
		List<AppAccountEntity> modelList = this.appAccountService.findAppAccount(ids);
		List<AppSecretDto> dataList = new ArrayList<AppSecretDto>();
		for (AppAccountEntity app : modelList) {
			dataList.add(new AppSecretDto(app));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备信息推送
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月15日下午3:07:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	synchronized public ResultUtil deviceStatusAdd(PushMQTTEntity entity) throws Exception {
		String batNum = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
		log.debug("[deviceStatus_"+batNum+"]开始");
		ResultUtil resultUtil = new ResultUtil();		
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getSn(),
						entity.getModel_version(),
						entity.getModel_id())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("sn,model_version,model_id");
		}
		//判断SN号
		log.debug("[deviceStatus_"+batNum+"]判断SN号:" + entity.getSn());
		DeviceLicenseEntity deviceLis = this.deviceLicenseService.findOne(
				new DeviceLicenseEntity(null, null, entity.getSn()));
		if (StringUtils.isBlank(deviceLis)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_DEVICE_SN_NOT_EXISTS);// 未在许可证中找到此SN号
		}
		String jsonStr = JavaBeanUtil.javaBeanToString(entity);
		log.debug("[deviceStatus_"+batNum+"]jsonStr:" + jsonStr);
		int result = 0;
		String batchNumber = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
		if (StringUtils.isBlank(entity.getInfo())) {
			log.info("[deviceStatus_"+batNum+"]INFO信息为空");
			return resultUtil;
		}
		DeviceAttributeEntity deviceAttribute = null;
		for (PushMQTTInfoEntity info : entity.getInfo()) {
			
			if (StringUtils.isBlank(info)) {
				
				log.info("[deviceStatus_"+batNum+"]INFO 对象信息为空");
				continue;
			}
			deviceAttribute = new DeviceAttributeEntity(
					UUIDUtil.getUUID(),
					entity.getSn(),
					info.getEp(),
					info.getProp_id(),
					info.getUnit(),
					JavaBeanUtil.javaBeanToString(info.getValue()),
					jsonStr,
					batchNumber);
			deviceAttribute.setModelNo(entity.getModel_id());
			deviceAttribute.setModelVersion(entity.getModel_version());
			result = this.deviceAttributeService.updateData(deviceAttribute);
			if (result == 0) {//无可更新数据则添加
				result = this.deviceAttributeService.insert(deviceAttribute);
				log.debug("[deviceStatus_"+batNum+"]添加设备状态信息成功");
			}else{
				log.debug("[deviceStatus_"+batNum+"]更新设备状态信息成功");
			}
			
			if (result != 1) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
			}
		}
		log.debug("[deviceStatus_"+batNum+"]结束");
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: NODEJS推送I/O、平台数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月3日下午4:20:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil ioAdd() throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		List<IoDataEntity> list = null, dataList = null;
		while(true){
			
			log.info("queue_io_statistics:" + DateUtils.getSysStringTime());
			list = RedisUtil.brpop("queue_io_statistics");
			if (!StringUtils.isBlank(list)
					&& !list.isEmpty()) {
				
				dataList = new ArrayList<IoDataEntity>();
				for(Object obj : list){
					JSONArray array = JSONArray.parseArray(obj.toString());
					for (Object object : array) {
						dataList.add((IoDataEntity) JavaBeanUtil.jsonToJavaBean(object.toString(), IoDataEntity.class));
					}
				}
				for (IoDataEntity data : dataList) {
					
					if (StringUtils.isBlankOr(data.getDataValue(), data.getDataCode(),data.getDataTime())) {
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("dataValue,dataCode,dataTime");
					}
					this.ioDataService.insert(data);
				}
			}
		}
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(ConfigapiEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.configapiEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(ConfigapiEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.configapiEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(ConfigapiEntity entity, InterfacePage<ConfigapiEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.configapiEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.configapiEntityMapper.findAll(entity));
		List<ConfigapiDto> listDto = new ArrayList<ConfigapiDto>();
		ConfigapiDto dto = null;
		for (ConfigapiEntity ce : page.getList()) {
			
			dto = new ConfigapiDto(ce);
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
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Override
	public ConfigapiEntity findOne(ConfigapiEntity entity)
			throws Exception {
		
		List<ConfigapiEntity> list = this.configapiEntityMapper.findAll(entity);
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
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Override
	public ConfigapiEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ConfigapiEntity> list = this.configapiEntityMapper.findAll(
				new ConfigapiEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public List<ImportLicenseEntity> findExLicense() throws Exception {
		return this.configapiEntityMapper.findExLicense();
	}

	/**
	 *<pre>
	 * 说       明: 根据sn更新设备状态（t_device），在线：0,离线：1
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017-07-04 13:01:21
	 *</pre>
	 */
	@Override
	public ResultUtil devicePut() throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<DeviceEntity> list = null, dataList = null;
		while(true){
			
			log.info("queue_online_status:" + DateUtils.getSysStringTime());
			list = RedisUtil.brpop("queue_online_status");
			if (!StringUtils.isBlank(list)
					&& !list.isEmpty()) {
				
				dataList = new ArrayList<DeviceEntity>();
				for(Object obj : list){
					dataList.add((DeviceEntity) JavaBeanUtil.jsonToJavaBean(obj.toString(), DeviceEntity.class));
				}
				
				for(DeviceEntity de : dataList){
					
					//检查sn是否为空
					if(StringUtils.isBlank(de.getSn())){
						continue;
					}
					//检查eqStatus是否为空
					if(StringUtils.isBlank(de.getEqStatus())){
						continue;
					}
					log.info("更新设备状态[开始]：["+de.getSn()+"]" + de.getEqStatus());
					Integer result = this.deviceService.update(de);
					//先判断是否为空
					if(result == 0){
						continue;
					}
					if(result == 1){
						//应用设备绑定也同步更新
						if (this.appDeviceService.updateBySN(new AppDeviceEntity(de.getSn(), de.getEqStatus())) > 0) {
							log.info("更新设备状态成功：["+de.getSn()+"]" + de.getEqStatus());
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 说       明: 新增警告接口
	 * @return
	 * @throws Exception
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月5日下午2:06:17
	 * Q    Q: 17789861157
	 * </pre>
	 */
	public ResultUtil warningSet() throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		List<WarningEntity> list = null, dataList = null;
		while(true){//实时监测
			
			log.info("queue_offline_warning:" + DateUtils.getSysStringTime());
			list = RedisUtil.brpop("queue_offline_warning");
			if (!StringUtils.isBlank(list)
					&& !list.isEmpty()) {
				
				dataList = new ArrayList<WarningEntity>();
				for(Object obj : list){
					dataList.add((WarningEntity) JavaBeanUtil.jsonToJavaBean(obj.toString(), WarningEntity.class));
				}
				
				for (WarningEntity warning : dataList) {
					
					
					//检查sn是否为空
					if(StringUtils.isBlank(warning.getSn())){
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("sn");
					}
					//检查content是否为空
					if(StringUtils.isBlank(warning.getContent())){
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("content");
					}
					//检查离线事件时间是否为空
					if(StringUtils.isBlank(warning.getEventTime())){
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("eventTime");
					}
					//拼接出content:[8001011212345678]设备离线[OnLineStatus]异常
					warning.setContent("[" + warning.getSn() + "]" + "设备离线" + "[" + warning.getContent() + "]" + "异常");
					//如果waType为空，添加默认值
					if(StringUtils.isBlank(warning.getWaType())){
						warning.setWaType(0);
					}
					//如果waStatus为空，添加默认值0:未读
					if(StringUtils.isBlank(warning.getWaStatus())){
						warning.setWaStatus(0);;
					}
					//根据SN号判断网关、感知：ErrorType.java:getEqType
					warning.setDeviceType(warning.getSn().substring(4,8));   
					warning.setTid(UUID.randomUUID().toString());
					//添加数据
					Integer result = this.warningService.insert(warning);
					if(result==1){
						log.info("添加警告成功!");
					}
				}
			}
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年8月22日上午9:24:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil testJava(WarningEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		int i=0;
		for(;;){
			log.info(++i);
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 定时更新设备状态
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年8月24日下午3:25:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil deviceStatusPlan() throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		log.debug("开始");
		//把表中数据缓存到内存中
		Map<String, DeviceAttributeEntity> dbMap = new HashMap<String, DeviceAttributeEntity>();
		List<DeviceAttributeEntity> dbList = this.deviceAttributeService.findAll(null);
		if (!StringUtils.isBlank(dbList)
				&& !dbList.isEmpty()) {
			
			for (DeviceAttributeEntity deviceAttribute : dbList) {
				dbMap.put(deviceAttribute.getSn() + ":" + deviceAttribute.getModelNo() + ":" + deviceAttribute.getPropId(), deviceAttribute);
			}
		}
		
		//获取REDIS数据并缓存到内存中
		Map<String, String> redisSource = RedisUtil.getHSetAll("device_status_list");
		Map<String, DeviceAttributeEntity> redisMap = new HashMap<String, DeviceAttributeEntity>();
		if (StringUtils.isBlank(redisSource)
				|| redisSource.isEmpty()) {
			
			log.info("REDIS KEY:device_status_list  没有数据");
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		
		String key = "", statusKey = "", statusProfileKey = "", redisKey = "";
		String batNum = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
		EpEntity json = null;
		StringBuffer sql = null;
		DeviceAttributeEntity deviceAttribute_redis = null;
		List<String> updateSQL = new ArrayList<String>();
		List<String> insertSQL = new ArrayList<String>();
		for(Iterator<String> iterator = redisSource.keySet().iterator(); iterator.hasNext();){//sn
			
			key = iterator.next();
			
			ImportDeviceStatusEntity importDS = JavaBeanUtil.jsonToJavaBean(redisSource.get(key), ImportDeviceStatusEntity.class);
			if (!StringUtils.isBlank(importDS.getStatus())
					&& !importDS.getStatus().isEmpty()) {
				
				statusKey = "";
				for(Iterator<String> iteratorStatus = importDS.getStatus().keySet().iterator(); iteratorStatus.hasNext();){//profileID

					statusKey = iteratorStatus.next();
					if (!StringUtils.isBlank(importDS.getStatus().get(statusKey))
							&& !importDS.getStatus().get(statusKey).isEmpty()) {
						
						statusProfileKey = "";
						for(Iterator<String> iteratorStatusProfile = importDS.getStatus().get(statusKey).keySet().iterator(); iteratorStatusProfile.hasNext();){//ep
							
							statusProfileKey = iteratorStatusProfile.next();
							if (!StringUtils.isBlank(importDS.getStatus().get(statusKey).get(statusProfileKey))
									&& !importDS.getStatus().get(statusKey).get(statusProfileKey).isEmpty()) {
								
								json = new EpEntity();
								JavaBeanUtil.mapToJavaBean(importDS.getStatus().get(statusKey).get(statusProfileKey), json);
								
								deviceAttribute_redis = new DeviceAttributeEntity();
								deviceAttribute_redis.setSn(key);
								deviceAttribute_redis.setModelNo(importDS.getModel_id());
								deviceAttribute_redis.setModelVersion(importDS.getModel_version());
								deviceAttribute_redis.setEp(json.getEp());
								deviceAttribute_redis.setPropId(statusKey);//PROFILE ID
								deviceAttribute_redis.setUnit(json.getUnit());
								deviceAttribute_redis.setInfoValue(json.getValue() + "");
								deviceAttribute_redis.setJsonData(redisSource.get(key) + "");
								deviceAttribute_redis.setBatchNumber(batNum);
								deviceAttribute_redis.setUpdateTime(Long.valueOf(DateUtils.format(DateUtils.format(json.getUpdatetime()), DateUtils.PATTERN_24_YYYYMMDDHHMMSS)));
								deviceAttribute_redis.setCreateTime(DateUtils.getTimestamp());
								
								redisKey = key + ":" + deviceAttribute_redis.getModelNo() + ":" + statusKey;
								redisMap.put(redisKey, deviceAttribute_redis);
								
								if (dbMap.containsKey(redisKey)) {//比对数据
									
									//判断更新时间
									if (dbMap.get(redisKey).getUpdateTime().intValue() != deviceAttribute_redis.getUpdateTime().intValue()) {
										
										//判断各字段
										sql = new StringBuffer();
										sql.append("update t_device_attribute set "
												+ " update_time = '"+deviceAttribute_redis.getUpdateTime()+"'  "
												+ ",batch_number = '"+deviceAttribute_redis.getBatchNumber()+"'   "
												+ ",json_data = '"+deviceAttribute_redis.getJsonData()+"'    ");
										
										if (StringUtils.isBlank(dbMap.get(redisKey).getModelVersion())
												&& !StringUtils.isBlank(deviceAttribute_redis.getModelVersion())) {
											
											sql.append(",model_version = '"+deviceAttribute_redis.getModelVersion());
										}else if (!StringUtils.isBlank(deviceAttribute_redis.getModelVersion()) && dbMap.get(redisKey).getModelVersion().intValue() != deviceAttribute_redis.getModelVersion().intValue()) {//已存在值不相同则创建UPDATE SQL
											sql.append(",model_version = '"+deviceAttribute_redis.getModelVersion());
										}
										if (StringUtils.isBlank(dbMap.get(redisKey).getEp())
												&& !StringUtils.isBlank(deviceAttribute_redis.getEp().intValue())) {
											
											sql.append(",ep = '"+deviceAttribute_redis.getEp()+"'   ");
										}else if (!StringUtils.isBlank(deviceAttribute_redis.getEp()) && dbMap.get(redisKey).getEp().intValue() != deviceAttribute_redis.getEp().intValue()) {//
											sql.append(",ep = '"+deviceAttribute_redis.getEp()+"'   ");
										}
										if (StringUtils.isBlank(dbMap.get(redisKey).getUnit())
												&& !StringUtils.isBlank(deviceAttribute_redis.getUnit())) {
											
											sql.append(",unit = '"+deviceAttribute_redis.getUnit()+"'   ");
										} else if (!StringUtils.isBlank(deviceAttribute_redis.getUnit()) && !dbMap.get(redisKey).getUnit().equals(deviceAttribute_redis.getUnit())) {//
											sql.append(",unit = '"+deviceAttribute_redis.getUnit()+"'   ");
										}
										if (StringUtils.isBlank(dbMap.get(redisKey).getInfoValue())
												&& !StringUtils.isBlank(deviceAttribute_redis.getInfoValue())) {
											
											sql.append(",info_value = '"+deviceAttribute_redis.getInfoValue()+"'   ");
										} else if (!StringUtils.isBlank(deviceAttribute_redis.getInfoValue()) && !dbMap.get(redisKey).getInfoValue().equals(deviceAttribute_redis.getInfoValue())) {//
											sql.append(",info_value = '"+deviceAttribute_redis.getInfoValue()+"'   ");
										}
										sql.append("where tid = '"+dbMap.get(redisKey).getTid()+"';  " + "\n");
										updateSQL.add(sql.toString());
									}
								}else{
									
									//不存在则创建INSERT SQL
									sql = new StringBuffer();
									sql.append("insert into t_device_attribute " +"(");
										sql.append("tid, ");
										sql.append("sn, ");
										sql.append("model_no, ");
										sql.append("model_version, ");
										sql.append("ep, ");
										sql.append("prop_id, ");
										sql.append("unit, ");
										sql.append("info_value, ");
										sql.append("json_data, ");
										sql.append("batch_number, ");
										sql.append("create_time, ");
										sql.append("update_time");
									sql.append(")");
									sql.append("values");
									sql.append("(");
										sql.append( "'"+UUIDUtil.getUUID()+"', ");
										sql.append("'"+deviceAttribute_redis.getSn()+"', ");
										sql.append("'"+deviceAttribute_redis.getModelNo()+"', ");
										sql.append(deviceAttribute_redis.getModelVersion() + ", ");
										sql.append("'"+deviceAttribute_redis.getEp() +"', ");
										sql.append("'"+deviceAttribute_redis.getPropId() +"', ");
										if (StringUtils.isBlank(deviceAttribute_redis.getUnit())) {
											sql.append("null, ");
										}else{
											sql.append("'"+deviceAttribute_redis.getUnit() +"', ");
										}
										if (StringUtils.isBlank(deviceAttribute_redis.getInfoValue())) {
											sql.append("null, ");
										}else{
											sql.append("'"+deviceAttribute_redis.getInfoValue() +"', ");
										}
										sql.append("'"+deviceAttribute_redis.getJsonData() +"', ");
										sql.append("'"+deviceAttribute_redis.getBatchNumber() +"', ");
										sql.append("'"+deviceAttribute_redis.getCreateTime() +"', ");
										sql.append("'"+deviceAttribute_redis.getUpdateTime() +"'");
									sql.append(");" );
									
									sql.append("\n");
									insertSQL.add(sql.toString());
								}
							}
						}
					}
				}
			}
			{//更新设备状态
//				this.deviceService.updateStatusBySN(new DeviceEntity(importDS.getSn(), importDS.getOnline().equals("true") ? 0 : 1));
			}
		}
		updateSQL.addAll(insertSQL);
		if (updateSQL.isEmpty()) {
			log.info("数据值没有变，不需要修改");
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		for (String string : updateSQL) {
			log.info(string);
		}
		//批量执行SQL
		this.configapiEntityMapper.changeBatSQL(updateSQL);
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
}
