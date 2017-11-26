package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功   能: REDIS 类型
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午3:56:13
 * Q  Q: 308053847
 * </pre>
 */
public enum RedisTypeEnum {
	USERS("用户数据", "USERS_HSET"), 
	TOKEN("TOKEN数据", "TOKEN"), 
	SYSCONFIGURATION("配置表数据", "SYSCONFIGURATION"),
	INTERFACE("接口", "INTERFACE"),
	NOTIFY("基础数据","notify"),
	CONFIG_UPDATE("基础配置数据","CONFIG_UPDATE")
	;

	private String name;
	private String code;

	RedisTypeEnum(String name, String code) {
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
		if (code == null || code.equals("")) {
			return null;
		}
		if (code.equals(USERS.getCode())) {
			return USERS.getName();
		}else if (code.equals(TOKEN.getCode())) {
			return TOKEN.getName();
		}
		return null;
	}
}