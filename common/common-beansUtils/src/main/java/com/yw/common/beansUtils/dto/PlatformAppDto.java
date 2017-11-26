package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.PlatformAppEntity;

/**
 *<pre>
 * 功       能: 第三方应用数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-23 12:25:32
 * Q    Q: 308053847
 *</pre>
 */
public class PlatformAppDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public PlatformAppDto() {
		super();
	}
	
	public PlatformAppDto(PlatformAppEntity data) {
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
