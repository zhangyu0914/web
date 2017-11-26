package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * <pre>
 * 功       能: 推送MQTT消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 * </pre>
 */
public class PushMQTTEntity {

	private static final long serialVersionUID = -2985357507316L;

	private String sn;//SN
	private Timestamp time;//时间
	private String model_id;//型号ID
	private Integer model_version;//型号版本号
	private boolean multiple;//
	private List<String> routers;//路由
	private List<PushMQTTInfoEntity> info;//详情

	/**
	 * 
	 */
	public PushMQTTEntity() {
		super();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public List<String> getRouters() {
		return routers;
	}

	public void setRouters(List<String> routers) {
		this.routers = routers;
	}

	public List<PushMQTTInfoEntity> getInfo() {
		return info;
	}

	public void setInfo(List<PushMQTTInfoEntity> info) {
		this.info = info;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public Integer getModel_version() {
		return model_version;
	}

	public void setModel_version(Integer model_version) {
		this.model_version = model_version;
	}

}
