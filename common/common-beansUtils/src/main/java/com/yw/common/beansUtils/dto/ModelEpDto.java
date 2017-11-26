package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.ModelEpEntity;

/**
 *<pre>
 * 功       能: 型号流程
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-07 16:36:19
 * Q    Q: 308053847
 *</pre>
 */
public class ModelEpDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public ModelEpDto() {
		super();
	}
	
	public ModelEpDto(ModelEpEntity data) {
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
