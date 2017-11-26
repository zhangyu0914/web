package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 用户状态
 * 涉及版本: V3.0.0
 * 创  建  者: Vickey
 * 日       期: 2015-10-30 11:26:01
 * Q    Q: 308053847
 *</pre>
 */
public enum UserInfoUserStateEnum {
	
	ENABLED("启用", 0),
	DISABLED("禁用", 1),
	;

	private String name;
	private Integer code;

	UserInfoUserStateEnum(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
