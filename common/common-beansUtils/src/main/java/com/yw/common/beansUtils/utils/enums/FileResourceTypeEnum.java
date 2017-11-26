package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功   能: 文件资源类型
 * 创建者: Vickey
 * 日   期: 2014-8-27下午3:56:13
 * Q  Q: 308053847
 * </pre>
 */
public enum FileResourceTypeEnum {
	UNKNOW("未知",-1),
	IMG("图片", 1), 
	VIDEO("视频", 2),
	FILE("文件", 3),
	;

	private String name;
	private int code;

	FileResourceTypeEnum(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
