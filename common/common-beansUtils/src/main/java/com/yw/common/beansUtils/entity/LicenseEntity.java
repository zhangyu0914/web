package com.yw.common.beansUtils.entity;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.LicenseDto;

/**
 * <pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-16 12:51:50
 * Q    Q: 308053847
 * </pre>
 */
public class LicenseEntity extends BaseEntity {

	private static final long serialVersionUID = -2979279821506L;

	@Length(min = 0, max = 50, message = "WEBPLATFORM.LICENSE.LICNAME")
	private String licName;// 许可证名称
	private String pid;//平台唯一编号
	@Length(min = 0, max = 50, message = "WEBPLATFORM.LICENSE.LICNO")
	private String licNo;// 许可证编号
	@Length(min = 0, max = 50, message = "WEBPLATFORM.LICENSE.REMARK")
	private String remark;// 备注

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V1.0.0版本 *******************************************************************************/
	private String sn;//SN
	private String modelName;//
	private String modelNo;//型号
	private String modelCode;//型号
	private String appName;//应用名称
	private String appId;//应用ID
	private Integer residueDayCount;// 剩余天数
	private String deviceType;// 设备类型
	private String eqName;//
	private String beginTime;//
	private String endTime;//
	private Integer maxInstance;// 最大实例数
	private String appLicenseTid;//许可证Tid
	private String appReportName;//实例名称
	private String tradeName;//厂商名称
	private String tradeCode;//厂商编码
	private Integer modelVersion;//型号版本号
	private Integer appVersion;//应用版本号

	// 无参构造方法
	public LicenseEntity() {
		super();
	}

	// TID参数构造方法
	public LicenseEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public LicenseEntity(String tid, String licNo) {
		super();
		this.setTid(tid);
		this.setLicNo(licNo);
	}

	public LicenseEntity(String tid, String licNo, String pid, String licName, String beginTime, String endTime) {
		super();
		this.setTid(tid);
		this.setLicNo(licNo);
		this.setPid(pid);
		this.setLicName(licName);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}

	public LicenseEntity(LicenseDto data) {
		super();

		if (data != null) {

		}
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getResidueDayCount() {
		return residueDayCount;
	}

	public void setResidueDayCount(Integer residueDayCount) {
		this.residueDayCount = residueDayCount;
	}


	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getMaxInstance() {
		return maxInstance;
	}

	public void setMaxInstance(Integer maxInstance) {
		this.maxInstance = maxInstance;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAppLicenseTid() {
		return appLicenseTid;
	}

	public void setAppLicenseTid(String appLicenseTid) {
		this.appLicenseTid = appLicenseTid;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
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

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
