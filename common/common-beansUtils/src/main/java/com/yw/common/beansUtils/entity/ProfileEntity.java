package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.dto.AttributeDto;

/**
 * <pre>
 * 功       能: PROFILE
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 * </pre>
 */
public class ProfileEntity {

	private static final long serialVersionUID = -2979110842720L;

	private String id;// PROFILE_ID
	private String name;// 属性名称

	// 无参构造方法
	public ProfileEntity() {
		super();
	}

	public ProfileEntity(AttributeDto data) {
		super();

		if (data != null) {

		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
