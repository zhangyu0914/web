package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.PlatformDataDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 平台数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-14 16:11:07
 * Q    Q: 308053847
 *</pre>
 */
public class PlatformDataEntity extends BaseEntity {

	private static final long serialVersionUID = -2978958135868L;
	
	private Integer pdata;// 数据
	@Length(min=0, max=20, message = "WEBPLATFORM.PLATFORMDATA.DATATIME")
	private String dataTime;// 数据时间
	@Length(min=0, max=100, message = "WEBPLATFORM.PLATFORMDATA.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String dateHour;//
	
	//无参构造方法
	public PlatformDataEntity() {
		super();
	}
	
	//TID参数构造方法
	public PlatformDataEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public PlatformDataEntity(PlatformDataDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public PlatformDataEntity(Integer pdata, String dateHour) {
		super();
		this.pdata = pdata;
		this.dateHour = dateHour;
	}

	public Integer getPdata() {
		return pdata;
	}

	public void setPdata(Integer pdata) {
		this.pdata = pdata;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDateHour() {
		return dateHour;
	}

	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}

}
