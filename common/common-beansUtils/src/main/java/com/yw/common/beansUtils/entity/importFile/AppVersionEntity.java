package com.yw.common.beansUtils.entity.importFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class AppVersionEntity {

	private static final long serialVersionUID = -2986347222798L;

	private Integer version;// 版本
	private List<PermissionEntity> permission;// 权限

	public AppVersionEntity() {
		super();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<PermissionEntity> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionEntity> permission) {
		this.permission = permission;
	}
	
	public void setPermissionObj(PermissionEntity permission) {
		if (this.permission == null) {
			this.permission = new ArrayList<PermissionEntity>();
		}
		this.permission.add(permission);
	}

}
