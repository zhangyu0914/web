package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年4月24日下午3:53:58
 * Q    Q: 308053847
 * </pre>
 */
public enum AppAccountInterfaceDataStatusEnum {
	
	ENABLED("正常", 0),
	DISABLE("禁用", 1),

	;

	private String name;
	private Integer code;

	AppAccountInterfaceDataStatusEnum(String name, Integer code) {
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
