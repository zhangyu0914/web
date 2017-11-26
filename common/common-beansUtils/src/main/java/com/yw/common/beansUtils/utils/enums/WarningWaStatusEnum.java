package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功       能: 报警状态
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年4月5日上午10:03:25
 * Q    Q: 308053847
 * </pre>
 */
public enum WarningWaStatusEnum {
	
	UNREAD("未处理", 0),
	READ("已处理", 1),

	;

	private String name;
	private Integer code;

	WarningWaStatusEnum(String name, Integer code) {
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
