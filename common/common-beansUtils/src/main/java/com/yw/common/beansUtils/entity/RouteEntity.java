package com.yw.common.beansUtils.entity;

import java.util.List;

/**
 * <pre>
 * 功       能: 路由
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 * </pre>
 */
public class RouteEntity {

	private static final long serialVersionUID = -2978831570220L;
	private Integer level;// 级别
	private String eqName;// 设备名称
	private Integer eqType;// 设备类型
	private Integer eqStatus;// 设备状态
	private String eqIcon;// 设备ICON
	private String modelNo;// 型号
	private List<RouteEntity> childrenList;// 子级

	// 无参构造方法
	public RouteEntity() {
		super();
	}

	// TID参数构造方法
	public RouteEntity(String tid) {
		super();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public Integer getEqType() {
		return eqType;
	}

	public void setEqType(Integer eqType) {
		this.eqType = eqType;
	}

	public Integer getEqStatus() {
		return eqStatus;
	}

	public void setEqStatus(Integer eqStatus) {
		this.eqStatus = eqStatus;
	}

	public String getEqIcon() {
		return eqIcon;
	}

	public void setEqIcon(String eqIcon) {
		this.eqIcon = eqIcon;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public List<RouteEntity> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<RouteEntity> childrenList) {
		this.childrenList = childrenList;
	}

}
