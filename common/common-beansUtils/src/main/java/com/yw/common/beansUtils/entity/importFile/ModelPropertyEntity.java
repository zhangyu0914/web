package com.yw.common.beansUtils.entity.importFile;

import java.util.List;

/**
 * <pre>
 * 功       能: 导入模型属性定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ModelPropertyEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String prop_id;// 属性ID
	private String prop_name;// 属性名称
	private List<ModelEpsEntity> eps;//
	private List<ImportModelConfigEntity> config;

	public ModelPropertyEntity() {
		super();
	}

	public String getProp_id() {
		return prop_id;
	}

	public void setProp_id(String prop_id) {
		this.prop_id = prop_id;
	}

	public String getProp_name() {
		return prop_name;
	}

	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}

	public List<ModelEpsEntity> getEps() {
		return eps;
	}

	public void setEps(List<ModelEpsEntity> eps) {
		this.eps = eps;
	}

	public List<ImportModelConfigEntity> getConfig() {
		return config;
	}

	public void setConfig(List<ImportModelConfigEntity> config) {
		this.config = config;
	}
	
}
