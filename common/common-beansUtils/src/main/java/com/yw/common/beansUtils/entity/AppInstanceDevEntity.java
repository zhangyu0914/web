package com.yw.common.beansUtils.entity;

import java.util.Arrays;
import java.util.List;

import com.yw.common.beansUtils.entity.AppDeviceEntity;

/**
 * <pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 * </pre>
 */
public class AppInstanceDevEntity {

	private static final long serialVersionUID = -2979110495220L;

	private String instance_id;//
	private List<String> sn_list;

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V1.0.0版本 *******************************************************************************/
	private String id;
	
	// 无参构造方法
	public AppInstanceDevEntity() {
		super();
	}

	public AppInstanceDevEntity(String instance_id, List<String> sn_list) {
		super();
		this.instance_id = instance_id;
		this.sn_list = sn_list;
	}

	public AppInstanceDevEntity(AppDeviceEntity data) {
		if (data != null) {
			
		}
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public List<String> getSn_list() {
		return sn_list;
	}

	public void setSn_list(List<String> sn_list) {
		this.sn_list = sn_list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
