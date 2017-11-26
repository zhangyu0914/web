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
public class ModelVersionEntity {

	private static final long serialVersionUID = -2986347222798L;

	private Integer version;// 版本
	private List<ModelPropertyEntity> property;// 功能
	private List<MConfigEntity> config;// 配置项

	public ModelVersionEntity() {
		super();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<ModelPropertyEntity> getProperty() {
		return property;
	}

	public void setProperty(List<ModelPropertyEntity> property) {
		this.property = property;
	}
	
	public void setPropertyObj(ModelPropertyEntity property) {
		if (this.property == null) {
			this.property = new ArrayList<ModelPropertyEntity>();
		}
		this.property.add(property);
	}

	public List<MConfigEntity> getConfig() {
		return config;
	}

	public void setConfig(List<MConfigEntity> config) {
		this.config = config;
	}
	
	public void setConfigObj(MConfigEntity config) {
		if (this.config == null) {
			this.config = new ArrayList<MConfigEntity>();
		}
		this.config.add(config);
	}
}
