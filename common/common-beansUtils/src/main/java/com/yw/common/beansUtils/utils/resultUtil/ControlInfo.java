package com.yw.common.beansUtils.utils.resultUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.beansUtils.entity.InfoEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: 控制接口INFO
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年9月14日上午9:48:02
 * Q    Q: 308053847
 * </pre>
 */
public class ControlInfo<T> implements Serializable {

	private static final long serialVersionUID = 5984957786423092079L;
	
	private String sn;
	private String ack;
	private String cmdid;
	private T cmddata;

	public ControlInfo() {
		
	}
	
	public ControlInfo(String ack) {
		super();
		this.ack = ack;
	}

	public ControlInfo(InfoEntity<T> info) {
		if (!StringUtils.isBlank(info)) {
			
			this.setSn(info.getSn());
			this.setCmdid(info.getCmdid());
			this.setCmddata(info.getCmddata());
		}
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
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
