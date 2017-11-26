package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppAccountInterfaceDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public class AppAccountInterfaceEntity extends BaseEntity {

	private static final long serialVersionUID = -2985357507634L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.APPACCOUNTINTERFACE.FKAPPACCOUNTTID")
	private String fkAppAccountTid;// 应用账号外键
	@Length(min=0, max=50, message = "WEBPLATFORM.APPACCOUNTINTERFACE.FKINTERFACETID")
	private String fkInterfaceTid;// 接口外键
	private String checkUrl;// 
	private String checkIp;// 
	private String checkType;// 
	private Integer dataStatus;// 状态
	@Length(min=0, max=100, message = "WEBPLATFORM.APPACCOUNTINTERFACE.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String orgName;//机构名称 
	private String appId;// 应用ID
	private Integer appVersion;
	private String appName;// 应用名称
	private String interfaceName;// 接口名称
	private String appLicenseTid;//应用许可证tid
	
	//无参构造方法
	public AppAccountInterfaceEntity() {
		super();
	}
	
	//TID参数构造方法
	public AppAccountInterfaceEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AppAccountInterfaceEntity(AppAccountInterfaceDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public AppAccountInterfaceEntity(String fkAppAccountTid,
			String fkInterfaceTid) {
		super();
		this.fkAppAccountTid = fkAppAccountTid;
		this.fkInterfaceTid = fkInterfaceTid;
	}

	public String getFkAppAccountTid() {
		return fkAppAccountTid;
	}

	public void setFkAppAccountTid(String fkAppAccountTid) {
		this.fkAppAccountTid = fkAppAccountTid;
	}

	public String getFkInterfaceTid() {
		return fkInterfaceTid;
	}

	public void setFkInterfaceTid(String fkInterfaceTid) {
		this.fkInterfaceTid = fkInterfaceTid;
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getCheckUrl() {
		return checkUrl;
	}

	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}

	public String getCheckIp() {
		return checkIp;
	}

	public void setCheckIp(String checkIp) {
		this.checkIp = checkIp;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getAppLicenseTid() {
		return appLicenseTid;
	}

	public void setAppLicenseTid(String appLicenseTid) {
		this.appLicenseTid = appLicenseTid;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
