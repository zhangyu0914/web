package com.yw.common.beansUtils.dto.platformapp;

import com.yw.common.beansUtils.dto.BaseDto;

/**
 * <pre>
 * 功       能: 设备绑定实体
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class DeviceBindDto extends BaseDto {

	private static final long serialVersionUID = -1109795279518681480L;
	private String app_name;// 应用名称
	private String app_id;// 应用ID
	private String app_instance;// 应用账号
	private String sn;// 设备SN
	private Integer status;// 状态 0：离线，1：在线，2：未接入

	public DeviceBindDto() {
		super();
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_instance() {
		return app_instance;
	}

	public void setApp_instance(String app_instance) {
		this.app_instance = app_instance;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
