package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum MqttDataTypeEnum {
	
	DATA("次数","#3ccdff", 0),

	;

	private String name;
	private Integer code;
	private String rgbColor;//

	MqttDataTypeEnum(String name, String rgbColor, Integer code) {
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
