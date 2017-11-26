package com.yw.common.beansUtils.entity;

import java.util.List;

import javax.validation.constraints.Size;

import com.yw.common.beansUtils.dto.UsersRolesDto;

/**
 * <pre>
 * 功   能: 角色表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesEntity extends BaseEntity {

	private static final long serialVersionUID = -2867634914716L;
	
	@Size(min=0, max=100, message = "角色名称数据长度过长")
	private String roleName;// 角色名称
	@Size(min=0, max=10, message = "角色编码数据长度过长")
	private String roleCode;// 角色编码
	@Size(min=0, max=1000, message = "角色描述数据长度过长")
	private String roleDescribe;// 角色描述
	private Integer state;// 所属平台
	
	/****表中不存在的属性定义在这里*******************************************************************************/
	private List<UsersFunctionsEntity> functionsList; // 此角色下所有权限
	private String functionsIds; // 所有权限ID

	public UsersRolesEntity() {
		super();
	}
	
	public UsersRolesEntity(UsersRolesDto data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setRoleName(data.getRoleName());
			this.setRoleCode(data.getRoleCode());
			this.setRoleDescribe(data.getRoleDescribe());
			this.setState(data.getState());

		}
	}
	
	
	public UsersRolesEntity(String roleName, String functionsIds) {
		super();
		this.roleName = roleName;
		this.functionsIds = functionsIds;
	}

	public UsersRolesEntity(String tid) {
		this.setTid(tid);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<UsersFunctionsEntity> getFunctionsList() {
		return functionsList;
	}

	public void setFunctionsList(List<UsersFunctionsEntity> functionsList) {
		this.functionsList = functionsList;
	}

	public String getFunctionsIds() {
		return functionsIds;
	}

	public void setFunctionsIds(String functionsIds) {
		this.functionsIds = functionsIds;
	}
}
