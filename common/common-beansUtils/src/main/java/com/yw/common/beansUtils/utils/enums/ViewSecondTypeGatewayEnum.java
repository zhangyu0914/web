package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备网关类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum ViewSecondTypeGatewayEnum {
	
	GATEWAY_READ("正常应用","#50e3c2",0),//
	GATEWAY_AP("异常应用", "#ff7c7c",1),//
	;

	private String name;
	private String rgbColor;
	private Integer code;

	ViewSecondTypeGatewayEnum(String name, String rgbColor, Integer code) {
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
	
	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}else if (code == GATEWAY_READ.getCode()) {
			return GATEWAY_READ.getName();
		}else if (code == GATEWAY_AP.getCode()) {
			return GATEWAY_AP.getName();
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
