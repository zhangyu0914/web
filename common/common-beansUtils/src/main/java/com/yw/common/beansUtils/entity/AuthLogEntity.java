package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AuthLogDto;
import com.yw.common.beansUtils.dto.platformapp.AuthDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 认证记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 *</pre>
 */
public class AuthLogEntity extends BaseEntity {

	private static final long serialVersionUID = -2986347222798L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.AUTHLOG.APPID")
	private String appId;// 应用ID
	private Integer appVersion;//应用版本
	@Length(min=0, max=100, message = "WEBPLATFORM.AUTHLOG.APPACCOUNT")
	private String appInstance;// 应用实例
	@Length(min=0, max=100, message = "WEBPLATFORM.AUTHLOG.TOKEN")
	private String token;// 会话
	@Length(min=0, max=100, message = "WEBPLATFORM.AUTHLOG.AUTHMSG")
	private String authMsg;// 认证信息
	private Integer onlineStatus;// 在线状态
	private Integer authStatus;// 认证状态
	private String tokenTimeout;// 会话超时时间

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String appKey;// 应用密钥
	private String clientIp;//客户端IP
	private String clientUrl;//客户端域名
	private String orgName;//机构名称
	private String appName;//应用名称
	
	//无参构造方法
	public AuthLogEntity() {
		super();
	}
	
	//TID参数构造方法
	public AuthLogEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AuthLogEntity(AuthLogDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public AuthLogEntity(String appId, String appInstance, String token) {
		super();
		this.appId = appId;
		this.appInstance = appInstance;
		this.token = token;
	}

	public AuthLogEntity(String appId, String appInstance, Integer onLineStatus) {
		super();
		this.appId = appId;
		this.appInstance = appInstance;
		this.onlineStatus = onLineStatus;
	}

	public AuthLogEntity(String appId, AuthDto data) {
		if (data != null) {
			
			this.setAppId(appId);
			this.setAppInstance(data.getApp_instance());
			this.setToken(data.getToken());
			this.setAuthMsg(data.getAuth_msg());
			this.setAuthStatus(data.getAuth_status());
			this.setOnlineStatus(data.getOnline_status());
			this.setTokenTimeout(data.getToken_timeout());
		}
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

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
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
