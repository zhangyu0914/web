package com.yw.common.beansUtils.entity.importFile;

import java.util.Arrays;
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
public class PermissionCmdEntity {

	private static final long serialVersionUID = -2986347222798L;

	private Integer ep;// 选项
	private String prop_id;// 功能ID
	private String flowStr;// 选项
	private List<String> flow;// 选项

	public PermissionCmdEntity() {
		super();
	}

	public PermissionCmdEntity(Integer ep, String prop_id, String... flow) {
		super();
		this.ep = ep;
		this.prop_id = prop_id;
		this.flow = Arrays.asList(flow);
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getProp_id() {
		return prop_id;
	}

	public void setProp_id(String prop_id) {
		this.prop_id = prop_id;
	}

	public List<String> getFlow() {
		return flow;
	}

	public void setFlow(List<String> flow) {
		this.flow = flow;
	}

	public String getFlowStr() {
		return flowStr;
	}

	public void setFlowStr(String flowStr) {
		this.flowStr = flowStr;
	}
}
