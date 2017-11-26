package com.yw.common.beansUtils.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.ModelDto;

/**
 *<pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:42
 * Q    Q: 308053847
 *</pre>
 */
public class ModelEntity extends BaseEntity {

	private static final long serialVersionUID = -2979110844008L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.MODEL.MODELNAME")
	private String modelName;// 型号名称
	@Length(min=0, max=100, message = "WEBPLATFORM.MODEL.MODELNO")
	private String modelNo;// 型号
	private String modelCode;// 型号编码
	private Integer modelVersion;// 
	private Integer modelFlag;// 
	private Integer eqType;// 
	private String tradeCode;// 厂商代码
	@Length(min=0, max=100, message = "WEBPLATFORM.MODEL.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private List<AttributeEntity> attList; // 此型号下所有所有属性
	private String fkModelAttTid;// 型号属性外键ID
	private String tradeName;// 厂商名称
	
	
	//无参构造方法
	public ModelEntity() {
		super();
	}
	
	//TID参数构造方法
	public ModelEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public ModelEntity(String tid, String modelNo) {
		super();
		this.setTid(tid);
		this.modelNo = modelNo;
	}

	public ModelEntity(String tid, String fkDeviceLicenseTid, String modelNo) {
		super();
		this.setTid(tid);
		this.modelNo = modelNo;
	}
	
	

	public ModelEntity(String tid, String modelName, String modelNo, Integer modelVersion,
			String tradeCode) {
		super();
		
		this.setTid(tid);
		this.modelName = modelName;
		this.modelNo = modelNo;
		this.modelVersion = modelVersion;
		this.tradeCode = tradeCode;
	}

	public ModelEntity(ModelDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public ModelEntity(String modelNo, Integer modelVersion) {
		this.setModelNo(modelNo);
		this.setModelVersion(modelVersion);
	}
	

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<AttributeEntity> getAttList() {
		return attList;
	}

	public void setAttList(List<AttributeEntity> attList) {
		this.attList = attList;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

	public String getFkModelAttTid() {
		return fkModelAttTid;
	}

	public void setFkModelAttTid(String fkModelAttTid) {
		this.fkModelAttTid = fkModelAttTid;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public Integer getModelFlag() {
		return modelFlag;
	}

	public void setModelFlag(Integer modelFlag) {
		this.modelFlag = modelFlag;
	}

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}

}
