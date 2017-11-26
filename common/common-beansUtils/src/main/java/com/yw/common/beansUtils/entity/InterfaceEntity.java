package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.InterfaceDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 *</pre>
 */
public class InterfaceEntity extends BaseEntity {

	private static final long serialVersionUID = -2985516909562L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.INTERFACE.INTERFACENAME")
	private String interfaceName;// 接口名称
	@Length(min=0, max=10, message = "WEBPLATFORM.INTERFACE.INTERFACECODE")
	private String interfaceCode;// 接口编码
	private Integer interfaceType;// 接口类型
	@Length(min=0, max=200, message = "WEBPLATFORM.INTERFACE.INTERFACEURL")
	private String interfaceUrl;// 接口地址

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public InterfaceEntity() {
		super();
	}
	
	//TID参数构造方法
	public InterfaceEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public InterfaceEntity(InterfaceDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public InterfaceEntity(String interfaceName, String interfaceUrl) {
		super();
		this.interfaceName = interfaceName;
		this.interfaceUrl = interfaceUrl;
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
