package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备表,属性表,设备与型号属性表,许可证表,设备型号与属性表,设备型号表:属性类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum AttributeAttTypeEnum {
	
	STATUS("状态", "0"),
	SETTING("配置", "1"),

	;

	private String name;
	private String code;

	AttributeAttTypeEnum(String name, String code) {
		this.name = name;
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
	
	public static String getTypeName(String code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
