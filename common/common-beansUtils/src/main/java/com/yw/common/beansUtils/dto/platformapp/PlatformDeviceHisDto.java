package com.yw.common.beansUtils.dto.platformapp;

import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;

/**
 * <pre>
 * 功       能: 平台APP设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 * </pre>
 */
public class PlatformDeviceHisDto{

	private static final long serialVersionUID = -1109795279518681480L;
	private String sn;// SN
	private String model_no;// 型号编号
	private String model_name;// 型号名称
	private Integer device_online_status;// 0:离线，1：在线
	private Integer device_auth_status;// 0:未认证，1：已认证
	private Map device_prop_status;// 设备属性
	
	private Integer page_no;// 第几页开始查
	private Integer page_size;// 每页查多少条
	private Integer total_page=1;// 总页数
	private Integer total_count=0;// 总记录数
	

	public PlatformDeviceHisDto() {
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

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public Integer getTotal_page() {
		try {
			return total_page = (this.total_count + this.page_size -1 )/this.page_size;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total_page;
	}

	public void setTotal_page(Integer total_page) {
		this.total_page = total_page;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	
}
