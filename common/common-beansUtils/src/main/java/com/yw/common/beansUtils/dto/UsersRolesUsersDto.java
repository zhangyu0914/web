package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.UsersRolesUsersEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功   能: 角色用户表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersRolesUsersDto  extends BaseDto {

	
	private String tid;//主键
	private String tUsersTid;// 用户外键
	private String tRolesTid;// 角色外键


	public UsersRolesUsersDto() {
		super();
	}
	
	public UsersRolesUsersDto(UsersRolesUsersEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setTUsersTid(data.getTUsersTid());
			this.setTRolesTid(data.getTRolesTid());
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

	
}
