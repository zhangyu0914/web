package com.yw.common.beansUtils.dto;

import java.util.Arrays;
import java.util.List;

import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppInstanceDevEntity;

/**
 * <pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 * </pre>
 */
public class AppInstanceDevDto {

	private static final long serialVersionUID = -2979110495220L;

	private String instance_id;//
	private List<String> sn_list;

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V1.0.0版本 *******************************************************************************/

	// 无参构造方法
	public AppInstanceDevDto() {
		super();
	}

	public AppInstanceDevDto(AppInstanceDevEntity data) {
		if (data != null) {
			this.setInstance_id(data.getInstance_id());
			this.setSn_list(data.getSn_list());
		}
	}

	public AppInstanceDevDto(AppDeviceEntity data) {
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

}
