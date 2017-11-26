package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.List;

import com.yw.common.beansUtils.entity.importFile.PermissionCmdEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionEntity;

/**
 * <pre>
 * 功       能: 导入应用权限定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class PermissionDto {

	private static final long serialVersionUID = -2986347222798L;

	private String model_id;// 型号ID
	private List<PermissionCmdDto> cmds;// 设备功能id列表

	public PermissionDto() {
		super();
	}

	public PermissionDto(PermissionEntity data) {
		if (data != null) {
			this.setModel_id(data.getModel_id());
			
			for (PermissionCmdEntity cmd : data.getCmds()) {
				
				this.setCmdsObj(new PermissionCmdDto(cmd.getEp(),cmd.getProp_id(),cmd.getFlowStr()));
			}
		}
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public List<PermissionCmdDto> getCmds() {
		return cmds;
	}

	public void setCmds(List<PermissionCmdDto> cmds) {
		this.cmds = cmds;
	}
	public void setCmdsObj(PermissionCmdDto cmds) {
		if(this.cmds == null){
			this.cmds = new ArrayList<PermissionCmdDto>();
		}
		this.cmds.add(cmds);
	}

	public void setPermissionCmdObj(PermissionCmdDto cmds) {
		if(this.cmds==null){
			this.cmds=new ArrayList<PermissionCmdDto>();
		}
		this.cmds.add(cmds);
	}
	
}
