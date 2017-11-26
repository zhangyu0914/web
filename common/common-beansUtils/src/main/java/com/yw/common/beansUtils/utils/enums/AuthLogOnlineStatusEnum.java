package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 认证记录:认证状态
 * 涉及版本: V2.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 *</pre>
 */
public enum AuthLogOnlineStatusEnum {
	
	ONLINE("在线","#9CF", 0),
	OFFLINE("离线","#9CF", 1),

	;

	private String name;
	private String rgbColor;
	private Integer code;

	AuthLogOnlineStatusEnum(String name, String rgbColor, Integer code) {
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
