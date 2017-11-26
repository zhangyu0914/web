package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备表,属性表,设备与型号属性表,许可证表,设备型号与属性表,设备型号表:设备状态
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum ViewAppIOTypeEnum {
	
	INPUT("输入","rgb(175, 175, 255)", 0),
	OUTPUT("输出","rgb(173, 246, 239)", 1),

	;

	private String name;
	private Integer code;
	private String rgbColor;//

	ViewAppIOTypeEnum(String name, String rgbColor, Integer code) {
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
		}else if(code == INPUT.getCode()){
			return INPUT.getName();
		}else if(code == OUTPUT.getCode()){
			return OUTPUT.getName();
		}
		return null;
	}
}
