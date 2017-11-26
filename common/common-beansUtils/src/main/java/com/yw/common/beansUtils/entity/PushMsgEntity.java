package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.PushMsgDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 推送消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public class PushMsgEntity extends BaseEntity {

	private static final long serialVersionUID = -2985357507316L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.PUSHMSG.APPID")
	private String appId;// 应用ID
	@Length(min=0, max=65535, message = "WEBPLATFORM.PUSHMSG.MSG")
	private String msg;// 消息
	private Integer msgStatus;// 消息状态
	@Length(min=0, max=100, message = "WEBPLATFORM.PUSHMSG.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String orgName;// 机构名称
	private String appName;// 应用名称
	private String appReportName;//实例名称
	private String sn;// 消息
	private String appInstanceId;//实例ID

	
	//无参构造方法
	public PushMsgEntity() {
		super();
	}
	
	//TID参数构造方法
	public PushMsgEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	/**
	 * @param appId
	 * @param msg
	 */
	public PushMsgEntity(String tid, String appId, String msg) {
		super();
		this.setTid(tid);
		this.appId = appId;
		this.msg = msg;
	}

	public PushMsgEntity(PushMsgDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAppInstanceId() {
		return appInstanceId;
	}

	public void setAppInstanceId(String appInstanceId) {
		this.appInstanceId = appInstanceId;
	}

}
