package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppDeviceDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
public class AppDeviceEntity extends BaseEntity {

	private static final long serialVersionUID = -2979110495220L;
	
	private String appId;// 
	private Integer appVersion;// 
	@Length(min=0, max=50, message = "WEBPLATFORM.APPDEVICE.FKAPPTID")
	private String fkAppAccountTid;// 应用许可证外键ID
	@Length(min=0, max=50, message = "WEBPLATFORM.APPDEVICE.FKDEVICETID")
	private String sn;// SN
	private String bindingTime;// 绑定时间
	private String eqName;//
	private Integer eqStatus;//
	private String modelNo;//
	@Length(min=0, max=100, message = "WEBPLATFORM.APPDEVICE.REMARK")
	private String remark;// 设备

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String fkAppTid;// 应用外键ID
	private String instanceId;//
	private String id;//
	
	//无参构造方法
	public AppDeviceEntity() {
		super();
	}
	
	//TID参数构造方法
	public AppDeviceEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	/**
	 * @param sn
	 * @param eqStatus
	 */
	public AppDeviceEntity(String sn, Integer eqStatus) {
		super();
		this.sn = sn;
		this.eqStatus = eqStatus;
	}

	public AppDeviceEntity(String tid, String fkAppAccountTid, String sn) {
		super();
		this.setTid(tid);
		this.fkAppAccountTid = fkAppAccountTid;
		this.sn = sn;
	}
	
	public AppDeviceEntity(AppDeviceDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public AppDeviceEntity(String tid, String fkAppAccountTid) {
		this.setTid(tid);
		this.setFkAppAccountTid(fkAppAccountTid);
	}

	
	public String getFkAppAccountTid() {
		return fkAppAccountTid;
	}

	public void setFkAppAccountTid(String fkAppAccountTid) {
		this.fkAppAccountTid = fkAppAccountTid;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFkAppTid() {
		return fkAppTid;
	}

	public void setFkAppTid(String fkAppTid) {
		this.fkAppTid = fkAppTid;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getBindingTime() {
		return bindingTime;
	}

	public void setBindingTime(String bindingTime) {
		this.bindingTime = bindingTime;
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

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
