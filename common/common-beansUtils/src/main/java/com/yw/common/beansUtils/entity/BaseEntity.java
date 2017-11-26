package com.yw.common.beansUtils.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.github.abel533.echarts.Option;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 实体基类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22下午8:57:51
 * Q  Q: 308053847
 * </pre>
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 3864100716825196678L;

	private String tid; // 主键
	private Timestamp createTime; // 创建时间
	private Long updateTime; // 更新时间
	private Timestamp startSearchTime;// 开始时间，供后台使用：主要用于查询某个时间段范围内的数据
	private Timestamp endSearchTime;// 结束时间
	private boolean checked; // 默认选中，用于后台表与表关联时，修改功能默认选中数据
	private Integer delFlag;//数据状态，0：正常，1：已删除
	private List<Option> option; // 报表
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getStartSearchTime() {
		return startSearchTime;
	}

	public void setStartSearchTime(Timestamp startSearchTime) {
		this.startSearchTime = startSearchTime;
	}

	public Timestamp getEndSearchTime() {
		if (this.endSearchTime != null) {
			return new Timestamp(this.endSearchTime.getTime()
					+ (1 * 24 * 60 * 60 * 1000));// 给结果时间加一天
		}
		return endSearchTime;
	}

	public void setEndSearchTime(Timestamp endSearchTime) {
		this.endSearchTime = endSearchTime;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<Option> getOption() {
		return option;
	}

	public void setOption(Option option) {
		if (StringUtils.isBlank(this.option)) {
			this.option = new ArrayList<Option>();
		}
		this.option.add(option);
	}
}
