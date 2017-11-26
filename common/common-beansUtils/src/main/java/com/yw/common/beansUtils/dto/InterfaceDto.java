package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 *</pre>
 */
public class InterfaceDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String interfaceName;//	接口名称
	private String interfaceCode;//	接口编码
	private Integer interfaceType;//	接口类型
	private String interfaceUrl;//	接口地址

	public InterfaceDto() {
		super();
	}
	
	public InterfaceDto(InterfaceEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setInterfaceName(data.getInterfaceName());
			this.setInterfaceCode(data.getInterfaceCode());
			this.setInterfaceType(data.getInterfaceType());
			this.setInterfaceUrl(data.getInterfaceUrl());
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

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	public Integer getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(Integer interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getInterfaceUrl() {
		return interfaceUrl;
	}

	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}
	
}
