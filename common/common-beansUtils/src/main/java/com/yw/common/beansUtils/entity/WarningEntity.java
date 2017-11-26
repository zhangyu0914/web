package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.WarningDto;

/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-17 15:48:47
 * Q    Q: 308053847
 *</pre>
 */
public class WarningEntity extends BaseEntity {

	private static final long serialVersionUID = -2979473855638L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.WARNING.FKDEVICETID")
	private String sn;// 设备外键ID
	private String deviceType;//设备类型
	private Integer waStatus;// 报警状态
	private String content;// 内容
	private Integer waType;// 报警类型
	@Length(min=0, max=100, message = "WEBPLATFORM.WARNING.REMARK")
	private String remark;// 备注
	private Timestamp eventTime; // 设备离线事件时间
	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private Integer eqType;// 设备类型
	private String eqName;// 设备名称
	private Integer eqStatus;// 设备状态
	
	//无参构造方法
	public WarningEntity() {
		super();
	}
	
	//TID参数构造方法
	public WarningEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	
	public WarningEntity(Integer eqType) {
		super();
		this.eqType = eqType;
	}
	
	public WarningEntity(Integer eqType, Integer eqStatus) {
		super();
		this.eqType = eqType;
		this.eqStatus = eqStatus;
	}

	public WarningEntity(WarningDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}

	public Integer getWaStatus() {
		return waStatus;
	}

	public void setWaStatus(Integer waStatus) {
		this.waStatus = waStatus;
	}

	public Integer getWaType() {
		return waType;
	}

	public void setWaType(Integer waType) {
		this.waType = waType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
