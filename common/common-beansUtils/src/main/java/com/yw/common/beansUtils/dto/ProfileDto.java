package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.AttributeEntity;

/**
 * <pre>
 * 功       能: PROFILE
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 * </pre>
 */
public class ProfileDto {

	private static final long serialVersionUID = -2979110842720L;

	private String id;// PROFILE_ID
	private String lua_script;// 属性名称

	// 无参构造方法
	public ProfileDto() {
		super();
	}

	public ProfileDto(AttributeEntity data) {
		super();

		if (data != null) {
			this.setId(data.getProfileId());
			this.setLua_script(data.getRemark());
		}
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLua_script() {
		return lua_script;
	}

	public void setLua_script(String lua_script) {
		this.lua_script = lua_script;
	}

}
