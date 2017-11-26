package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.entity.BaseEntity;
import com.yw.common.beansUtils.dto.SnListDto;

/**
 *<pre>
 * 功       能: SN列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-07-13 14:34:22
 * Q    Q: 308053847
 *</pre>
 */
public class SnListEntity extends BaseEntity {

	private static final long serialVersionUID = -2999855324414L;
	
	@Length(min=0, max=10, message = "WEBPLATFORM.SNLIST.DEVICETYPE")
	private String deviceType;// 设备类型
	@Length(min=0, max=100, message = "WEBPLATFORM.SNLIST.EQNAME")
	private String eqName;// 设备名称
	private Integer eqType;// 设备类型
	@Length(min=0, max=100, message = "WEBPLATFORM.SNLIST.MODELNO")
	private String modelNo;// 设备类型

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public SnListEntity() {
		super();
	}
	
	//TID参数构造方法
	public SnListEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public SnListEntity(SnListDto data) {
		super();

		if (data != null) {
			
			//this.setTid(data.getTid());
			//this.setDeviceType(data.getDeviceType());
			//this.setEqName(data.getEqName());
			//this.setEqType(data.getEqType());
			//this.setModelNo(data.getModelNo());

		}
	}
	
	public SnListEntity(Integer eqType) {
		super();
		this.eqType = eqType;
	}

	public SnListEntity(String tid, String deviceType) {
		super();
		this.setTid(tid);
		this.deviceType = deviceType;
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

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

}
