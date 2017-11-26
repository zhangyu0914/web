package com.yw.common.beansUtils.dto;

import java.io.Serializable;

/**
 * <pre>
 * 功   能: Dto基类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22下午8:57:51
 * Q  Q: 308053847
 * </pre>
 */
public class BaseDto implements Serializable {

	private static final long serialVersionUID = -3671852735736781507L;
	private String createTime;// 创建时间

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
