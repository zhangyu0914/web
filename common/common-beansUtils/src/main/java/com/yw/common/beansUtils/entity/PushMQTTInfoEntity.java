package com.yw.common.beansUtils.entity;


/**
 * <pre>
 * 功       能: 推送MQTT消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 * </pre>
 */
public class PushMQTTInfoEntity {

	private static final long serialVersionUID = -2985357507316L;
	private Integer ep;
	private String epname;
	private String name;
	private String prop_id;
	private String unit;
	private Object value;

	public PushMQTTInfoEntity() {
		super();
	}
	public PushMQTTInfoEntity(Integer ep, String epname, String name,
			String prop_id, String unit, Object value) {
		super();
		this.ep = ep;
		this.epname = epname;
		this.name = name;
		this.prop_id = prop_id;
		this.unit = unit;
		this.value = value;
	}

	public Integer getEp() {
		return ep;
	}

	public void setEp(Integer ep) {
		this.ep = ep;
	}

	public String getEpname() {
		return epname;
	}

	public void setEpname(String epname) {
		this.epname = epname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProp_id() {
		return prop_id;
	}

	public void setProp_id(String prop_id) {
		this.prop_id = prop_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	

}
