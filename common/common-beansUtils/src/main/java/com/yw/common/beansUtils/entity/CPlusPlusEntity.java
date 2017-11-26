package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.dto.CPlusPlusDto;

/**
 *<pre>
 * 功       能: C++接口
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 *</pre>
 */
public class CPlusPlusEntity extends BaseEntity {

	private static final long serialVersionUID = -2978831570220L;
	
	
	//无参构造方法
	public CPlusPlusEntity() {
		super();
	}
	
	//TID参数构造方法
	public CPlusPlusEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public CPlusPlusEntity(CPlusPlusDto data) {
		super();

		if (data != null) {
			
		}
	}

}
