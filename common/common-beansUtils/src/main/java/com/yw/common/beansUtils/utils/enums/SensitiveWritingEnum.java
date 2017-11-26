package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功   能: 过敏词匹配规则
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午3:56:13
 * Q  Q: 308053847
 * </pre>
 */
public enum SensitiveWritingEnum {
	
	MIN_MATCHING("最小匹配规则", 1), 
	Max_MATCHING("最大匹配规则", 2), 
	
	;

	private String name;
	private int code;

	SensitiveWritingEnum(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}