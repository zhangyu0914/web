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
public enum AppStatusEnum {
	
	APP_NORMAL("正常应用","#b8e986", 0),
	APP_ERROR("异常应用","#84a3fc", 1),
	APP_UNKOW("未知","#cccccc", 2),

	;

	private String name;
	private Integer code;
	private String rgbColor;//

	AppStatusEnum(String name, String rgbColor, Integer code) {
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
		}else if(code == APP_NORMAL.getCode()){
			return APP_NORMAL.getName();
		}else if(code == APP_ERROR.getCode()){
			return APP_ERROR.getName();
		}else if(code == APP_UNKOW.getCode()){
			return APP_UNKOW.getName();
		}
		return null;
	}
}
