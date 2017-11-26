package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.dto.ModelEpDto;
import org.hibernate.validator.constraints.Length;

/**
 *<pre>
 * 功       能: 型号流程
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-07 16:36:19
 * Q    Q: 308053847
 *</pre>
 */
public class ModelEpEntity extends BaseEntity {

	private static final long serialVersionUID = -2993649158456L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.MODELEP.MODELNO")
	private String fkModelTid;// 型号
	@Length(min=0, max=100, message = "WEBPLATFORM.MODELEP.PROPID")
	private String propId;// PROFILE_ID
	private Integer ep;// 流程
	@Length(min=0, max=100, message = "WEBPLATFORM.MODELEP.EPNAME")
	private String epName;// 流程名称
	@Length(min = 0, max = 10, message = "WEBPLATFORM.MODELATT.UNIT")
	private String unit;// 单位
	private Integer precision;// 精度
	@Length(min = 0, max = 100, message = "WEBPLATFORM.MODELATT.FLOWS")
	private String flows;// 支持操作

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public ModelEpEntity() {
		super();
	}
	
	/**
	 * @param fkModelTid
	 * @param propId
	 * @param ep
	 */
	public ModelEpEntity(String fkModelTid, String propId, Integer ep) {
		super();
		this.fkModelTid = fkModelTid;
		this.propId = propId;
		this.ep = ep;
	}
	public ModelEpEntity(String tid, String fkModelTid, String propId, Integer ep,
			String epName, String unit, Integer precision, String flows) {
		super();
		this.setTid(tid);
		this.fkModelTid = fkModelTid;
		this.propId = propId;
		this.ep = ep;
		this.epName = epName;
		this.unit = unit;
		this.precision = precision;
		this.flows = flows;
	}

	//TID参数构造方法
	public ModelEpEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public ModelEpEntity(ModelEpDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public String getFkModelTid() {
		return fkModelTid;
	}

	public void setFkModelTid(String fkModelTid) {
		this.fkModelTid = fkModelTid;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getEpName() {
		return epName;
	}

	public void setEpName(String epName) {
		this.epName = epName;
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

	public String getFlows() {
		return flows;
	}

	public void setFlows(String flows) {
		this.flows = flows;
	}

}
