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
public enum DeviceLicenseCountEnum {
	
	USE_COUNT("已使用","#b8e986", 0),
	UNUSE_COUNT("未使用","#4bd8ff", 1),

	;

	private String name;
	private Integer code;
	private String rgbColor;//

	DeviceLicenseCountEnum(String name, String rgbColor, Integer code) {
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
