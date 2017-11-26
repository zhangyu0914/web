package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.AppFunctionEntity;

/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-23 14:54:23
 * Q    Q: 308053847
 *</pre>
 */
public class AppFunctionDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public AppFunctionDto() {
		super();
	}
	
	public AppFunctionDto(AppFunctionEntity data) {
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
