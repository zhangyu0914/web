package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 调用接口记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public class InterfaceLogDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String orgName;// 公司名称	
	private String appName;// 应用名称
	private String appId;// 应用ID
	private Integer appVersion;//应用版本
	private String appInstance;//应用账号
	private String interfaceName;// 接口名称
	private Integer timeConsuming;// 耗时

	public InterfaceLogDto() {
		super();
	}
	
	public InterfaceLogDto(InterfaceLogEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setOrgName(data.getOrgName());
			this.setAppId(data.getAppId());
			this.setAppVersion(data.getAppVersion());
			this.setAppName(data.getAppName());
			this.setAppInstance(data.getAppInstance());
			this.setInterfaceName(data.getInterfaceName());
			this.setTimeConsuming(data.getTimeConsuming());
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Integer getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Integer timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
