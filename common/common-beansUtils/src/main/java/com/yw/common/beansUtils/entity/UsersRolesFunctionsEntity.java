package com.yw.common.beansUtils.entity;

import javax.validation.constraints.Size;

import com.yw.common.beansUtils.dto.UsersRolesFunctionsDto;

/**
 * <pre>
 * 功   能: 角色权限表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesFunctionsEntity extends BaseEntity {

	private static final long serialVersionUID = -2867634914820L;
	
	@Size(min=0, max=100, message = "权限外键数据长度过长")
	private String tFunctionsTid;// 权限外键
	@Size(min=0, max=100, message = "角色外键数据长度过长")
	private String tRolesTid;// 角色外键

	public UsersRolesFunctionsEntity() {
		super();
	}
	
	public UsersRolesFunctionsEntity(UsersRolesFunctionsDto data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setTFunctionsTid(data.getTFunctionsTid());
			this.setTRolesTid(data.getTRolesTid());

		}
	}
	
	public UsersRolesFunctionsEntity(String tFunctionsTid, String tRolesTid) {
		super();
		this.tFunctionsTid = tFunctionsTid;
		this.tRolesTid = tRolesTid;
	}

	public String getTFunctionsTid() {
		return tFunctionsTid;
	}

	public void setTFunctionsTid(String tFunctionsTid) {
		this.tFunctionsTid = tFunctionsTid;
	}

	public String getTRolesTid() {
		return tRolesTid;
	}

	public void setTRolesTid(String tRolesTid) {
		this.tRolesTid = tRolesTid;
	}

}
