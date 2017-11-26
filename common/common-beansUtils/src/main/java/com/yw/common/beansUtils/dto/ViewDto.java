package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.ViewEntity;

/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
public class ViewDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public ViewDto() {
		super();
	}
	
	public ViewDto(ViewEntity data) {
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
