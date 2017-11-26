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
public class ImportAppEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// ID
	private String name;// 名称
	private String enterprise_code;// 机构编码
	private List<AppVersionEntity> versions;// 版本

	/****以下是导入模板中不存在的属性定义*******************************************************************************/
	private String instance_id;//
	
	public ImportAppEntity() {
		super();
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


	public String getEnterprise_code() {
		return enterprise_code;
	}

	public void setEnterprise_code(String enterprise_code) {
		this.enterprise_code = enterprise_code;
	}


	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public List<AppVersionEntity> getVersions() {
		return versions;
	}

	public void setVersions(List<AppVersionEntity> versions) {
		this.versions = versions;
	}
	
	public void setVersionsObj(AppVersionEntity versions) {
		if (this.versions == null) {
			this.versions = new ArrayList<AppVersionEntity>();
		}
		this.versions.add(versions);
	}

}
