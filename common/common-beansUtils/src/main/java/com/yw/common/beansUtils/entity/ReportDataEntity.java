package com.yw.common.beansUtils.entity;

/**
 * <pre>
 * 功       能: 报表数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2016-05-01 15:11:48
 * Q    Q: 308053847
 * </pre>
 */
public class ReportDataEntity extends BaseEntity {

	private static final long serialVersionUID = -2924173417112L;

	private String dataName;// 数据名称
	private Integer firstData;// 第一部分数据
	private Integer secondData;// 第二部分数据
	private Integer thirdData;// 第三部分数据

	// 无参构造方法
	public ReportDataEntity() {
		super();
	}

	// TID参数构造方法
	public ReportDataEntity(String tid) {
		super();
		this.setTid(tid);
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Integer getFirstData() {
		return firstData;
	}

	public void setFirstData(Integer firstData) {
		this.firstData = firstData;
	}

	public Integer getSecondData() {
		return secondData;
	}

	public void setSecondData(Integer secondData) {
		this.secondData = secondData;
	}

	public Integer getThirdData() {
		return thirdData;
	}

	public void setThirdData(Integer thirdData) {
		this.thirdData = thirdData;
	}

}
