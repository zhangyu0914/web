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
public enum DeviceEqStatusEnum {
	
	NORMAL("正常","#84a3fc", 0),
	OFFLINE("离线","#b8e986", 1),
	DISCONNECT("异常","", 2),

	;

	private String name;
	private String rpgColor;//
	private Integer code;

	DeviceEqStatusEnum(String name, String rpgColor, Integer code) {
		this.name = name;
		this.rpgColor = rpgColor;
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
	
	public String getRpgColor() {
		return rpgColor;
	}

	public void setRpgColor(String rpgColor) {
		this.rpgColor = rpgColor;
	}

	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}
		return null;
	}
	
}
