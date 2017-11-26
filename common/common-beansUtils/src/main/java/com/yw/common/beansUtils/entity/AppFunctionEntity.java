package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppFunctionDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-23 14:54:23
 * Q    Q: 308053847
 *</pre>
 */
public class AppFunctionEntity extends BaseEntity {

	private static final long serialVersionUID = -2991044926266L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.APPFUNCTION.FKAPPTID")
	private String fkAppTid;// 应用外键ID
	private String profileId;// 功能
	private String appId;// 应用ID
	private Integer appVersion;// 应用版本
	private String flow;// 配置
	@Length(min=0, max=10, message = "WEBPLATFORM.APPFUNCTION.MODELID")
	private String modelNo;// 型号ID
	private Integer ep;// 同组中某一个

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String fkModelTid;
	private String sn;
	//无参构造方法
	public AppFunctionEntity() {
		super();
	}
	
	public AppFunctionEntity(AppFunctionDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	//TID参数构造方法
	public AppFunctionEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AppFunctionEntity(String tid, String fkAppTid, String profileId, 
			String modelNo, String flow, Integer ep, String appId, Integer appVersion) {
		super();
		this.setTid(tid);
		this.fkAppTid = fkAppTid;
		this.setProfileId(profileId);
		this.modelNo = modelNo;
		this.flow = flow;
		this.ep = ep;
		this.appId = appId;
		this.appVersion = appVersion;
	}

	public AppFunctionEntity(String appId, String sn) {
		super();
		this.appId = appId;
		this.sn = sn;
	}

	public String getFkAppTid() {
		return fkAppTid;
	}

	public void setFkAppTid(String fkAppTid) {
		this.fkAppTid = fkAppTid;
	}

	

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getFkModelTid() {
		return fkModelTid;
	}

	public void setFkModelTid(String fkModelTid) {
		this.fkModelTid = fkModelTid;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
