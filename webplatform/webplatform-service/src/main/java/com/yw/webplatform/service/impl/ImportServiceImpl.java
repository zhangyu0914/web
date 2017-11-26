package com.yw.webplatform.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.ConfigurationEntity;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.entity.ModelConfigEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.entity.ModelEpEntity;
import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.entity.importFile.AppVersionEntity;
import com.yw.common.beansUtils.entity.importFile.AppsEntity;
import com.yw.common.beansUtils.entity.importFile.DevicesEntity;
import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;
import com.yw.common.beansUtils.entity.importFile.ImportFileEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.entity.importFile.ImportModelEntity;
import com.yw.common.beansUtils.entity.importFile.ImportProfileEntity;
import com.yw.common.beansUtils.entity.importFile.MConfigEntity;
import com.yw.common.beansUtils.entity.importFile.ModelEpsEntity;
import com.yw.common.beansUtils.entity.importFile.ModelPropertyEntity;
import com.yw.common.beansUtils.entity.importFile.ModelVersionEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionCmdEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.Zip4jUtil;
import com.yw.common.beansUtils.utils.des.AESUtil;
import com.yw.common.beansUtils.utils.enums.AttributeAttTypeEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqStatusEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.webplatform.api.IAppService;
import com.yw.webplatform.api.IAttributeService;
import com.yw.webplatform.api.IDeviceLicenseService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IImportService;
import com.yw.webplatform.api.ILicenseService;
import com.yw.webplatform.api.IModelConfigService;
import com.yw.webplatform.api.IModelService;
import com.yw.webplatform.api.IOrganizationService;
import com.yw.webplatform.api.ISnListService;
import com.yw.webplatform.api.ITradeService;

/**
 * <pre>
 * 功       能: 导入
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 * </pre>
 */
@Service("importService")
public class ImportServiceImpl extends BaseMapperImpl implements IImportService {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	@Autowired
	private IAttributeService attributeService;//属性
	@Autowired
	private IModelService modelService;//型号
	@Autowired
	private IAppService appService;//应用
	@Autowired
	private ILicenseService licenseService;//许可证
	@Autowired
	private IOrganizationService organizationService;//机构
	@Autowired
	private ITradeService tradeService;//厂家
	@Autowired
	private IModelConfigService modelConfigService;//型号配置
	@Autowired
	private IConfigapiService configapiService;//内部API
	@Autowired
	private IConfigurationService configurationService;//
	@Autowired
	private IDeviceService deviceService;//
	@Autowired
	private IDeviceLicenseService deviceLicenseService;//
	@Autowired
	private ISnListService snListService;//
	
