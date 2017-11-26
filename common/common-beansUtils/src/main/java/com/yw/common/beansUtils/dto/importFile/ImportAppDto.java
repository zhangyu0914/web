package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.List;

import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionEntity;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: 导入应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportAppDto {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// 应用ID
	private String name;// 名称
	private Integer version;// 版本
	private String enterprise_code;// 机构编码
	private List<PermissionDto> permission;// 权限

	public ImportAppDto() {
		super();
	}

	public ImportAppDto(ImportAppEntity data) {
		if (data != null) {
			
			this.setId(data.getId());
			this.setName(data.getName());
			if (!StringUtils.isBlank(data.getVersions())) {
				
				this.setVersion(data.getVersions().get(0).getVersion());
				this.setEnterprise_code(data.getEnterprise_code());
				if(data.getVersions().get(0).getPermission() != null){
					for (PermissionEntity permission : data.getVersions().get(0).getPermission()) {
						this.setPermissionObj(new PermissionDto(permission));
					}
				}
			}
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEnterprise_code() {
		return enterprise_code;
	}

	public void setEnterprise_code(String enterprise_code) {
		this.enterprise_code = enterprise_code;
	}

	public List<PermissionDto> getPermission() {
		return permission;
	}

	public void setPermission(List<PermissionDto> permission) {
		this.permission = permission;
	}
	
	public void setPermissionObj(PermissionDto permission) {
		if (this.permission == null) {
			this.permission = new ArrayList<PermissionDto>();
		}
		this.permission.add(permission);
	}

}
