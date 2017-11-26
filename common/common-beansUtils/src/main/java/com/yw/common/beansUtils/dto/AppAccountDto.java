package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 * </pre>
 */
public class AppAccountDto extends BaseDto {

	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;
	private String orgName;
	private String appId;
	private Integer appVersion;
	private String appName;
	private String appInstance;
	private Integer msgMaxCount;
	private Integer accountStatus;
	private String appReportName;
	private String remark;

	public AppAccountDto() {
		super();
	}

	public AppAccountDto(AppAccountEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setOrgName(data.getOrgName());
			this.setAppId(data.getAppId());
			this.setAppVersion(data.getAppVersion());
			this.setAppName(data.getAppName());
			this.setAppInstance(data.getAppInstance());
			this.setMsgMaxCount(data.getMsgMaxCount());
			this.setAccountStatus(data.getAccountstatus());
			this.setAppReportName(data.getAppReportName());
			this.setRemark(data.getRemark());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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


	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public Integer getMsgMaxCount() {
		return msgMaxCount;
	}

	public void setMsgMaxCount(Integer msgMaxCount) {
		this.msgMaxCount = msgMaxCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
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
