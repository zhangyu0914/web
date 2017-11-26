package com.yw.common.beansUtils.entity;


/**
 *<pre>
 * 功       能: t_control_log表
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年6月29日下午2:11:30
 * Q    Q: 1784286916
 *</pre>
 */
public class ControlLogEntity extends BaseEntity {

	private String appInstance;//实例ID
	private String controlUuid;//下发唯一标识;
	private String reqParam;//请求参数
	private String rspData;//返回内容
	
	public ControlLogEntity() {
		
	}
	
	/**
	 * @param appInstance
	 * @param controlUuid
	 */
	public ControlLogEntity(String appInstance, String controlUuid) {
		super();
		this.appInstance = appInstance;
		this.controlUuid = controlUuid;
	}

	public ControlLogEntity(String tid, String appInstance, String controlUuid, String reqParam, String rspData) {
		this.setTid(tid);
		this.setAppInstance(appInstance);
		this.setControlUuid(controlUuid);
		this.setReqParam(reqParam);
		this.setRspData(rspData);
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
