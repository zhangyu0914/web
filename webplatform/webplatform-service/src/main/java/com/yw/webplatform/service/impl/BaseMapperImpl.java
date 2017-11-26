package com.yw.webplatform.service.impl;

import com.yw.webplatform.mapper.SnListEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.yw.webplatform.mapper.AppAccountEntityMapper;
import com.yw.webplatform.mapper.AppAccountInterfaceEntityMapper;
import com.yw.webplatform.mapper.AppDeviceEntityMapper;
import com.yw.webplatform.mapper.AppEntityMapper;
import com.yw.webplatform.mapper.AppFunctionEntityMapper;
import com.yw.webplatform.mapper.AppLicenseEntityMapper;
import com.yw.webplatform.mapper.AttributeEntityMapper;
import com.yw.webplatform.mapper.AuthLogEntityMapper;
import com.yw.webplatform.mapper.DeviceAttributeEntityMapper;
import com.yw.webplatform.mapper.DeviceEntityMapper;
import com.yw.webplatform.mapper.DeviceLicenseEntityMapper;
import com.yw.webplatform.mapper.DeviceModelAttEntityMapper;
import com.yw.webplatform.mapper.InterfaceEntityMapper;
import com.yw.webplatform.mapper.InterfaceLogEntityMapper;
import com.yw.webplatform.mapper.IoDataEntityMapper;
import com.yw.webplatform.mapper.LicenseEntityMapper;
import com.yw.webplatform.mapper.ModelAttEntityMapper;
import com.yw.webplatform.mapper.ModelConfigEntityMapper;
import com.yw.webplatform.mapper.ModelEntityMapper;
import com.yw.webplatform.mapper.ModelEpEntityMapper;
import com.yw.webplatform.mapper.OrganizationEntityMapper;
import com.yw.webplatform.mapper.PlatformDataEntityMapper;
import com.yw.webplatform.mapper.PushMsgEntityMapper;
import com.yw.webplatform.mapper.ReportDataEntityMapper;
import com.yw.webplatform.mapper.TradeEntityMapper;
import com.yw.webplatform.mapper.UserInfoEntityMapper;
import com.yw.webplatform.mapper.UserLogEntityMapper;
import com.yw.webplatform.mapper.UsersFunctionsEntityMapper;
import com.yw.webplatform.mapper.UsersRolesEntityMapper;
import com.yw.webplatform.mapper.UsersRolesFunctionsEntityMapper;
import com.yw.webplatform.mapper.UsersRolesUsersEntityMapper;
import com.yw.webplatform.mapper.ViewEntityMapper;
import com.yw.webplatform.mapper.WarningEntityMapper;


/**
 *<pre>
 * 功       能: 基础MAPPER
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-7上午10:03:16
 * Q    Q: 308053847
 *</pre>
 */
public class BaseMapperImpl {

	@Autowired
	public AppEntityMapper appEntityMapper;//应用表
	@Autowired
	public UserInfoEntityMapper userInfoEntityMapper;//用户表
	@Autowired
	public UserLogEntityMapper userLogEntityMapper;//用户注册登录日志表
	@Autowired
	public WarningEntityMapper warningEntityMapper;//报警表
	@Autowired
	public AttributeEntityMapper attributeEntityMapper;//属性表
	@Autowired
	public DeviceEntityMapper deviceEntityMapper;//设备表
	@Autowired
	public ModelEntityMapper modelEntityMapper;//设备型号表
	@Autowired
	public DeviceModelAttEntityMapper deviceModelAttEntityMapper;//设备与型号属性表
	@Autowired
	public LicenseEntityMapper licenseEntityMapper;//许可证表
	@Autowired
	public ModelAttEntityMapper modelAttEntityMapper;//设备型号与属性表
	@Autowired
	public PlatformDataEntityMapper platformDataEntityMapper;//平台数据
	@Autowired
	public IoDataEntityMapper ioDataEntityMapper;//IO数据
	@Autowired
	public AppLicenseEntityMapper appLicenseEntityMapper;//型号许可证表
	@Autowired
	public DeviceLicenseEntityMapper deviceLicenseEntityMapper;//SN许可证表
	@Autowired
	public AppDeviceEntityMapper appDeviceEntityMapper;//应用与设备
	@Autowired
	public AppFunctionEntityMapper appFunctionEntityMapper;//应用权限
	@Autowired
	public OrganizationEntityMapper organizationEntityMapper;//机构
	@Autowired
	public ReportDataEntityMapper reportDataEntityMapper;//
	@Autowired
	public DeviceAttributeEntityMapper deviceAttributeEntityMapper;//设备属性
	@Autowired
	public AuthLogEntityMapper authLogEntityMapper;//认证记录
	@Autowired
	public AppAccountEntityMapper appAccountEntityMapper;//APP账户
	@Autowired
	public InterfaceLogEntityMapper interfaceLogEntityMapper;//调用接口记录
	@Autowired
	public PushMsgEntityMapper pushMsgEntityMapper;//推送消息
	@Autowired
	public AppAccountInterfaceEntityMapper appAccountInterfaceEntityMapper;//可访问接口列表
	@Autowired
	public InterfaceEntityMapper interfaceEntityMapper;//接口
	@Autowired
	public UsersFunctionsEntityMapper usersFunctionsEntityMapper;//
	@Autowired
	public UsersRolesEntityMapper usersRolesEntityMapper;//
	@Autowired
	public UsersRolesUsersEntityMapper usersRolesUsersEntityMapper;//
	@Autowired
	public UsersRolesFunctionsEntityMapper usersRolesFunctionsEntityMapper;//
	@Autowired
	public ViewEntityMapper viewEntityMapper;//预览
	@Autowired
	public ModelConfigEntityMapper modelConfigEntityMapper;//型号配置信息
	@Autowired
	public ModelEpEntityMapper modelEpEntityMapper;//型号流程
	@Autowired
	public TradeEntityMapper tradeEntityMapper;//厂商
	@Autowired
	public SnListEntityMapper snListEntityMapper;//SN列表
}