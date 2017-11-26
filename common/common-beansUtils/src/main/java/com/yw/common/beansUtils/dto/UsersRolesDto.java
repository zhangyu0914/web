package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功   能: 角色表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesDto  extends BaseDto {

	
	private String tid;//主键
	private String roleName;// 角色名称
	private String roleCode;// 角色编码
	private String roleDescribe;// 角色描述
	private Integer state;// 所属平台
	private boolean checked; // 默认选中，用于后台表与表关联时，修改功能默认选中数据


	public UsersRolesDto() {
		super();
	}
	
	public UsersRolesDto(UsersRolesEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setRoleName(data.getRoleName());
			this.setRoleCode(data.getRoleCode());
			this.setRoleDescribe(data.getRoleDescribe());
			this.setState(data.getState());
			this.setChecked(data.getChecked());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
}
