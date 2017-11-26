package com.yw.common.beansUtils.entity.importFile;


/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ModelEpsEntity {

	private static final long serialVersionUID = -2986347222798L;

	private Integer ep;//流程
	private String name;//流程名称
	private String unit;// //单位
	private Integer precision;// 精度
	private String[] flows;// 选项
	private String flowsStr;// 选项

	public ModelEpsEntity() {
		super();
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public String[] getFlows() {
		return flows;
	}

	public void setFlows(String[] flows) {
		this.flows = flows;
	}

	public String getFlowsStr() {
		return flowsStr;
	}

	public void setFlowsStr(String flowsStr) {
		this.flowsStr = flowsStr;
	}
}
