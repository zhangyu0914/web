package com.yw.common.beansUtils.entity.importFile;

/**
 * <pre>
 * 功       能: 导入模型配置定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class MConfigEntity {

	private static final long serialVersionUID = -2986347222798L;

	private Integer config_id;// 配置ID
	private String config_name;// 配置名称
	private String config_type;// 配置类型
	private String[] flows;// 流程
	private String flowStr;//

	public MConfigEntity() {
		super();
	}

	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}

	public String getConfig_name() {
		return config_name;
	}

	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}

	public String getConfig_type() {
		return config_type;
	}

	public void setConfig_type(String config_type) {
		this.config_type = config_type;
	}

	public String[] getFlows() {
		return flows;
	}

	public void setFlows(String[] flows) {
		this.flows = flows;
	}

	public String getFlowStr() {
		return flowStr;
	}

	public void setFlowStr(String flowStr) {
		this.flowStr = flowStr;
	}

}
