package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备使用数量
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum AppLicenseCountEnum {
	
	USE_DAY_COUNT("已使用天数","#ffe07d", 0),
	RESIDUE_DAY_COUNT("剩余天数","#4bd8ff", 1),
	OVER("已过期","red", 1),
	;

	private String name;
	private Integer code;
	private String rgbColor;//

	AppLicenseCountEnum(String name, String rgbColor, Integer code) {
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
