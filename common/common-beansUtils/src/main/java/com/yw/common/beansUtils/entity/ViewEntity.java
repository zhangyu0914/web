package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.ViewDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
public class ViewEntity extends BaseEntity {

	private static final long serialVersionUID = -2988467870278L;
	

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public ViewEntity() {
		super();
	}
	
	//TID参数构造方法
	public ViewEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public ViewEntity(ViewDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
}
