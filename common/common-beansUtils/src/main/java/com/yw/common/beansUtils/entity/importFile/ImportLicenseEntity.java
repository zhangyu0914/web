package com.yw.common.beansUtils.entity.importFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入许可证定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportLicenseEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// ID
	private String pid;// 平台ID
	private String desc;// 描述
	private String create_time;// 开始时间
	private String exp_time;// 结束时间
	private List<DevicesEntity> devices;// 设备
	private List<AppsEntity> apps;// 应用

	public ImportLicenseEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getExp_time() {
		return exp_time;
	}

	public void setExp_time(String exp_time) {
		this.exp_time = exp_time;
	}

	public List<DevicesEntity> getDevices() {
		return devices;
	}

	public void setDevices(List<DevicesEntity> devices) {
		this.devices = devices;
	}
	
	public void setDevicesObj(DevicesEntity devices) {
		if (this.devices == null) {
			this.devices = new ArrayList<DevicesEntity>();
		}
		this.devices.add(devices);
	}

	public List<AppsEntity> getApps() {
		return apps;
	}

	public void setApps(List<AppsEntity> apps) {
		this.apps = apps;
	}
	
	public void setAppsObj(AppsEntity apps) {
		if (this.apps == null) {
			this.apps = new ArrayList<AppsEntity>();
		}
		this.apps.add(apps);
	}

}
