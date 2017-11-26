package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: IO数据:数据编码
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-20 16:49:08
 * Q    Q: 308053847
 *</pre>
 */
public enum IoDataDataCodeEnum {
	
	APP("应用","#969", "0"),
	PLATFORM("平台","#CA8622", "1"),
	DEVICE("设备","#749F83", "2"),

	;

	private String name;
	private String rgbColor;
	private String code;

	IoDataDataCodeEnum(String name, String rgbColor, String code) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
	public static String getTypeName(String code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
