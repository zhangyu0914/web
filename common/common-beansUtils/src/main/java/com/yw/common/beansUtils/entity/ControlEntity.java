package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.PlatformAppDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 * <pre>
 * 功       能: 控制接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 * </pre>
 */
public class ControlEntity<T> {

	private static final long serialVersionUID = -2986723067570L;

	private String uuid;//
	private String time;
	private InfoEntity<T> info;//

	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V2.0.0版本 *******************************************************************************/

	// 无参构造方法
	public ControlEntity() {
		super();
	}

	// TID参数构造方法
	public ControlEntity(String tid) {
		super();
	}

	public ControlEntity(PlatformAppDto data) {
		super();

		if (data != null) {

		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public InfoEntity<T> getInfo() {
		return info;
	}

	public void setInfo(InfoEntity<T> info) {
		this.info = info;
	}

}
