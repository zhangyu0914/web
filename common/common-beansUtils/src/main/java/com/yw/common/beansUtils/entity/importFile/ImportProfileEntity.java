package com.yw.common.beansUtils.entity.importFile;


/**
 * <pre>
 * 功       能: 导入PROFILE
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportProfileEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// ID
	private String name;// KEY
	private String lua;// LUA脚本

	public ImportProfileEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLua() {
		return lua;
	}

	public void setLua(String lua) {
		this.lua = lua;
	}

}
