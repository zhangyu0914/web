package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:14
 * Q    Q: 308053847
 *</pre>
 */
public class OrganizationDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String orgName;// 机构名称
	private String orgCode;// 机构编码
	private String address;// 地址

	public OrganizationDto() {
		super();
	}
	
	public OrganizationDto(OrganizationEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setOrgName(data.getOrgName());
			this.setOrgCode(data.getOrgCode());
			this.setAddress(data.getAddress());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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
