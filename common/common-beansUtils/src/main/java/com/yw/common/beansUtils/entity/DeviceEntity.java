package com.yw.common.beansUtils.entity;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.DeviceDto;

/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceEntity extends BaseEntity {

	private static final long serialVersionUID = -2979110842446L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICE.FKMODELTID")
	private String fkModelTid;// 型号外键ID
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICE.FKSNLICENCETID")
	private String fkSnLicenceTid;// SN许可证外键ID
	private String fkOrganizationTid;// 机构外键ID
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICE.SN")
	private String sn;// SN
	private String responseData;// SN
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICE.EQNAME")
	private String eqName;// 设备名称
	private Integer eqStatus;// 设备状态
	private String deviceType;// 设备类型
	private String eqIcon;//设备图标
	private String modelNo;// SN

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String fkDeviceModelAttTid;//
	private String fkAppTid;//
	private String modelName;// SN
	private String modelCode;// 
	private String orgName;// SN
	private String attName;// 
	private String attType;// 
	private String attValue;// 
	private String valueType;// 
	private String valueDemand;// 
	private String valueRegex;//值的正则
	private String bindingTime;//绑定时间
	private String tradeName;//厂商名称
	private String tradeCode;//厂商名称
	private Integer modelVersion;//型号版本号
	private Integer eqType;//
	
	//无参构造方法
	public DeviceEntity() {
		super();
	}
	
	//TID参数构造方法
	public DeviceEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public DeviceEntity(String tid, Integer eqStatus, Integer eqType) {
		super();
		this.setTid(tid);
		this.eqStatus = eqStatus;
		this.eqType = eqType;
	}

	
	public DeviceEntity(String sn, Integer eqStatus) {
		super();
		this.sn = sn;
		this.eqStatus = eqStatus;
	}

	public DeviceEntity(DeviceDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public DeviceEntity(String tid, String fkSnLicenceTid) {
		this.setTid(tid);
		this.setFkSnLicenceTid(fkSnLicenceTid);
	}
	
	public DeviceEntity(String tid, String fkSnLicenceTid, String sn) {
		this.setTid(tid);
		this.setFkSnLicenceTid(fkSnLicenceTid);
		this.setSn(sn);
	}
	
	public String getFkModelTid() {
		return fkModelTid;
	}

	public void setFkModelTid(String fkModelTid) {
		this.fkModelTid = fkModelTid;
	}

	public String getFkSnLicenceTid() {
		return fkSnLicenceTid;
	}

	public void setFkSnLicenceTid(String fkSnLicenceTid) {
		this.fkSnLicenceTid = fkSnLicenceTid;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public Integer getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(Integer eqStatus) {
		this.eqStatus = eqStatus;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getFkOrganizationTid() {
		return fkOrganizationTid;
	}

	public void setFkOrganizationTid(String fkOrganizationTid) {
		this.fkOrganizationTid = fkOrganizationTid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValueDemand() {
		return valueDemand;
	}

	public void setValueDemand(String valueDemand) {
		this.valueDemand = valueDemand;
	}

	public String getAttValue() {
		return attValue;
	}

	public void setAttValue(String attValue) {
		this.attValue = attValue;
	}

	public String getFkDeviceModelAttTid() {
		return fkDeviceModelAttTid;
	}

	public void setFkDeviceModelAttTid(String fkDeviceModelAttTid) {
		this.fkDeviceModelAttTid = fkDeviceModelAttTid;
	}

	public String getEqIcon() {
		return eqIcon;
	}

	public void setEqIcon(String eqIcon) {
		this.eqIcon = eqIcon;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getValueRegex() {
		return valueRegex;
	}

	public void setValueRegex(String valueRegex) {
		this.valueRegex = valueRegex;
	}

	public String getFkAppTid() {
		return fkAppTid;
	}

	public void setFkAppTid(String fkAppTid) {
		this.fkAppTid = fkAppTid;
	}

	public String getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(String bindingTime) {
		this.bindingTime = bindingTime;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}

}
