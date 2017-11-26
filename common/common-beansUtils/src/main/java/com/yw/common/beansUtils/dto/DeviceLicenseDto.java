package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;

/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:56:29
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceLicenseDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public DeviceLicenseDto() {
		super();
	}
	
	public DeviceLicenseDto(DeviceLicenseEntity data) {
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
