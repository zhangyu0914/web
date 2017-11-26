package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 向前端传输t_control_log表数据
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年7月1日下午8:24:23
 * Q    Q: 1784286916
 *</pre>
 */
public class ControlLogDto extends BaseDto {

	private String tid;//主键
	private String appInstance;//实例ID
	private String controlUuid;//下发唯一标识
	private String reqParam;//请求参数
	private String rspData;//返回内容
	
	public ControlLogDto() {
		super();
	}
	
	public ControlLogDto(ControlLogEntity data) {
		super();
		this.setTid(data.getTid());
		this.setAppInstance(data.getAppInstance());
		this.setControlUuid(data.getControlUuid());
		this.setReqParam(data.getReqParam());
		this.setRspData(data.getRspData());
		try {
			this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public String getControlUuid() {
		return controlUuid;
	}

	public void setControlUuid(String controlUuid) {
		this.controlUuid = controlUuid;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public String getRspData() {
		return rspData;
	}

	public void setRspData(String rspData) {
		this.rspData = rspData;
	}

}
