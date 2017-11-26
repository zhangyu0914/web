package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String sn;// SN
	private String orgName;// 型号编号
	private String modelNo;// 型号编号
	private String modelCode;//
	private String modelName;// 型号名称
	private String eqName;// 设备名称
	private Integer eqStatus;// 设备状态
	private String deviceType;// 设备类型
	private String eqIcon;// 设备图标
	
	private String fkDeviceModelAttTid;//设备与型号属性ID
	private String attName;// 属性名称
	private String attType;// 属性类型
	private String attValue;// 属性值
	private String valueType;// 值类型
	private String valueDemand;//值定义 
	private String valueRegex;//值的正则
	private String bindingTime;//绑定时间
	private String tradeName;//厂商名称
	private String tradeCode;//厂商名称
	private Integer modelVersion;//型号版本号
	private Integer eqType;//
	
	public DeviceDto() {
		super();
	}
	
	public DeviceDto(DeviceEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setSn(data.getSn());
			this.setOrgName(data.getOrgName());
			this.setModelName(data.getModelName());
			this.setModelNo(data.getModelNo());
			this.setModelCode(data.getModelCode());
			this.setEqName(data.getEqName());
			this.setEqStatus(data.getEqStatus());
			this.setDeviceType(data.getDeviceType());
			this.setEqType(data.getEqType());
			this.setEqIcon(data.getEqIcon());
			
			this.setFkDeviceModelAttTid(data.getFkDeviceModelAttTid());
			this.setAttName(data.getAttName());
			this.setAttType(data.getAttType());
			this.setAttValue(data.getAttValue());
			this.setValueType(data.getValueType());
			this.setValueDemand(data.getValueDemand());
			this.setValueRegex(data.getValueRegex());
			this.setTradeCode(data.getTradeCode());
			this.setTradeName(data.getTradeName());
			this.setModelVersion(data.getModelVersion());
			try {
				this.setBindingTime(DateUtils.formatString(data.getBindingTime(), null));
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
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

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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

	public String getValueRegex() {
		return valueRegex;
	}

	public void setValueRegex(String valueRegex) {
		this.valueRegex = valueRegex;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
