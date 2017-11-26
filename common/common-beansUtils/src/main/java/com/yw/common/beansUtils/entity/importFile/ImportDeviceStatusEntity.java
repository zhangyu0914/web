package com.yw.common.beansUtils.entity.importFile;

import java.util.Map;

/**
 * <pre>
 * 功       能: 设备状态数据处理
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportDeviceStatusEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String sn;//
	private String model_id;//
	private Integer model_version;//
	private String auth;//
	private String msg_time;//
	private Integer beacon_value_s;//
	private String online;//
	private String[] routers;//
	private Map<String, Map<String, Map<String, String>>> status;//

	/**** 以下是导入模板中不存在的属性定义 *******************************************************************************/

	public ImportDeviceStatusEntity() {
		super();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public Integer getModel_version() {
		return model_version;
	}

	public void setModel_version(Integer model_version) {
		this.model_version = model_version;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getMsg_time() {
		return msg_time;
	}

	public void setMsg_time(String msg_time) {
		this.msg_time = msg_time;
	}

	public Integer getBeacon_value_s() {
		return beacon_value_s;
	}

	public void setBeacon_value_s(Integer beacon_value_s) {
		this.beacon_value_s = beacon_value_s;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String[] getRouters() {
		return routers;
	}

	public void setRouters(String[] routers) {
		this.routers = routers;
	}

	public Map<String, Map<String, Map<String, String>>> getStatus() {
		return status;
	}

	public void setStatus(Map<String, Map<String, Map<String, String>>> status) {
		this.status = status;
	}
}
