package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: 导入应用权限定义
 * 设计版本: V2.0.0
 * 创  建  者: zhangyu
 * 日       期: 2017年5月24日 下午6:32:34
 * Q     Q: 982234234
 * </pre>
 */
public class PermissionCmdDto {

	private Integer ep;// 选项
	private String prop_id;// 功能ID
	private List<String> flow;// 选项

	public PermissionCmdDto() {
		super();
	}

	public PermissionCmdDto(Integer ep, String prop_id, String flowStr) {
		super();
		this.ep = ep;
		this.prop_id = prop_id;
		if (!StringUtils.isBlank(flowStr)) {
			this.flow = Arrays.asList(flowStr.split(","));
		}else{
			this.flow = new ArrayList<String>();
		}
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

}
