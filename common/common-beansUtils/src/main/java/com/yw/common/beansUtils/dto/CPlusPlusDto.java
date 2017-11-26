package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.CPlusPlusEntity;

/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 *</pre>
 */
public class CPlusPlusDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public CPlusPlusDto() {
		super();
	}
	
	public CPlusPlusDto(CPlusPlusEntity data) {
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
