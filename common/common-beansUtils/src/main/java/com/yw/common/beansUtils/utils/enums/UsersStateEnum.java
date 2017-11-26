package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功   能: 用户状态
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午3:56:13
 * Q  Q: 308053847
 * </pre>
 */
public enum UsersStateEnum {
	IS_USE("正常用户", 0), //
	IS_NOT_USE("禁用用户", 1),//
	;

	private String name;
	private Integer code;

	UsersStateEnum(String name, Integer code) {
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
