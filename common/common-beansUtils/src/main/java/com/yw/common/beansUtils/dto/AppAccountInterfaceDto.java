package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public class AppAccountInterfaceDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String orgName;//机构名称 
	private String appId;// 应用ID
	private Integer appVersion;
	private String appName;// 应用名称
	private String interfaceName;// 接口名称
	private Integer dataStatus;// 状态
	private String remark;// 备注
	private String appLicenseTid;//应用许可证tid
	
	public AppAccountInterfaceDto() {
		super();
	}
	
	public AppAccountInterfaceDto(AppAccountInterfaceEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setOrgName(data.getOrgName());
			this.setAppId(data.getAppId());
			this.setAppVersion(data.getAppVersion());
			this.setAppName(data.getAppName());
			this.setInterfaceName(data.getInterfaceName());
			this.setDataStatus(data.getDataStatus());
			this.setRemark(data.getRemark());
			this.setAppLicenseTid(data.getAppLicenseTid());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(),null));
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

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
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
