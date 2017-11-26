package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.UsersRolesFunctionsEntity;

/**
 * <pre>
 * 功   能: 角色权限表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesFunctionsDto  extends BaseDto {

	
	private String tid;//主键
	private String tFunctionsTid;// 权限外键
	private String tRolesTid;// 角色外键


	public UsersRolesFunctionsDto() {
		super();
	}
	
	public UsersRolesFunctionsDto(UsersRolesFunctionsEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setTFunctionsTid(data.getTFunctionsTid());
			this.setTRolesTid(data.getTRolesTid());

		}
	}
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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
