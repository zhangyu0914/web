package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功       能: 账号状态;0,0:正常:ENABLED,1:禁用:DISABLED;
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年4月21日下午3:58:49
 * Q    Q: 308053847
 * </pre>
 */
public enum AppAccountStatusEnum {
	
	ENABLED("正常", 0),
	DISABLED("禁用", 1)

	;

	private String name;
	private Integer code;

	AppAccountStatusEnum(String name, Integer code) {
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
	
	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
