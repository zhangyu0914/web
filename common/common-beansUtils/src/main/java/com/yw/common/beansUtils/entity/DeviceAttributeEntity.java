package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.DeviceAttributeDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-15 15:09:58
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceAttributeEntity extends BaseEntity {

	private static final long serialVersionUID = -2995021197372L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.SN")
	private String sn;// SN
	private String modelNo;// 型号
	private Integer modelVersion;// 
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.EP")
	private Integer ep;// 流程
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.PROPID")
	private String propId;// 功能ID
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.UNIT")
	private String unit;// 单位
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.INFOVALUE")
	private String infoValue;// 值
	@Length(min=0, max=65535, message = "WEBPLATFORM.DEVICEATTRIBUTE.JSONDATA")
	private String jsonData;// JSON数据
	private String batchNumber;// 批次
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEATTRIBUTE.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public DeviceAttributeEntity() {
		super();
	}
	
	//TID参数构造方法
	public DeviceAttributeEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public DeviceAttributeEntity(DeviceAttributeDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public DeviceAttributeEntity(String tid, String sn) {
		super();
		this.setTid(tid);
		this.setSn(sn);
	}

	public DeviceAttributeEntity(String tid, String sn, Integer ep, String propId,
			String unit, String infoValue, String jsonData, String batchNumber) {
		
		super();
		this.setTid(tid);
		this.sn = sn;
		this.ep = ep;
		this.propId = propId;
		this.unit = unit;
		this.infoValue = infoValue;
		this.jsonData = jsonData;
		this.batchNumber = batchNumber;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

}
