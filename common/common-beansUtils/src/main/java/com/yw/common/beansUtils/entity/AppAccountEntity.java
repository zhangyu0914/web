package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppAccountDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 *</pre>
 */
public class AppAccountEntity extends BaseEntity {

	private static final long serialVersionUID = -2985516909312L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.APPACCOUNT.FKORGANIZATIONTID")
	private String fkOrganizationTid;// 机构
	@Length(min=0, max=50, message = "WEBPLATFORM.APPACCOUNT.FKAPPLICENSETID")
	private String fkAppLicenseTid;// 应用许可证外键
	@Length(min=0, max=50, message = "WEBPLATFORM.APPACCOUNT.APPID")
	private String appId;// 应用ID
	private Integer appVersion;// 应用版本
	@Length(min=0, max=100, message = "WEBPLATFORM.APPACCOUNT.APPACCOUNT")
	private String appInstance;// 应用账号
	@Length(min=0, max=100, message = "WEBPLATFORM.APPACCOUNT.APPKEY")
	private String appKey;// 应用密钥
	private Integer msgMaxCount;// 可接收最大消息数
	private Integer accountstatus;// 账号状态
	@Length(min=0, max=100, message = "WEBPLATFORM.APPACCOUNT.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String orgName;
	private String appName;
	private String appReportName;
	
	//无参构造方法
	public AppAccountEntity() {
		super();
	}
	
	//TID参数构造方法
	public AppAccountEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AppAccountEntity(AppAccountDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public AppAccountEntity(String fkAppLicenseTid, String appId) {
		super();
		this.fkAppLicenseTid = fkAppLicenseTid;
		this.appId = appId;
	}

	public AppAccountEntity(String appId, String appInstance, String appKey) {
		super();
		this.appId = appId;
		this.appInstance = appInstance;
		this.appKey = appKey;
	}

	public String getFkOrganizationTid() {
		return fkOrganizationTid;
	}

	public void setFkOrganizationTid(String fkOrganizationTid) {
		this.fkOrganizationTid = fkOrganizationTid;
	}

	public String getFkAppLicenseTid() {
		return fkAppLicenseTid;
	}

	public void setFkAppLicenseTid(String fkAppLicenseTid) {
		this.fkAppLicenseTid = fkAppLicenseTid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Integer getMsgMaxCount() {
		return msgMaxCount;
	}

	public void setMsgMaxCount(Integer msgMaxCount) {
		this.msgMaxCount = msgMaxCount;
	}

	public Integer getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(Integer accountstatus) {
		this.accountstatus = accountstatus;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
