package com.yw.common.beansUtils.dto.platformapp;

import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.dto.BaseDto;

/**
 * <pre>
 * 功       能: 平台APP设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 * </pre>
 */
public class PlatformDeviceDto{

	private static final long serialVersionUID = -1109795279518681480L;
	private String sn;// SN
	private String app_instance;// 实例
	private String model_no;// 型号编号
	private String model_name;// 型号名称
	private Integer device_online_status;// 0:离线，1：在线
	private Integer device_auth_status;// 0:未认证，1：已认证
	private Map device_prop_status;// 设备属性

	public PlatformDeviceDto() {
		super();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getModel_no() {
		return model_no;
	}

	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public Integer getDevice_online_status() {
		return device_online_status;
	}

	public void setDevice_online_status(Integer device_online_status) {
		this.device_online_status = device_online_status;
	}

	public Integer getDevice_auth_status() {
		return device_auth_status;
	}

	public void setDevice_auth_status(Integer device_auth_status) {
		this.device_auth_status = device_auth_status;
	}

	public Map getDevice_prop_status() {
		return device_prop_status;
	}

	public void setDevice_prop_status(Map device_prop_status) {
		this.device_prop_status = device_prop_status;
	}
	

	public String getApp_instance() {
		return app_instance;
	}

	public void setApp_instance(String app_instance) {
		this.app_instance = app_instance;
	}



	public enum DeviceOnlineStatus {
		OFFLINE("离线", 0), ONLINE("在线", 1);

		private String name;
		private Integer code;

		DeviceOnlineStatus(String name, Integer code) {
			this.name = name;
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}
	}
	
	public enum DeviceAuthStatus {
		UN_AUTH("未认证", 0), AUTH("已认证", 1);
		
		private String name;
		private Integer code;
		
		DeviceAuthStatus(String name, Integer code) {
			this.name = name;
			this.code = code;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Integer getCode() {
			return code;
		}
		
		public void setCode(Integer code) {
			this.code = code;
		}
	}
}
