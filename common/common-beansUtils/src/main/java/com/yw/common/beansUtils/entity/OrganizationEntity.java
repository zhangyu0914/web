package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.OrganizationDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:14
 * Q    Q: 308053847
 *</pre>
 */
public class OrganizationEntity extends BaseEntity {

	private static final long serialVersionUID = -2979125909892L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.ORGANIZATION.ORGNAME")
	private String orgName;// 机构名称
	@Length(min=0, max=10, message = "WEBPLATFORM.ORGANIZATION.ORGCODE")
	private String orgCode;// 机构编码
	@Length(min=0, max=100, message = "WEBPLATFORM.ORGANIZATION.ADDRESS")
	private String address;// 地址

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public OrganizationEntity() {
		super();
	}
	
	//TID参数构造方法
	public OrganizationEntity(String tid) {
		super();
		this.setTid(tid);
	}
	public OrganizationEntity(String orgName, String orgCode) {
		super();
		this.setOrgName(orgName);
		this.setOrgCode(orgCode);
	}
	
	public OrganizationEntity(OrganizationDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
