package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-17 15:48:47
 * Q    Q: 308053847
 *</pre>
 */
public class WarningDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private Integer eqType;// 设备类型
	private String content;// 内容
	private Integer waStatus;// 警告状态
	private String sn;// SN
	private String eqName;// 设备名称
	private Integer eqStatus;// 设备状态
	private String eventTime; // 设备离线事件时间

	public WarningDto() {
		super();
	}
	
	public WarningDto(WarningEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setEqType(data.getEqType());
			this.setContent(data.getContent());
			this.setWaStatus(data.getWaStatus());
			this.setSn(data.getSn());
			this.setEqName(data.getEqName());
			this.setEqStatus(data.getWaStatus());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
				this.setEventTime(DateUtils.format(data.getEventTime(), null));
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

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	
}
