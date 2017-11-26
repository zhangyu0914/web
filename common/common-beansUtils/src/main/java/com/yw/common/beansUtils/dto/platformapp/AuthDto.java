package com.yw.common.beansUtils.dto.platformapp;

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
public class AuthDto{

	private static final long serialVersionUID = -1109795279518681480L;
	private String org_name;// 应用ID
	private String app_name;// 应用ID
	private String app_instance;// 应用账号
	private String token;// 会话
	private String auth_msg;// 认证信息
	private Integer auth_status;// 认证状态
	private Integer online_status;// 0:在线,1:离线
	private String token_timeout;// 会话超时时间

	public AuthDto() {
		super();
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_instance() {
		return app_instance;
	}

	public void setApp_instance(String app_instance) {
		this.app_instance = app_instance;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAuth_msg() {
		return auth_msg;
	}

	public void setAuth_msg(String auth_msg) {
		this.auth_msg = auth_msg;
	}

	public Integer getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(Integer auth_status) {
		this.auth_status = auth_status;
	}

	public Integer getOnline_status() {
		return online_status;
	}

	public void setOnline_status(Integer online_status) {
		this.online_status = online_status;
	}

	public String getToken_timeout() {
		return token_timeout;
	}

	public void setToken_timeout(String token_timeout) {
		this.token_timeout = token_timeout;
	}

}
