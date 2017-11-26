package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-16 12:51:50
 * Q    Q: 308053847
 *</pre>
 */
public class LicenseDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String licNo;// 许可证编号
	private String pid;// 平台ID
	private String licName;// 许可证名称
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private String sn;//sn
	private String modelName;//模型名称
	private String modelNo;//型号
	private String modelCode;//厂商型号
	private String appName;//应用ID
	private String appId;//应用ID
	private Integer residueDayCount;// 剩余天数
	private String eqName;//设备名称
	private String deviceType;// 设备类型
	private Integer maxInstance;// 最大实例数
	private String appLicenseTid;//型号许可证Tid
	private String appReportName;//实例名称
	private String tradeName;//厂商名称
	private String tradeCode;//厂商编码
	private Integer modelVersion;//型号版本号
	private Integer appVersion;//应用版本号

	public LicenseDto() {
		super();
	}
	
	public LicenseDto(LicenseEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setLicName(data.getLicName());
			this.setLicNo(data.getLicNo());
			this.setPid(data.getPid());
			this.setSn(data.getSn());
			this.setModelName(data.getModelName());
			this.setModelNo(data.getModelNo());
			this.setModelCode(data.getModelCode());
			this.setAppName(data.getAppName());
			this.setAppId(data.getAppId());
			this.setResidueDayCount(data.getResidueDayCount());
			this.setDeviceType(data.getDeviceType());
			this.setEqName(data.getEqName());
			this.setBeginTime(data.getBeginTime());
			this.setEndTime(data.getEndTime());
			this.setMaxInstance(data.getMaxInstance());
			this.setAppLicenseTid(data.getAppLicenseTid());
			this.setAppReportName(data.getAppReportName());
			this.setTradeCode(data.getTradeCode());
			this.setTradeName(data.getTradeName());
			this.setModelVersion(data.getModelVersion());
			this.setAppVersion(data.getAppVersion());
			try {
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
