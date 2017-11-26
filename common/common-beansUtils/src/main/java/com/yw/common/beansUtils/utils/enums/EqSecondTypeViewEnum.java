package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum EqSecondTypeViewEnum {
	
	GATEWAY_BABY("婴儿标签","#84a3fc",0),//
	GATEWAY_MOTHER("母亲标签", "#b8e986",1),//
	GATEWAY_ENVIRONMENT("环境标签","#cccccc",2),//
	;

	private String name;
	private String rgbColor;
	private Integer code;

	EqSecondTypeViewEnum(String name, String rgbColor, Integer code) {
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
	
	public static EqSecondTypeViewEnum getTypeName(Integer code) {
		if (code == null) {
			return null;
		}else if (code == GATEWAY_BABY.getCode()) {
			return GATEWAY_BABY;
		}else if (code == GATEWAY_MOTHER.getCode()) {
			return GATEWAY_MOTHER;
		}else if (code == GATEWAY_ENVIRONMENT.getCode()) {
			return GATEWAY_ENVIRONMENT;
		}
		return null;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
}
