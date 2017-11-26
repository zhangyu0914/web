package com.yw.common.beansUtils.entity;

import javax.validation.constraints.Size;

import com.yw.common.beansUtils.dto.UsersRolesUsersDto;

/**
 * <pre>
 * 功   能: 角色用户表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesUsersEntity extends BaseEntity {

	private static final long serialVersionUID = -2867634914936L;
	
	@Size(min=0, max=50, message = "用户外键数据长度过长")
	private String tUsersTid;// 用户外键
	@Size(min=0, max=50, message = "角色外键数据长度过长")
	private String tRolesTid;// 角色外键
	
	/****表中不存在的属性定义在这里*******************************************************************************/
	private UserInfoEntity users;
	private UsersRolesEntity roles;

	public UsersRolesUsersEntity() {
		super();
	}
	
	public UsersRolesUsersEntity(UsersRolesUsersDto data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setTUsersTid(data.getTUsersTid());
			this.setTRolesTid(data.getTRolesTid());

		}
	}
	
	public UsersRolesUsersEntity(String tid, String usersTid, String rolesTid) {
		super();
		this.setTid(tid);
		this.setTUsersTid(usersTid);
		this.setTRolesTid(rolesTid);
	}
	
	public String getTUsersTid() {
		return tUsersTid;
	}

	public void setTUsersTid(String tUsersTid) {
		this.tUsersTid = tUsersTid;
	}

	public String getTRolesTid() {
		return tRolesTid;
	}

	public void setTRolesTid(String tRolesTid) {
		this.tRolesTid = tRolesTid;
	}

	public UserInfoEntity getUsers() {
		return users;
	}

	public void setUsers(UserInfoEntity users) {
		this.users = users;
	}

	public UsersRolesEntity getRoles() {
		return roles;
	}

	public void setRoles(UsersRolesEntity roles) {
		this.roles = roles;
	}
}
