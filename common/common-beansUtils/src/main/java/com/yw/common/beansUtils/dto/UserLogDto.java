package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.UserLogEntity;

/**
 *<pre>
 * 功       能: 用户注册登录日志表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 17:45:14
 * Q    Q: 308053847
 *</pre>
 */
public class UserLogDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键

	public UserLogDto() {
		super();
	}
	
	public UserLogDto(UserLogEntity data) {
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
