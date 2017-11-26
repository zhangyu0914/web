package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.InterfaceLogDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 调用接口记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-24 16:30:57
 * Q    Q: 308053847
 *</pre>
 */
public class InterfaceLogEntity extends BaseEntity {

	private static final long serialVersionUID = -2986045314482L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.INTERFACELOG.FKAPPACCOUNTTID")
	private String fkAppAccountTid;// 应用账号外键
	@Length(min=0, max=100, message = "WEBPLATFORM.INTERFACELOG.FKAPPACCOUNTINTERFACETID")
	private String fkAppAccountInterfaceTid;// 应用账号接口外键
	private Integer timeConsuming;// 耗时

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String orgName;// 公司名称	
	private String appName;// 应用名称
	private String appId;// 应用ID
	private Integer appVersion;//应用版本
	private String appInstance;//应用账号
	private String interfaceName;// 接口名称
	
	//无参构造方法
	public InterfaceLogEntity() {
		super();
	}
	
	//TID参数构造方法
	public InterfaceLogEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	/**
	 * @param fkAppAccountTid
	 */
	public InterfaceLogEntity(String tid, String fkAppAccountTid) {
		super();
		
		this.setTid(tid);
		this.fkAppAccountTid = fkAppAccountTid;
	}

	public InterfaceLogEntity(InterfaceLogDto data) {
		super();

		if (data != null) {
			
			
		}
	}
	
	
	public String getFkAppAccountTid() {
		return fkAppAccountTid;
	}

	public void setFkAppAccountTid(String fkAppAccountTid) {
		this.fkAppAccountTid = fkAppAccountTid;
	}

	public String getFkAppAccountInterfaceTid() {
		return fkAppAccountInterfaceTid;
	}

	public void setFkAppAccountInterfaceTid(String fkAppAccountInterfaceTid) {
		this.fkAppAccountInterfaceTid = fkAppAccountInterfaceTid;
	}

	public Integer getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(Integer timeConsuming) {
		this.timeConsuming = timeConsuming;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppInstance() {
		return appInstance;
	}

	public void setAppInstance(String appInstance) {
		this.appInstance = appInstance;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

}
