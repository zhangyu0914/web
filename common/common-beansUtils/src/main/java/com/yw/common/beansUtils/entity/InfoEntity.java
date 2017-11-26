package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.dto.PlatformAppDto;

/**
 * <pre>
 * 功       能: 控制信息接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 * </pre>
 */
public class InfoEntity<T> {

	private static final long serialVersionUID = -2986723067570L;

	private String sn;//
	private Integer ep;
	private String cmdid;
	private T cmddata;

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V2.0.0版本 *******************************************************************************/

	// 无参构造方法
	public InfoEntity() {
		super();
	}

	// TID参数构造方法
	public InfoEntity(String tid) {
		super();
	}

	public InfoEntity(PlatformAppDto data) {
		super();

		if (data != null) {

		}
	}

	public InfoEntity(String sn, Integer ep, String cmdid, T cmddata) {
		super();
		this.sn = sn;
		this.ep = ep;
		this.cmdid = cmdid;
		this.cmddata = cmddata;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getCmdid() {
		return cmdid;
	}

	public void setCmdid(String cmdid) {
		this.cmdid = cmdid;
	}

	public T getCmddata() {
		return cmddata;
	}

	public void setCmddata(T cmddata) {
		this.cmddata = cmddata;
	}
}
