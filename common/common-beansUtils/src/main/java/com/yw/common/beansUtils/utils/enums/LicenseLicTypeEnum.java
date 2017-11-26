package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 许可证表:许可证类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-16 12:51:50
 * Q    Q: 308053847
 *</pre>
 */
public enum LicenseLicTypeEnum {
	
	DEVICE_SN("设备SN", 0),
	APP_NO("应用", 1),

	;

	private String name;
	private Integer code;

	LicenseLicTypeEnum(String name, Integer code) {
		this.name = name;
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
		}
		return null;
	}
}
