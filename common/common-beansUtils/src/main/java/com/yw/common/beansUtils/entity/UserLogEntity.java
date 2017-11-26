package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.UserLogDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 用户注册登录日志表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 17:45:14
 * Q    Q: 308053847
 *</pre>
 */
public class UserLogEntity extends BaseEntity {

	private static final long serialVersionUID = -2977932628736L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.USERLOG.FKUSERTID")
	private String fkUserTid;// 外键用户ID
	private Timestamp lastLoginTime;// 最近一次登录时间
	@Length(min=0, max=64, message = "WEBPLATFORM.USERLOG.LASTLOGINTOKEN")
	private String lastLoginToken;// 最后一次TOKEN
	@Length(min=0, max=64, message = "WEBPLATFORM.USERLOG.LASTLOGINDEVICEID")
	private String lastLoginDeviceId;// 最后一次使用设备ID
	private Integer visitType;// 访问类型
	private String tokenTimeout;// 

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public UserLogEntity() {
		super();
	}
	
	//TID参数构造方法
	public UserLogEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public UserLogEntity(String tid, String fkUserTid, String lastLoginToken) {
		super();
		this.setTid(tid);
		this.fkUserTid = fkUserTid;
		this.lastLoginToken = lastLoginToken;
	}

	public UserLogEntity(UserLogDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public UserLogEntity(String tid, Integer visitType) {
		this.setTid(tid);
		this.setVisitType(visitType);
	}

	public UserLogEntity(String tid, String lastLoginToken) {
		this.setTid(tid);
		this.setLastLoginToken(lastLoginToken);
	}

	public String getFkUserTid() {
		return fkUserTid;
	}

	public void setFkUserTid(String fkUserTid) {
		this.fkUserTid = fkUserTid;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginToken() {
		return lastLoginToken;
	}

	public void setLastLoginToken(String lastLoginToken) {
		this.lastLoginToken = lastLoginToken;
	}

	public String getLastLoginDeviceId() {
		return lastLoginDeviceId;
	}

	public void setLastLoginDeviceId(String lastLoginDeviceId) {
		this.lastLoginDeviceId = lastLoginDeviceId;
	}

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public String getTokenTimeout() {
		return tokenTimeout;
	}

	public void setTokenTimeout(String tokenTimeout) {
		this.tokenTimeout = tokenTimeout;
	}

}
