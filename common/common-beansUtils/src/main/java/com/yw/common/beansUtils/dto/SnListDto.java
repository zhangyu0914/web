package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;

import com.yw.common.beansUtils.entity.SnListEntity;

/**
 *<pre>
 * 功       能: SN列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-07-13 14:34:22
 * Q    Q: 308053847
 *</pre>
 */
public class SnListDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public SnListDto() {
		super();
	}
	
	public SnListDto(SnListEntity data) {
		super();

		if (data != null) {
			
			//this.setTid(data.getTid());
			//this.setDeviceType(data.getDeviceType());
			//this.setEqName(data.getEqName());
			//this.setEqType(data.getEqType());
			//this.setModelNo(data.getModelNo());

		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
}
