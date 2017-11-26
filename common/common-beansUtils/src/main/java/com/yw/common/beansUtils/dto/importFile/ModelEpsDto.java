package com.yw.common.beansUtils.dto.importFile;

import com.yw.common.beansUtils.entity.importFile.ModelEpsEntity;


/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ModelEpsDto {

	private static final long serialVersionUID = -2986347222798L;

	private Integer ep;//流程
	private String name;//流程名称
	private String unit;// //单位
	private Integer precision;// 精度
	private String[] flows;// 选项

	public ModelEpsDto() {
		super();
	}

	public ModelEpsDto(ModelEpsEntity data) {
		if (data != null) {

			this.setEp(data.getEp());
			this.setName(data.getName());
			this.setUnit(data.getUnit());
			this.setPrecision(data.getPrecision());
			if (data.getFlowsStr() != null) {
				this.setFlows(data.getFlowsStr().split(","));
			}
		}
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
}
