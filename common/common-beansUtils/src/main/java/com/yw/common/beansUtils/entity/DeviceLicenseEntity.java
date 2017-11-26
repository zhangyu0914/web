package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.DeviceLicenseDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:56:29
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceLicenseEntity extends BaseEntity {

	private static final long serialVersionUID = -2979121978466L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICELICENSE.FKLICENSETID")
	private String fkLicenseTid;// 许可证外键ID
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICELICENSE.SN")
	private String sn;// SN
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICELICENSE.REMARK")
	private String remark;// 备注
	private String beginTime;///
	private String endTime;///
	private String modelNo;//

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String modelName;//
	
	//无参构造方法
	public DeviceLicenseEntity() {
		super();
	}
	
	//TID参数构造方法
	public DeviceLicenseEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public DeviceLicenseEntity(DeviceLicenseDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public DeviceLicenseEntity(String tid, String fkLicenseTid) {
		this.setTid(tid);
		this.setFkLicenseTid(fkLicenseTid);
	}
	
	public DeviceLicenseEntity(String tid, String fkLicenseTid, String sn) {
		this.setTid(tid);
		this.setFkLicenseTid(fkLicenseTid);
		this.setSn(sn);
	}
	
	public DeviceLicenseEntity(String tid, String fkLicenseTid, String sn, String modelNo) {
		this.setTid(tid);
		this.setFkLicenseTid(fkLicenseTid);
		this.setSn(sn);
		this.setModelNo(modelNo);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}
	
	public DeviceLicenseEntity(String tid, String fkLicenseTid, String sn, String modelNo, String beginTime, String endTime) {
		this.setTid(tid);
		this.setFkLicenseTid(fkLicenseTid);
		this.setSn(sn);
		this.setModelNo(modelNo);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}
	
	public String getFkLicenseTid() {
		return fkLicenseTid;
	}

	public void setFkLicenseTid(String fkLicenseTid) {
		this.fkLicenseTid = fkLicenseTid;
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