	/**
	 * <pre>
	 * 说       明: 导入型号
	 * 规       则: 同型号、同版本号导入不变，其余新增
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月22日下午2:14:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importModel(String content, String batNo) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		if (StringUtils.isBlank(content)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		Map<String,Integer> modelMap = new HashMap<String, Integer>();
		List<String> modelList = new ArrayList<String>();
		List list = JavaBeanUtil.jsonToJavaBean(content, List.class);
		if (StringUtils.isBlank(list)
				|| list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		Map<String, ModelEntity> map = new HashMap<String, ModelEntity>();
		for (Object obj : list) {
			
			ImportModelEntity importModelEntity = JavaBeanUtil.jsonToJavaBean(obj.toString(), ImportModelEntity.class); 
			
			for (ModelVersionEntity modelVersion : importModelEntity.getVersions()) {
				
				ModelEntity model = this.modelService.findOne(new ModelEntity(null, null, importModelEntity.getId(), modelVersion.getVersion(), null));
				{//判断
					
					if (!StringUtils.isBlank(model)) {
						log.info("["+batNo+"]【型号】"+"此型号已存在:" + importModelEntity.getId()+",版本:" + modelVersion.getVersion());
						continue;
					}
				}
				{//model
					
					{
						TradeEntity trade = this.tradeService.findOne(
								new TradeEntity(null, importModelEntity.getEnterprise_code()));
						if (StringUtils.isBlank(trade)) {//检查是否存在
							
							this.tradeService.insertTrade(new TradeEntity(
									importModelEntity.getEnterprise_code(), 
									importModelEntity.getEnterprise_code()));
						}
					}
					
					model = new ModelEntity(
							UUIDUtil.getUUID(),
							importModelEntity.getName(),
							importModelEntity.getId(),
							modelVersion.getVersion(),
							importModelEntity.getEnterprise_code());
					model.setModelCode(importModelEntity.getId().split("\\.")[1]);//厂商设备型号
					model.setModelFlag(importModelEntity.getFlag());
					if (!StringUtils.isBlank(importModelEntity.getTags())) {
						
						String tags = importModelEntity.getTags().get(0);
						if (tags.equals("sensor")) {
							model.setEqType(DeviceEqTypeEnum.VIEW.getCode());
						}else{
							model.setEqType(DeviceEqTypeEnum.GATEWAY.getCode());
						}
					}else{
						return resultUtil.setData("["+batNo+"]【型号】"+"新增型号失败:tags为空").setCode(ErrorTypeEnum.FAILURE);
					}
					
					map.put(model.getModelNo(), model);
					if (this.modelEntityMapper.insert(model) == 0) {
						throw new Exception("["+batNo+"]【型号】"+"新增型号失败");
					}
					
					modelMap.put(importModelEntity.getId(), modelVersion.getVersion());
					log.info("["+batNo+"]【型号】"+"新增型号成功:" + model.getModelNo()+",文件内容:" + obj.toString());
					
					{//property
						if (!StringUtils.isBlank(modelVersion.getProperty())) {
							
							AttributeEntity attribute = null;
							for (ModelPropertyEntity property : modelVersion.getProperty()) {
								
								attribute = this.attributeService.findOne(
										new AttributeEntity(property.getProp_id(),null,null));
								if (StringUtils.isBlank(attribute)) {//检查是否存在

									attribute = new AttributeEntity(
											UUIDUtil.getUUID(),
											property.getProp_id(),
											property.getProp_name(),
											property.getProp_name(),
											AttributeAttTypeEnum.STATUS.getCode());// TODO : 需要完善类型
									if (this.attributeEntityMapper.insert(attribute) == 0) {
										throw new Exception("["+batNo+"]【型号】"+"新增属性失败");
									}
									modelList.add(property.getProp_id());
								}
								if (StringUtils.isBlank(this.modelAttEntityMapper.findAll(
										new ModelAttEntity(null, model.getTid(), property.getProp_id())))) {
									
									if (this.modelAttEntityMapper.insert(
											new ModelAttEntity(
													UUIDUtil.getUUID(), 
													model.getTid(), 
													property.getProp_id(),
													property.getProp_name())) == 0) {
										
										throw new Exception("["+batNo+"]【型号】"+"新增型号关联属性失败");
									}
									log.info("["+batNo+"]【型号】"+"新增型号关联属性成功:" + attribute.getProfileId());
								}else{
									log.info("["+batNo+"]【型号】"+"此配置已与属性关联:" + attribute.getProfileId());
								}
								
								{//eps
									if (!StringUtils.isBlank(property.getEps())) {
										
										for (ModelEpsEntity eps : property.getEps()) {
											
											if (StringUtils.isBlank(this.modelEpEntityMapper.findAll(
													new ModelEpEntity(model.getModelNo(), property.getProp_id(), eps.getEp())))) {
												
												if (this.modelEpEntityMapper.insert(
														new ModelEpEntity(
																UUIDUtil.getUUID(), 
																model.getModelNo(), 
																property.getProp_id(),
																eps.getEp(),
																eps.getName(),
																eps.getUnit(),
																eps.getPrecision(),
																StringUtils.toString(eps.getFlows()).toLowerCase())) == 0) {
													
													throw new Exception("["+batNo+"]【型号】"+"新增型号关联EP失败");
												}
												log.info("["+batNo+"]【型号】"+"新增型号关联EP成功:" + eps.getName());
											}
										}
									}
								}
							}
						}
					}
					{//config
						if (!StringUtils.isBlank(modelVersion.getConfig())) {
							
							for (MConfigEntity config : modelVersion.getConfig()) {
								
								if (StringUtils.isBlank(this.modelConfigEntityMapper.findAll(new ModelConfigEntity(model.getTid(), config.getConfig_id())))) {
									if (this.modelConfigEntityMapper.insert(
											new ModelConfigEntity(UUIDUtil.getUUID(), model.getTid(), config.getConfig_id(),
													config.getConfig_name(),config.getConfig_type(),
													StringUtils.toString(config.getFlows()).toLowerCase())) == 0) {
										throw new Exception("新增型号关联属性失败");
									}
									log.info("["+batNo+"]【型号】"+"新增型号关联配置成功:" + config.getConfig_id());
								}else{
									log.info("["+batNo+"]【型号】"+"此配置已与型号关联:" + config.getConfig_id());
								}
							}
						}
					}

				}
			}
		}
		return resultUtil.setData(map).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 导入应用
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月22日下午2:14:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importApp(String content, String batNo) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		if (StringUtils.isBlank(content)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		
		List list = JavaBeanUtil.jsonToJavaBean(content, List.class);
		for(Object obj : list){
			
			ImportAppEntity importAppEntity = JavaBeanUtil.jsonToJavaBean(obj.toString(), ImportAppEntity.class);
			if (StringUtils.isBlank(importAppEntity.getVersions())
					|| importAppEntity.getVersions().isEmpty()) {
				
				log.info("["+batNo+"]【应用】"+"应用版本集合为空");
				continue;
			}
			for (AppVersionEntity appVersion : importAppEntity.getVersions()) {
				
				AppEntity app = null;
				{//app
					app = this.appService.findOne(new AppEntity(null, importAppEntity.getId(), null, appVersion.getVersion(), null));
					if (!StringUtils.isBlank(app)) {//检查是否存在
						
						log.info("["+batNo+"]【应用】"+"此应用已存在:" + app.getAppId()+",版本:" + appVersion.getVersion());
						continue;
					}
					{
						{
							TradeEntity trade = this.tradeService.findOne(
									new TradeEntity(null, importAppEntity.getEnterprise_code()));
							if (StringUtils.isBlank(trade)) {//检查是否存在
								
								this.tradeService.insertTrade(new TradeEntity(
										importAppEntity.getEnterprise_code(), 
										importAppEntity.getEnterprise_code()));
							}
						}
						app = new AppEntity(
								UUIDUtil.getUUID(),
								importAppEntity.getId(),
								importAppEntity.getName(),
								appVersion.getVersion(),
								importAppEntity.getEnterprise_code());
						if (this.appEntityMapper.insert(app) == 0) {
							throw new Exception("新增应用失败");
						}
						log.info("["+batNo+"]【应用】"+"新增应用成功:" + app.getAppId()+",版本:"+app.getAppVersion()+",文件内容:" + obj.toString());
						
						{//permission
							for (PermissionEntity permission : appVersion.getPermission()) {
								
								for(PermissionCmdEntity cmd : permission.getCmds()){
									
									if (StringUtils.isBlank(cmd.getFlow())) {
										continue;
									}
									for(String flow : cmd.getFlow()){
										
										if (StringUtils.isBlank(this.appFunctionEntityMapper.findAll(new AppFunctionEntity(null, app.getTid(),cmd.getProp_id(),
												permission.getModel_id(), flow, cmd.getEp(), app.getAppId(), app.getAppVersion())))) {
											
											if (this.appFunctionEntityMapper.insert(new AppFunctionEntity(UUIDUtil.getUUID(), app.getTid(), 
													cmd.getProp_id(),permission.getModel_id(), flow, cmd.getEp(), app.getAppId(), app.getAppVersion())) == 0) {
												
												throw new Exception("["+batNo+"]【应用】"+"新增应用关联属性失败");
											}
											log.info("["+batNo+"]【应用】"+"新增应用关联权限成功:" + cmd.getProp_id());
										}else{
											log.info("["+batNo+"]【应用】"+"此权限已与应用关联:" + cmd.getProp_id());
										}
									}
									
								}
							}
						}
					}
				}
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 导入许可证
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月22日下午2:14:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importLicense(String content, String batNo, Map<String, ModelEntity> modelMap) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		if (StringUtils.isBlank(content)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		List<String> licenseList = new ArrayList<String>();
		List list = JavaBeanUtil.jsonToJavaBean(content, List.class);
		
		log.info("["+batNo+"]【许可证】开始");
		for(Object obj : list){
			
			ImportLicenseEntity importLicense = JavaBeanUtil.jsonToJavaBean(obj.toString(), ImportLicenseEntity.class);
			{
				if (StringUtils.isBlank(this.configurationService.findAll(new ConfigurationEntity(null, null, importLicense.getPid())))) {
					log.error("["+batNo+"]【许可证】"+"PID不对");
					continue;
				}
			}
			LicenseEntity license = null;
			{//license
				
				license = this.licenseService.findOne(new LicenseEntity(null, importLicense.getId()));;
				if (StringUtils.isBlank(license)) {//检查是否存在
					
					license = new LicenseEntity(
							UUIDUtil.getUUID(),
							importLicense.getId(),
							importLicense.getPid(),
							importLicense.getDesc(),
							importLicense.getCreate_time(),
							importLicense.getExp_time());
					
					if (this.licenseEntityMapper.insert(license) == 0) {
						throw new Exception("["+batNo+"]【许可证】"+"新增应用失败");
					}
					licenseList.add(importLicense.getId());
					
					
					
					{//device
						String fkSnLicenceTid = "";
						for (DevicesEntity devices : importLicense.getDevices()) {
							
							for(String sn : devices.getSn_list()){
								
								fkSnLicenceTid = UUIDUtil.getUUID();
								if (this.deviceLicenseEntityMapper.insert(new DeviceLicenseEntity(
										fkSnLicenceTid, license.getTid(), 
										sn, devices.getModel_id(), importLicense.getCreate_time(), importLicense.getExp_time())) == 0) {
									
									throw new Exception("["+batNo+"]【许可证】"+"新增许可证关联属性失败");
								}
								
								{//添加设备
									DeviceEntity de = new DeviceEntity();
									de.setSn(sn);
									String deviceType = sn.substring(4,8);
									
									if (StringUtils.isBlank(this.deviceService.findOneBySn(sn))) {
										
										if (!StringUtils.isBlank(modelMap)) {
											
											ModelEntity me = modelMap.get(devices.getModel_id());
											if (!StringUtils.isBlank(me)) {
												
												de.setModelNo(me.getModelNo());
												de.setModelVersion(me.getModelVersion());
												de.setEqName(me.getModelName());
												de.setFkSnLicenceTid(fkSnLicenceTid);
												
												de.setFkOrganizationTid(devices.getModel_id().split("\\.")[0]);
											}
										}
										de.setEqStatus(DeviceEqStatusEnum.OFFLINE.getCode());
										de.setDeviceType(deviceType);
										if (this.deviceService.insert(de) == 0) {
											throw new Exception("["+batNo+"]【许可证】"+"添加设备失败");
										}
									}
								}
							}
						}
					}
					
					{//app
						
						List<AppLicenseEntity> appLicenseList = this.appLicenseEntityMapper.findAll(null);
						Map<String, AppLicenseEntity> appLicenseMap = new HashMap<String, AppLicenseEntity>();
						if (!StringUtils.isBlank(appLicenseList)
								&& !appLicenseList.isEmpty()) {
							
							for (AppLicenseEntity appLicense : appLicenseList) {
								appLicenseMap.put(appLicense.getAppInstanceLicId(), appLicense);
							}
						}
						for (AppsEntity apps : importLicense.getApps()) {
							
							int index = 0;
							for(String appInstanceLicId : apps.getLic_ids()){
								
								if (!appLicenseMap.containsKey(appInstanceLicId)) {
									
									if (this.appLicenseEntityMapper.insert(new AppLicenseEntity(
											UUIDUtil.getUUID(), license.getTid(), 
											appInstanceLicId, apps.getApp_id(), 
											importLicense.getCreate_time(), importLicense.getExp_time(), ++ index, apps.getApp_name() + index,
											"images/icon/RYDW.png")) == 0) {
										
										throw new Exception("["+batNo+"]【许可证】"+"新增应用许可证失败");
									}
								}else{
									log.info("["+batNo+"]【许可证】"+"此应用实例许可证ID已存在:" + appInstanceLicId+"[应用实例ID]");
								}
							}
						}
					}
				}else{
					log.info("["+batNo+"]【许可证】"+"此许可证已存在:" + license.getLicNo());
				}
			}
		}
		log.info("["+batNo+"]【许可证】结束");
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月27日下午3:41:15
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importProfile(String content) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		if (StringUtils.isBlank(content)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		List<String> profileList = new ArrayList<String>();
		List list = JavaBeanUtil.jsonToJavaBean(content, List.class);
		{//删除
				
			for(DeviceAttributeEntity data : this.deviceAttributeEntityMapper.findAll(null)){
				this.deviceAttributeEntityMapper.delete(data.getTid());
			}
			for(ModelAttEntity data : this.modelAttEntityMapper.findAll(null)){
				this.modelAttEntityMapper.delete(data.getTid());
			}
			for(AttributeEntity data : this.attributeEntityMapper.findAll(null)){
				this.configapiService.removeProfile(data.getProfileId());
			}
			
		}
		for (Object obj : list) {
			
			ImportProfileEntity profile = JavaBeanUtil.jsonToJavaBean(obj.toString(), ImportProfileEntity.class);
			AttributeEntity attribute = null;
			{//PROFILE
				
				attribute = this.attributeService.findOne(new AttributeEntity(profile.getId(), null, null));
				if (StringUtils.isBlank(attribute)) {//检查是否存在
					
					attribute = new AttributeEntity(
							UUIDUtil.getUUID(),
							profile.getId(),
							profile.getName(),
							profile.getName(),
							AttributeAttTypeEnum.SETTING.getCode()
							);
					
					if (this.attributeEntityMapper.insert(attribute) == 0) {
						throw new Exception("新增属性失败");
					}
					profileList.add(profile.getId());
					
				}else{
					
					
					String lua = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\luaFile\\" 
					+ attribute.getAttName() + ".lua");
					if (!StringUtils.isBlank(lua)) {
						
						attribute.setRemark(lua);
						this.attributeEntityMapper.updateById(attribute);
					}
					log.info("此PROFILE已经存在" + attribute.getProfileId());
				}
			}
		}

		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 导入PROFILE
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月5日下午6:06:28
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importProfileFile(String filePath, String batNo) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(filePath)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("path");
		}
		{//删除
			for(AttributeEntity data : this.attributeEntityMapper.findAll(null)){
				this.configapiService.removeProfile(data.getProfileId());
			}
			log.info("["+batNo+"]【PROFILE】"+"删除所有PROFILE成功");
		}
		File file = new File(filePath);
		List<String> list = new ArrayList<String>();
		for(File f : file.listFiles()){
			
			String content = FileUtil.readFile(f);
			int index = content.indexOf("return 0x");
			String id = content.substring(index + 9, index + 15);
			String value = f.getName().substring(0,f.getName().length() - 4);
			
			AttributeEntity attribute = null;
			{//PROFILE
				
				attribute = this.attributeService.findOne(new AttributeEntity(id, null, null));
				if (StringUtils.isBlank(attribute)) {//检查是否存在
					
					attribute = new AttributeEntity(
							UUIDUtil.getUUID(),
							id,
							value,
							value,
							AttributeAttTypeEnum.SETTING.getCode()
							);
					attribute.setRemark(content);
					if (this.attributeService.insert(attribute) == 0) {
						throw new Exception("新增属性失败");
					}
					log.info("["+batNo+"]【PROFILE】"+"添加PROFILE成功:" + value);
					list.add(id);
				}else{
					attribute.setRemark(content);
					if (this.attributeService.update(attribute) == 0) {
						throw new Exception("更新属性失败");
					}
				}
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 导入打包文件
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月8日下午1:35:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil importFile(String sourcePath, String batNo, String zipPassword) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		
		if (StringUtils.isBlank(sourcePath)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
		}
		//解密文件
		File sourceFile = new File(sourcePath);
		File zipFile = AESUtil.decryptFile(
				sourcePath, 
				sourceFile.getParent(), 
				System.currentTimeMillis() + ".zip", 
				ConfigurationEnum.LICENSE_AES_KEY.getValue() + ConfigurationEnum.PID.getValue(), 
				ConfigurationEnum.PID.getValue());
		
		//解压文件
		String targetPath = "/mnt/temp/";
		File [] fileArray = Zip4jUtil.unZipFiles(zipFile.getPath(), targetPath, zipPassword);
		if (StringUtils.isBlank(fileArray)) {
			
			log.info("["+batNo+"]"+"解压失败");
			return resultUtil.setData("解压失败");
		}
		
		log.info("["+batNo+"]"+"开始解压文件包");
		
		//导入型号、应用、许可证
		String all = FileUtil.readFile(targetPath+"pack.json");
		if (!StringUtils.isBlank(all)) {
			
			ImportFileEntity file = JavaBeanUtil.jsonToJavaBean(all, ImportFileEntity.class);
			if (StringUtils.isBlank(file)) {
				return resultUtil;
			}
			{//型号
				String content = JavaBeanUtil.javaBeanToString(file.getModel_info());
				ResultUtil modelResultUtil = this.importModel(content, batNo);
			}
			{//应用
				String content = JavaBeanUtil.javaBeanToString(file.getApp_info());
				this.importApp(content, batNo);
			}
			{//许可证
				String content = JavaBeanUtil.javaBeanToString(file.getLicense_info());
				List<ModelEntity> modelList = this.modelEntityMapper.findAll(null);
				Map<String, ModelEntity> modelMap = new HashMap<String, ModelEntity>();
				if (!StringUtils.isBlank(modelList)
						&& !modelList.isEmpty()) {
					for (ModelEntity model : modelList) {
						modelMap.put(model.getModelNo(), model);
					}
				}
				this.importLicense(content, batNo, modelMap);
			}
		}
		{//导入PROFILE
			this.importProfileFile(targetPath+"profile", batNo);
		}
		return resultUtil.setData(batNo).setCode(ErrorTypeEnum.SUCCESS);
	}
}
