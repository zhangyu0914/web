package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;

/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-15 15:09:58
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceAttributeDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public DeviceAttributeDto() {
		super();
	}
	
	public DeviceAttributeDto(DeviceAttributeEntity data) {
		super();

		if (data != null) {
			
		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
}
