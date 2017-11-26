package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 型号许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 17:25:06
 * Q    Q: 308053847
 *</pre>
 */
public class AppLicenseDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String fkLicenseTid;// 许可证外键ID
	private String appInstanceLicId;// 应用许可证实例ID
	private String appId;// APPID
	private Integer appVersion;
	private Integer appIndex;//索引
	private String appReportName;// 应用报表名称
	private String profileId;// 属性ID
	private String function;// 权限
	private String modelNo;// 型号
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private Integer maxInstance;// 最大绑定数
	private Integer appInstanceStatus;// 应用实例状态
	private String icon;// ICON

	private String dataCode;
	private Integer inputData;
	private Integer outputData;
	private String dateHour;//
	private Integer bindingCount;//
	private String fkAppAccountTid;//
	
	public AppLicenseDto() {
		super();
	}
	
	public AppLicenseDto(AppLicenseEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setFkLicenseTid(data.getFkLicenseTid());
			this.setAppInstanceLicId(data.getAppInstanceLicId());
			this.setAppId(data.getAppId());
			this.setAppIndex(data.getAppIndex());
			this.setAppReportName(data.getAppReportName());
			this.setProfileId(data.getProfileId());
			this.setFunction(data.getFunction());
			this.setModelNo(data.getModelNo());
			this.setBeginTime(data.getBeginTime());
			this.setEndTime(data.getEndTime());
			this.setMaxInstance(data.getMaxInstance());
			this.setAppInstanceStatus(data.getAppInstanceStatus());
			this.setIcon(data.getIcon());
			this.setDataCode(data.getDataCode());
			this.setInputData(data.getInputData());
			this.setOutputData(data.getOutputData());
			this.setDateHour(data.getDateHour());
			this.setBindingCount(data.getBindingCount());
			this.setFkAppAccountTid(data.getFkAppAccountTid());
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

	public String getFkLicenseTid() {
		return fkLicenseTid;
	}

	public void setFkLicenseTid(String fkLicenseTid) {
		this.fkLicenseTid = fkLicenseTid;
	}

	public String getAppInstanceLicId() {
		return appInstanceLicId;
	}

	public void setAppInstanceLicId(String appInstanceLicId) {
		this.appInstanceLicId = appInstanceLicId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getAppIndex() {
		return appIndex;
	}

	public void setAppIndex(Integer appIndex) {
		this.appIndex = appIndex;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
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

	public Integer getMaxInstance() {
		return maxInstance;
	}

	public void setMaxInstance(Integer maxInstance) {
		this.maxInstance = maxInstance;
	}

	public Integer getAppInstanceStatus() {
		return appInstanceStatus;
	}

	public void setAppInstanceStatus(Integer appInstanceStatus) {
		this.appInstanceStatus = appInstanceStatus;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
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

	public String getDateHour() {
		return dateHour;
	}

	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}

	public Integer getBindingCount() {
		return bindingCount;
	}

	public void setBindingCount(Integer bindingCount) {
		this.bindingCount = bindingCount;
	}

	public String getFkAppAccountTid() {
		return fkAppAccountTid;
	}

	public void setFkAppAccountTid(String fkAppAccountTid) {
		this.fkAppAccountTid = fkAppAccountTid;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}
	
}
