package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.entity.importFile.AppsEntity;
import com.yw.common.beansUtils.entity.importFile.DevicesEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功       能: 导入许可证定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportLicenseDto {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// ID
	private String pid;// 平台ID
	private String desc;// 描述
	private String create_time;// 开始时间
	private String exp_time;// 结束时间
	private List<DevicesDto> devices;// 设备
	private List<AppsDto> apps;// 应用

	public ImportLicenseDto() {
		super();
	}

	public ImportLicenseDto(ImportLicenseEntity data) {

		if (data != null) {
			this.setId(data.getId());
			this.setPid(data.getPid());
			this.setDesc(data.getDesc());
			try {
				this.setCreate_time(DateUtils.formatStr(data.getCreate_time(), DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS));
				this.setExp_time(DateUtils.formatStr(data.getExp_time(), DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (data.getDevices() != null) {
				
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				for (DevicesEntity devices : data.getDevices()) {

					if (map.containsKey(devices.getModel_id())) {
						List<String> list = map.get(devices.getModel_id());
						list.add(devices.getSnStr());
						map.put(devices.getModel_id(), list);
					} else {
						List<String> list = new ArrayList<String>();
						list.add(devices.getSnStr());
						map.put(devices.getModel_id(), list);
					}
				}
				String tempKey = "";
				for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
					tempKey = iterator.next();
					this.setDevices(new DevicesDto(tempKey, map.get(tempKey)));
				}
			}
			if (data.getApps() != null) {
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				for(AppsEntity apps : data.getApps()){
					
					if(map.containsKey(apps.getApp_id())){
						List<String> list = map.get(apps.getApp_id());
						list.add(apps.getLic_ids_str());
						map.put(apps.getApp_id(), list);
					}else{
						List<String> list = new ArrayList<String>();
						list.add(apps.getLic_ids_str());
						map.put(apps.getApp_id(), list);
					}
				}
				String tempKey = "";
				for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
					
					tempKey = iterator.next();
					this.setAppsObj(new AppsDto(tempKey, map.get(tempKey)));
				}
			}
		}
	
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

	public List<DevicesDto> getDevices() {
		return devices;
	}

	public void setDevices(List<DevicesDto> devices) {
		this.devices = devices;
	}
	
	public void setDevices(DevicesDto devices) {
		if (this.devices == null) {
			this.devices = new ArrayList<DevicesDto>();
		}
		this.devices.add(devices);
	}

	public List<AppsDto> getApps() {
		return apps;
	}

	public ImportLicenseDto setApps(List<AppsDto> apps) {
		this.apps = apps;
		return this;
	}
	
	public void setAppsObj(AppsDto apps) {
		if (this.apps == null) {
			this.apps = new ArrayList<AppsDto>();
		}
		this.apps.add(apps);
	}

}
