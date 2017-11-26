package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppLicenseDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 * <pre>
 * 功       能: 型号许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 17:25:06
 * Q    Q: 308053847
 * </pre>
 */
public class AppLicenseEntity extends BaseEntity {

	private static final long serialVersionUID = -2979139813032L;

	@Length(min = 0, max = 50, message = "WEBPLATFORM.APPLICENSE.FKLICENSETID")
	private String fkLicenseTid;// 许可证外键ID
	private String appInstanceLicId;// 应用许可证实例ID
	@Length(min = 0, max = 100, message = "WEBPLATFORM.APPLICENSE.APPID")
	private String appId;// APPID
	private Integer appIndex;//索引
	private String appReportName;// 应用报表名称
	@Length(min = 0, max = 50, message = "WEBPLATFORM.APPLICENSE.PROFILEID")
	private String profileId;// 属性ID
	@Length(min = 0, max = 10, message = "WEBPLATFORM.APPLICENSE.FUNCTION")
	private String function;// 权限
	@Length(min = 0, max = 100, message = "WEBPLATFORM.APPLICENSE.MODELNO")
	private String modelNo;// 型号
	private String beginTime;// 开始时间
	private String endTime;// 结束时间
	private Integer maxInstance;// 最大绑定数
	private Integer appInstanceStatus;// 应用实例状态
	private String icon;// ICON

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V1.0.0版本 *******************************************************************************/
	private String appInstance;//实例ID
	private String dataCode;//数据编码：应用:APP，平台：PLATFORM
	private Integer inputData;//输入数据
	private Integer outputData;//输出数据
	private String dateHour;//时间
	private Integer bindingCount;//绑定数据
	private String fkAppAccountTid;//
	
	// 无参构造方法
	public AppLicenseEntity() {
		super();
	}

	// TID参数构造方法
	public AppLicenseEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AppLicenseEntity(String tid, String fkLicenseTid) {
		super();
		this.setTid(tid);
		this.setFkLicenseTid(fkLicenseTid);
	}

	
	public AppLicenseEntity(String tid, String fkLicenseTid, String appInstanceLicId, String appId) {
		super();
		this.setTid(tid);
		this.fkLicenseTid = fkLicenseTid;
		this.appInstanceLicId = appInstanceLicId;
		this.appId = appId;
	}
	
	public AppLicenseEntity(String tid, String fkLicenseTid, String appInstanceLicId, 
			String appId, String beginTime, String endTime, Integer appIndex, String appReportName, String icon) {
		super();
		this.setTid(tid);
		this.fkLicenseTid = fkLicenseTid;
		this.appInstanceLicId = appInstanceLicId;
		this.appId = appId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.appIndex = appIndex;
		this.appReportName = appReportName;
		this.icon = icon;
	}

	public AppLicenseEntity(AppLicenseDto data) {
		super();

		if (data != null) {

		}
	}

	public String getFkLicenseTid() {
		return fkLicenseTid;
	}

	public void setFkLicenseTid(String fkLicenseTid) {
		this.fkLicenseTid = fkLicenseTid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	public String getAppInstanceLicId() {
		return appInstanceLicId;
	}

	public void setAppInstanceLicId(String appInstanceLicId) {
		this.appInstanceLicId = appInstanceLicId;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
	}

	public Integer getAppIndex() {
		return appIndex;
	}

	public void setAppIndex(Integer appIndex) {
		this.appIndex = appIndex;
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

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
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

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}
	
}
