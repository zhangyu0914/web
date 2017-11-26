package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.DeviceModelAttDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 设备与型号属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:56:17
 * Q    Q: 308053847
 *</pre>
 */
public class DeviceModelAttEntity extends BaseEntity {

	private static final long serialVersionUID = -2979129154696L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICEMODELATT.FKDEVICETID")
	private String fkDeviceTid;// 设备外键ID
	@Length(min=0, max=50, message = "WEBPLATFORM.DEVICEMODELATT.FKMODELATTTID")
	private String fkModelAttTid;// 型号属性外键ID
	@Length(min=0, max=100, message = "WEBPLATFORM.DEVICEMODELATT.ATTVALUE")
	private String attValue;// 属性值

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public DeviceModelAttEntity() {
		super();
	}
	
	//TID参数构造方法
	public DeviceModelAttEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public DeviceModelAttEntity(String tid, String fkDeviceTid, String fkModelAttTid,
			String attValue) {
		super();
		this.setTid(tid);
		this.fkDeviceTid = fkDeviceTid;
		this.fkModelAttTid = fkModelAttTid;
		this.attValue = attValue;
	}

	public DeviceModelAttEntity(DeviceModelAttDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public DeviceModelAttEntity(String tid, String attValue) {
		this.setTid(tid);
		this.setAttValue(attValue);
	}

	public String getFkDeviceTid() {
		return fkDeviceTid;
	}

	public void setFkDeviceTid(String fkDeviceTid) {
		this.fkDeviceTid = fkDeviceTid;
	}

	public String getFkModelAttTid() {
		return fkModelAttTid;
	}

	public void setFkModelAttTid(String fkModelAttTid) {
		this.fkModelAttTid = fkModelAttTid;
	}

	public String getAttValue() {
		return attValue;
	}

	public void setAttValue(String attValue) {
		this.attValue = attValue;
	}

}
