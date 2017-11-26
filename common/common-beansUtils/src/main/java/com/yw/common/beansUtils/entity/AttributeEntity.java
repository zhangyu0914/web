package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AttributeDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public class AttributeEntity extends BaseEntity {

	private static final long serialVersionUID = -2979110842720L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.PROFILEID")
	private String profileId;// PROFILE_ID
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.ATTNAME")
	private String attName;// 属性名称
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.ATTKEY")
	private String attKey;// 属性key
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.ATTTYPE")
	private String attType;// 属性类型
	@Length(min=0, max=50, message = "WEBPLATFORM.ATTRIBUTE.VALUETYPE")
	private String valueType;// 值类型
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.VALUEDEMAND")
	private String valueDemand;// 值要求
	@Length(min=0, max=1000, message = "WEBPLATFORM.ATTRIBUTE.VALUEREGEX")
	private String valueRegex;// 正则规则
	@Length(min=0, max=100, message = "WEBPLATFORM.ATTRIBUTE.REMARK")
	private String remark;// 备注

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	private List<ModelEntity> modelList; // 此属性下所有所有型号
	/****V1.0.0版本*******************************************************************************/
	private String[] id;//
	
	//无参构造方法
	public AttributeEntity() {
		super();
	}
	
	//TID参数构造方法
	public AttributeEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public AttributeEntity(String profileId, String attName, String attKey) {
		super();
		this.profileId = profileId;
		this.attName = attName;
		this.attKey = attKey;
	}
	
	

	public AttributeEntity(String tid, String profileId, String attName, String attKey, String attType) {
		super();
		this.setTid(tid);
		this.profileId = profileId;
		this.attName = attName;
		this.attKey = attKey;
		this.attType = attType;
	}

	public AttributeEntity(AttributeDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getValueDemand() {
		return valueDemand;
	}

	public void setValueDemand(String valueDemand) {
		this.valueDemand = valueDemand;
	}

	public String getAttKey() {
		return attKey;
	}

	public void setAttKey(String attKey) {
		this.attKey = attKey;
	}

	public String getValueRegex() {
		return valueRegex;
	}

	public void setValueRegex(String valueRegex) {
		this.valueRegex = valueRegex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ModelEntity> getModelList() {
		return modelList;
	}

	public void setModelList(List<ModelEntity> modelList) {
		this.modelList = modelList;
	}

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

}
