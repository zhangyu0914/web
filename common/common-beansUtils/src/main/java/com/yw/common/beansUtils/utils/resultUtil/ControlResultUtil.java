package com.yw.common.beansUtils.utils.resultUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 控制接口返回类型
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-24上午9:39:51
 * Q  Q: 308053847
 * </pre>
 */
public class ControlResultUtil implements Serializable {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	private static final long serialVersionUID = 5984957786423092079L;

	private String time;
	private String uuid;
	private ControlInfo info;

	
	public ControlResultUtil() {
		super();
	}

	public ControlResultUtil(ControlEntity entity) {
		if (!StringUtils.isBlank(entity)) {
			this.setUuid(entity.getUuid());
			this.setInfo(new ControlInfo(entity.getInfo()));
		}
	}

	public String getTime() {
		return DateUtils.getSysStringTime();
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ControlInfo getInfo() {
		return info;
	}

	public ControlResultUtil setInfo(ControlInfo info) {
		this.info = info;
		return this;
	}
}
