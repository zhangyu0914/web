package com.yw.common.beansUtils.entity.importFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入模型定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportModelEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// 唯一标示
	private String name;// 名称
	private Integer flag;// 标签：0：非标签类设备，1：标签类设备，（设备配置界面需要展示）
	private List<String> tags;// sensor:感知, gateway:网关
	private String enterprise_code;// 机构编码
	private List<ModelVersionEntity> versions;// 版本
	
	/****以下是导入模板中不存在的属性定义*******************************************************************************/
	private Integer version;// 版本:查询使用

	public ImportModelEntity() {
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


	public List<ModelVersionEntity> getVersions() {
		return versions;
	}

	public void setVersions(List<ModelVersionEntity> versions) {
		this.versions = versions;
	}
	
	public void setVersionsObj(ModelVersionEntity versions) {
		if (this.versions == null) {
			this.versions = new ArrayList<ModelVersionEntity>();
		}
		this.versions.add(versions);
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
