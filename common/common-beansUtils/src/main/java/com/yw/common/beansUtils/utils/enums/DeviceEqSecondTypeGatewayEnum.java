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
public enum DeviceEqSecondTypeGatewayEnum {
	
	GATEWAY_READ("阅读器","#84a3fc",0),//
	GATEWAY_AP("物联网AP", "#b8e986",1),//
	GATEWAY_MONITOR("出口监视器","#cccccc",2),//
	;

	private String name;
	private String rgbColor;
	private Integer code;

	DeviceEqSecondTypeGatewayEnum(String name, String rgbColor, Integer code) {
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
	
	public static DeviceEqSecondTypeGatewayEnum getTypeName(Integer code) {
		if (code == null) {
			return null;
		}else if (code == GATEWAY_READ.getCode()) {
			return GATEWAY_READ;
		}else if (code == GATEWAY_AP.getCode()) {
			return GATEWAY_AP;
		}else if (code == GATEWAY_MONITOR.getCode()) {
			return GATEWAY_MONITOR;
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
