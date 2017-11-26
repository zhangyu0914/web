package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: SN列表:设备类型
 * 涉及版本: V2.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-07-13 14:34:22
 * Q    Q: 308053847
 *</pre>
 */
public enum SnListEqTypeEnum {
	
	VIEW("感知","#2F4554", 0),
	GATEWAY("网关","#CA8622", 1),

	;

	private String name;
	private String rgbColor;
	private Integer code;

	SnListEqTypeEnum(String name, String rgbColor, Integer code) {
		this.name = name;
		this.rgbColor = rgbColor;
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
	
	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
