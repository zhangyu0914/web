package com.yw.common.beansUtils.entity;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppDto;

/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 *</pre>
 */
public class AppEntity extends BaseEntity {

	private static final long serialVersionUID = -2978831570220L;
	
	private String appId;// 应用ID
	@Length(min=0, max=30, message = "WEBPLATFORM.APP.APPNAME")
	private String appName;// 应用名称
	private Integer appVersion;// 应用版本
	private String orgCode;// 机构编码
	@Length(min=0, max=65535, message = "WEBPLATFORM.APP.ICON")
	private String icon;// 图标URL
	private Integer appStatus;// 应用状态

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String dataCode;
	private String eqName;
	private String sn;
	private Integer bindingCount;//绑定设备数量
	private String attType;//属性类型
	private String attName;//属性名称
	private String function;//权限
	private String modelNo;//型号
	private String modelCode;//厂商设备型号
	private String modelName;//型号
	private String tradeName;//厂商名称
	private String tradeCode;//厂商代码
	private Integer inputData;
	private Integer outputData;
	private String dateHour;//
	private String orgName;//机构名称
	private Integer appCount;//应用数量
	private Integer modelVersion;//型号版本号
	
	private String flow;
	private String ep;
	
	//无参构造方法
	public AppEntity() {
		super();
	}
	
	//TID参数构造方法
	public AppEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AppEntity(String tid, String appId) {
		super();
		this.setTid(tid);
		this.setAppId(appId);
	}
	
	public AppEntity(AppDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public AppEntity(String tid, String appId, String appName, Integer appVersion,
			String orgCode) {
		this.setTid(tid);
		this.setAppId(appId);
		this.setAppName(appName);
		this.setAppVersion(appVersion);
		this.setOrgCode(orgCode);
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getBindingCount() {
		return bindingCount;
	}

	public void setBindingCount(Integer bindingCount) {
		this.bindingCount = bindingCount;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public Integer getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}

	public Integer getInputData() {
		return inputData;
	}

	public void setInputData(Integer inputData) {
		this.inputData = inputData;
	}

	public Integer getOutputData() {
		return outputData;
	}

	public void setOutputData(Integer outputData) {
		this.outputData = outputData;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDateHour() {
		return dateHour;
	}

	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getAppCount() {
		return appCount;
	}

	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

}
