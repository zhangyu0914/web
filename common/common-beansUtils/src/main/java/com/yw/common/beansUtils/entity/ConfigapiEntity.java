package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.ConfigapiDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13:01:21
 * Q    Q: 308053847
 *</pre>
 */
public class ConfigapiEntity extends BaseEntity {

	private static final long serialVersionUID = -2991204163498L;
	

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public ConfigapiEntity() {
		super();
	}
	
	//TID参数构造方法
	public ConfigapiEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public ConfigapiEntity(ConfigapiDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
}
