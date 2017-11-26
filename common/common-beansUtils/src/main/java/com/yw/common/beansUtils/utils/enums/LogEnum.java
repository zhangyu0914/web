package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功   能: 导医数据是否可见
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午3:56:13
 * Q  Q: 308053847
 * </pre>
 */
public enum LogEnum {
	
	IP("IP","IP"),//接口分组
	INTERFACEGROUPNO("INTERFACEGROUPNO","INTERFACEGROUPNO"),//接口分组
	INTERFACEGROUPNAME("INTERFACEGROUPNAME","INTERFACEGROUPNAME"),//接口分组名称
	INTERFACENO("INTERFACENO","INTERFACENO"),//接口编号
	USERTID("USERTID","USERTID"),//用户ID
	USERNAME("USERNAME","USERNAME")//登录名
	
	;

	private String name;
	private String code;

	LogEnum(String name, String code) {
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
}
