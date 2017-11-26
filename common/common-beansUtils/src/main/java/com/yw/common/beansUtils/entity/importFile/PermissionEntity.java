package com.yw.common.beansUtils.entity.importFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入应用权限定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class PermissionEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String model_id;// 型号ID
	private List<PermissionCmdEntity> cmds;// 设备功能id列表

	public PermissionEntity() {
		super();
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public List<PermissionCmdEntity> getCmds() {
		return cmds;
	}

	public void setCmds(List<PermissionCmdEntity> cmds) {
		this.cmds = cmds;
	}
	
	public void setCmdsObj(PermissionCmdEntity cmds) {
		if (this.cmds == null) {
			this.cmds = new ArrayList<PermissionCmdEntity>();
		}
		this.cmds.add(cmds);
	}

}
