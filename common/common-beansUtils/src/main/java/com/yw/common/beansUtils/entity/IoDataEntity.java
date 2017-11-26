package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.IoDataDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: IO数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-20 16:49:08
 * Q    Q: 308053847
 *</pre>
 */
public class IoDataEntity extends BaseEntity {

	private static final long serialVersionUID = -2979999497596L;
	
	@Length(min=0, max=10, message = "WEBPLATFORM.IODATA.DATACODE")
	private String dataCode;// 数据编码
	@Length(min=0, max=100, message = "WEBPLATFORM.IODATA.DATAVALUE")
	private String dataValue;// 数据值
	private Integer inputData;// 输入数据
	private Integer outputData;// 输出数据
	@Length(min=0, max=20, message = "WEBPLATFORM.IODATA.DATATIME")
	private String dataTime;// 数据时间
	@Length(min=0, max=100, message = "WEBPLATFORM.IODATA.REAMRK")
	private String reamrk;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public IoDataEntity() {
		super();
	}
	
	//TID参数构造方法
	public IoDataEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public IoDataEntity(IoDataDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
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

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getReamrk() {
		return reamrk;
	}

	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

}
