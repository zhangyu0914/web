package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 用户注册登录日志表:访问类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 17:45:14
 * Q    Q: 308053847
 *</pre>
 */
public enum UserLogVisitTypeEnum {
	
	THE_LOGIN("登录", 0),
	QUIT("退出", 1),

	;

	private String name;
	private Integer code;

	UserLogVisitTypeEnum(String name, Integer code) {
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
