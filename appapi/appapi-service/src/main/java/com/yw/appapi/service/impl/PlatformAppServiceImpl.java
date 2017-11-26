package com.yw.appapi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yw.appapi.api.IControlLogService;
import com.yw.appapi.api.IPlatformAppService;
import com.yw.appapi.mapper.BaseMapperImpl;
import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.dto.ControlLogDto;
import com.yw.common.beansUtils.dto.platformapp.PlatformDeviceDto;
import com.yw.common.beansUtils.dto.platformapp.PlatformDeviceDto.DeviceAuthStatus;
import com.yw.common.beansUtils.dto.platformapp.PlatformDeviceDto.DeviceOnlineStatus;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.entity.importFile.PushModelDataEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.DeviceEqStatusEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.enums.SystemConfigEnum;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.resultUtil.ControlInfo;
import com.yw.common.beansUtils.utils.resultUtil.ControlResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAppDeviceService;
import com.yw.webplatform.api.IAppFunctionService;
import com.yw.webplatform.api.IAuthLogService;
import com.yw.webplatform.api.IDeviceAttributeService;
import com.yw.webplatform.api.IDeviceLicenseService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IModelService;


/**
 *<pre>
 * 功       能: 平台提供APP访问接口
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 *</pre>
 */
@Service("platformAppService")
public class PlatformAppServiceImpl extends BaseMapperImpl  implements IPlatformAppService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IConfigurationService configurationService;//
	@Autowired
	private IAppAccountService appAccountService;//
	@Autowired
	private IAppDeviceService appDeviceService;//
	@Autowired
	private IDeviceLicenseService deviceLicenseService;//
	@Autowired
	private IAuthLogService authLogService;//
	@Autowired
	private IAppFunctionService appFunctionService;//
	@Autowired
	private IDeviceService deviceService;//
	@Autowired
	private IModelService modelService;//
	@Autowired
	private IDeviceAttributeService deviceAttributeService;//
	@Autowired
	private IControlLogService controlLogService;//
	
	private static ExecutorService es = null;
	
	/**
	 * <pre>
	 * 说       明: 设置线程池
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午5:30:43
	 * Q     Q: 982234234
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
	 * 说       明: 设备状态查询接口(A1001)[数据来源：本机查]
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil deviceGet(String sn) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(sn)) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"sn");// 缺少参数
		}
		PlatformDeviceDto dto = new PlatformDeviceDto();
		Map<String, Object> dpsMap = new HashMap<String, Object>();
		
		
		DeviceEntity device = this.deviceService.findOneBySn(sn);
		if (StringUtils.isBlank(device)) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		dto.setSn(sn);
		
		ModelEntity searchModel = new ModelEntity();
		searchModel.setModelNo(device.getModelNo());
		ModelEntity model = this.modelService.findOne(searchModel);
		
		if (!StringUtils.isBlank(model)) {
			
			dto.setModel_no(model.getModelNo());
			dto.setModel_name(model.getModelName());
		}
		dto.setDevice_auth_status(DeviceOnlineStatus.ONLINE.getCode());
		dto.setDevice_online_status(DeviceAuthStatus.AUTH.getCode());
		
		DeviceAttributeEntity deviceAttribute = this.deviceAttributeService.findOne(new DeviceAttributeEntity(null, sn));
		if (!StringUtils.isBlank(deviceAttribute)) {
			dpsMap = JavaBeanUtil.jsonToMap(deviceAttribute.getJsonData());
		}
		return resultUtil.setData(dto).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备历史数据查询(A1002)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil historyDeviceDataGet(PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getSn(),
				entity.getStart_time(),
				entity.getEnd_time())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"sn,start_time,end_time");// 缺少参数
		}
		List list = new ArrayList();
		PlatformDeviceDto dto = new PlatformDeviceDto();
		Map<String, String> dpsMap = new HashMap<String, String>();
		
		dto.setSn(entity.getSn());
		dto.setModel_no("8001.B610");
		dto.setModel_name("婴儿防盗");
		dto.setDevice_auth_status(DeviceOnlineStatus.ONLINE.getCode());
		dto.setDevice_online_status(DeviceAuthStatus.AUTH.getCode());
		
		dpsMap.put("battery", "78");
		dpsMap.put("hearbeat", "97");
		dto.setDevice_prop_status(dpsMap);
		
		list.add(dto);
		list.add(dto);
		return resultUtil.setData(list).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备在离线历史数据查询接口(A1003)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil onofflineDeviceDataGet(PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getSn(),
				entity.getStart_time(),
				entity.getEnd_time())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"sn,start_time,end_time");// 缺少参数
		}
		List list = new ArrayList();
		PlatformDeviceDto dto = new PlatformDeviceDto();
		Map<String, String> dpsMap = new HashMap<String, String>();
		
		dto.setSn(entity.getSn());
		dto.setModel_no("8001.B610");
		dto.setModel_name("婴儿防盗");
		dto.setDevice_auth_status(DeviceOnlineStatus.ONLINE.getCode());
		dto.setDevice_online_status(DeviceAuthStatus.AUTH.getCode());
		
		dpsMap.put("battery", "78");
		dpsMap.put("hearbeat", "97");
		dto.setDevice_prop_status(dpsMap);
		
		list.add(dto);
		list.add(dto);
		return resultUtil.setData(list).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 下行历史数据查询接口(A1004)[数据来源：本机查]
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil historyDescendingOrderGet(PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getApp_instance(),
				entity.getStart_time(),
				entity.getEnd_time())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,start_time,end_time");// 缺少参数
		}
		List<ControlLogEntity> list = this.controlLogService.findAll(new ControlLogEntity(entity.getApp_instance(), null));
		if (StringUtils.isBlank(list)) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		List<ControlLogDto> dataList = new ArrayList(); 
		for (ControlLogEntity controlLog : list) {
			dataList.add(new ControlLogDto(controlLog));
		}
		return resultUtil.setData(dataList).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备控制(A1005)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Object deviceControl(ControlEntity entity) throws Exception {
		ControlResultUtil resultUtil = new ControlResultUtil(entity);	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getUuid(),
				entity.getTime())) {
			
			resultUtil.getInfo().setAck("缺少参数：uuid或time");
			return resultUtil;// 缺少参数
		}
		String host= this.configurationService.sysParam().get(SystemConfigEnum.YW_DEVMGR_PORT_9002_TCP_ADDR.toString());
		log.info("--------host------"+host);
		String response = UrlUtil.post(host, JavaBeanUtil.javaBeanToString(entity));
		log.info("--------response--------"+response);
		if (StringUtils.isBlank(response)) {
			resultUtil.getInfo().setAck("errmsg");
			return resultUtil;
		}else{
			if (response.indexOf("{") != -1) {
				return JavaBeanUtil.jsonToMap(response);
			}else{
				return response;
			}
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 设备绑定(A1007)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil devicebindSet(String app_instance, String sn, String token) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(app_instance) || StringUtils.isBlankOr(sn)) {// TODO : token 要改为必传
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,sn");// 缺少参数
		}
		
		if (!StringUtils.isBlank(token)) {
			AuthLogEntity authLog = this.authLogService.findAuthLogByToken(token);
			if (!authLog.getAppInstance().equals(app_instance)) {
				//此会话ID中的实例ID与被操作的实例ID不匹配
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_BIND_TOKEN_APP_INSTANCE_AUTH);
			}
		}
		//判断平台应用实例
		AppAccountEntity appAccount = this.appAccountService.findOne(
				new AppAccountEntity(null, app_instance, null));
		if (StringUtils.isBlank(appAccount)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_INSTANCE_IS_NO_EXISTS).setData("app_instance");// 应用实例不存在
		}
		//判断SN号
		DeviceLicenseEntity deviceLicense = this.deviceLicenseService.findOne(
				new DeviceLicenseEntity(null, null, sn));
		if (StringUtils.isBlank(deviceLicense)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_DEVICE_SN_NOT_EXISTS);// 未在许可证中找到此SN号
		}
		
		//判断型号是否支持
		List<AppFunctionEntity> appFunctionList = this.appFunctionService.findAppFunctionBySn(new AppFunctionEntity(appAccount.getAppId(), sn));
		if (StringUtils.isBlank(appFunctionList)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_FUNCTION_ERROR_BIND_MODEL_CHECK);// 此应用不支持此型号绑定
		}
		
		//绑定判断状态
		AppDeviceEntity appDevice = this.appDeviceService.findOne(
				new AppDeviceEntity(null, null, sn));
		if (!StringUtils.isBlank(appDevice)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_BIND_EXISTS);// 已经绑定，请误重复绑定
		}
		
		//添加数据
		AppDeviceEntity appDeviceInsert = new AppDeviceEntity(UUIDUtil.getUUID(), appAccount.getTid(), sn);
		appDeviceInsert.setAppId(appAccount.getAppId());
		appDeviceInsert.setAppVersion(appAccount.getAppVersion());
		
		DeviceEntity device = this.deviceService.findOne(new DeviceEntity(null, null, sn));
		if (!StringUtils.isBlank(device)) {
			appDeviceInsert.setEqStatus(device.getEqStatus());
		}else{
			appDeviceInsert.setEqStatus(DeviceEqStatusEnum.OFFLINE.getCode());
		}
		
		appDeviceInsert.setBindingTime(DateUtils.getSysStringTime());
		appDeviceInsert.setModelNo(deviceLicense.getModelNo());
		appDeviceInsert.setEqName(deviceLicense.getModelName());
		
		if (this.appDeviceService.insert(appDeviceInsert) == 0) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
		}
		
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("instance_id", app_instance);
			map.put("sn_list", new String[]{sn});
			list.add(map);
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "bind", new PushModelDataEntity("dev_bind", list));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备解绑(A1008)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil devicebindDel(String app_instance, String sn, String token) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(app_instance) || StringUtils.isBlankOr(app_instance, sn)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,sn");// 缺少参数
		}
		
		if (!StringUtils.isBlank(token)) {
			AuthLogEntity authLog = this.authLogService.findAuthLogByToken(token);
			if (!authLog.getAppInstance().equals(app_instance)) {
				//此会话ID中的实例ID与被操作的实例ID不匹配
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_BIND_TOKEN_APP_INSTANCE_AUTH);
			}
		}
		//判断平台应用实例
		AppAccountEntity appAccount = this.appAccountService.findOne(
				new AppAccountEntity(null, app_instance, null));
		if (StringUtils.isBlank(appAccount)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_INSTANCE_IS_NO_EXISTS);// 应用实例不存在
		}
		//判断SN号
		{
			DeviceLicenseEntity deviceLicense = this.deviceLicenseService.findOne(
					new DeviceLicenseEntity(null, null, sn));
			if (StringUtils.isBlank(deviceLicense)) {
				
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_DEVICE_SN_NOT_EXISTS);// SN号不存在
			}
		}
		
		//绑定判断状态
		AppDeviceEntity appDevice = this.appDeviceService.findOne(
				new AppDeviceEntity(null, appAccount.getTid(), sn));
		if (StringUtils.isBlank(appDevice)) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_DEVICE_SN_NOT_BIND_APP);// SN号未绑定到此应用下
		}
		if (this.appDeviceService.delete(appDevice.getTid()) == 0) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
		}
		
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("instance_id", app_instance);
			map.put("sn_list", new String[]{sn});
			list.add(map);
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "bind", new PushModelDataEntity("dev_unbind", list));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 应用状态心跳提交接口(A1009)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil appstatusSet(PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getApp_instance(),
				entity.getStatus(),
				entity.getSysload(),
				entity.getMemory(),
				entity.getDisk(),
				entity.getCpu())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,status,sysload,memory,disk,cpu");// 缺少参数
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.platformAppEntityMapper.insert(entity) == 1) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}
	
	
	/**
	 * <pre>
	 * 说       明: 应用错误提交接口(A1010)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil appstatusPost(PlatformAppEntity entity) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getApp_instance(),
				entity.getStatus(),
				entity.getSysload(),
				entity.getMemory(),
				entity.getDisk(),
				entity.getCpu())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"app_instance,status,sysload,memory,disk,cpu");// 缺少参数
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.platformAppEntityMapper.insert(entity) == 1) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备实时状态查询接口(A1011)
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:53:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public AppResultUtil deviceRealTimeGet(String sn) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();	
		if (StringUtils.isBlank(sn)) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData(
					"sn");// 缺少参数
		}
		PlatformDeviceDto dto = new PlatformDeviceDto();
		Map<String, Object> dpsMap = new HashMap<String, Object>();
		
		DeviceEntity device = this.deviceService.findOneBySn(sn);
		if (StringUtils.isBlank(device)) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		dto.setSn(sn);
		
		ModelEntity searchModel = new ModelEntity();
		searchModel.setModelNo(device.getModelNo());
		ModelEntity model = this.modelService.findOne(searchModel);
		
		if (!StringUtils.isBlank(model)) {
			
			dto.setModel_no(model.getModelNo());
			dto.setModel_name(model.getModelName());
		}
		dto.setDevice_auth_status(DeviceOnlineStatus.ONLINE.getCode());
		dto.setDevice_online_status(DeviceAuthStatus.AUTH.getCode());
		
		DeviceAttributeEntity deviceAttribute = this.deviceAttributeService.findOne(new DeviceAttributeEntity(null, sn));
		if (!StringUtils.isBlank(deviceAttribute)) {
			dpsMap = JavaBeanUtil.jsonToMap(deviceAttribute.getJsonData());
		}
		
		dto.setDevice_prop_status(dpsMap);
		return resultUtil.setData(dto).setCode(ErrorTypeEnum.SUCCESS);
	}
	
}
