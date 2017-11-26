package com.yw.webplatform.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yw.common.api.IConfigurationService;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.webplatform.api.IAppAccountInterfaceService;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAppDeviceService;
import com.yw.webplatform.api.IAppFunctionService;
import com.yw.webplatform.api.IAppLicenseService;
import com.yw.webplatform.api.IAppService;
import com.yw.webplatform.api.IAttributeService;
import com.yw.webplatform.api.IAuthLogService;
import com.yw.webplatform.api.ICPlusPlusService;
import com.yw.webplatform.api.IDeviceAttributeService;
import com.yw.webplatform.api.IDeviceLicenseService;
import com.yw.webplatform.api.IDeviceModelAttService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IHomePageService;
import com.yw.webplatform.api.IImportService;
import com.yw.webplatform.api.IInterfaceLogService;
import com.yw.webplatform.api.IInterfaceService;
import com.yw.webplatform.api.IIoDataService;
import com.yw.webplatform.api.ILicenseService;
import com.yw.webplatform.api.IModelAttService;
import com.yw.webplatform.api.IModelEpService;
import com.yw.webplatform.api.IModelService;
import com.yw.webplatform.api.IOrganizationService;
import com.yw.webplatform.api.IPlatformDataService;
import com.yw.webplatform.api.IPushMsgService;
import com.yw.webplatform.api.ISnListService;
import com.yw.webplatform.api.ITradeService;
import com.yw.webplatform.api.IUserInfoService;
import com.yw.webplatform.api.IUserLogService;
import com.yw.webplatform.api.IUsersFunctionsService;
import com.yw.webplatform.api.IUsersRolesService;
import com.yw.webplatform.api.IViewService;
import com.yw.webplatform.api.IWarningService;



/**
 *<pre>
 * 功   能: 功能说明 ：控制器共享类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-9-13下午1:10:25
 * Q  Q: 308053847
 *</pre>
 */
public class BaseControllerTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	
	@Autowired
	public IUserInfoService userInfoService;//用户表
	@Autowired
	public IUserLogService userLogService;//用户注册登录日志表
	@Autowired
	public IConfigurationService configurationService;//字典数据表
	@Autowired
	public IDeviceModelAttService deviceModelAttService;//设备与型号属性表
	@Autowired
	public IWarningService warningService;//报警表
	@Autowired
	public IAttributeService attributeService;//属性表
	@Autowired
	public IDeviceService deviceService;//设备表
	@Autowired
	public IModelService modelService;//设备型号表
	@Autowired
	public IModelAttService modelAttService;//设备型号与属性表
	@Autowired
	public ILicenseService licenseService;//许可证表
	@Autowired
	public IAppService appService;//应用表
	@Autowired
	public IHomePageService homePageService;//
	@Autowired
	public ICPlusPlusService cplusPlusService;//
	@Autowired
	public IPlatformDataService platformDataService;//平台数据
	@Autowired
	public IIoDataService ioDataService;//IO数据
	@Autowired
	public IAppLicenseService appLicenseService;//型号许可证表
	@Autowired
	public IDeviceLicenseService deviceLicenseService;//SN许可证表
	@Autowired
	public IAppDeviceService appDeviceService;//应用与设备
	@Autowired
	public IAppFunctionService appFunctionService;//应用权限
	@Autowired
	public IOrganizationService organizationService;//机构
	@Autowired
	public IDeviceAttributeService deviceAttributeService;//设备属性
	@Autowired
	public IAuthLogService authLogService;//认证记录
	@Autowired
	public IAppAccountService appAccountService;//APP账户
	@Autowired
	public IInterfaceLogService interfaceLogService;//调用接口记录
	@Autowired
	public IPushMsgService pushMsgService;//推送消息
	@Autowired
	public IAppAccountInterfaceService appAccountInterfaceService;//可访问接口列表
	@Autowired
	public IInterfaceService interfaceService;//接口
	@Autowired
	public IUsersRolesService usersRolesService;//角色表
	@Autowired
	public IUsersFunctionsService usersFunctionsService;//权限表
	@Autowired
	public IViewService viewService;//预览
	@Autowired
	public IImportService importService;//导入
	@Autowired
	public IModelEpService modelEpService;//型号流程
	@Autowired
	public ITradeService tradeService;//厂商
	@Autowired
	public ISnListService snListService;//SN列表
	@Autowired
	public IConfigapiService configapiService;//
}