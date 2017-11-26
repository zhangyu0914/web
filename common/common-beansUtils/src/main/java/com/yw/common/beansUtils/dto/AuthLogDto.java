package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功       能: 认证记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class AuthLogDto extends BaseDto {

	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String appId;// 应用ID
	private Integer appVersion;
	private String appInstance;// 应用账号
	private String token;// 会话
	private String authMsg;// 认证信息
	private Integer authStatus;// 认证状态
	private String tokenTimeout;// 会话超时时间
	private String orgName;//机构名称
	private String appName;//应用名称

	public AuthLogDto() {
		super();
	}

	public AuthLogDto(AuthLogEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setAppId(data.getAppId());
			this.setAppVersion(data.getAppVersion());
			this.setAppInstance(data.getAppInstance());
			this.setToken(data.getToken());
			this.setAuthMsg(data.getAuthMsg());
			this.setAuthStatus(data.getAuthStatus());
			this.setOrgName(data.getOrgName());
			this.setAppName(data.getAppName());
			try {
				this.setTokenTimeout(DateUtils.format(DateUtils.formatDate(data.getTokenTimeout()), null));
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuthMsg() {
		return authMsg;
	}

	public void setAuthMsg(String authMsg) {
		this.authMsg = authMsg;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getTokenTimeout() {
		return tokenTimeout;
	}

	public void setTokenTimeout(String tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
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

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
