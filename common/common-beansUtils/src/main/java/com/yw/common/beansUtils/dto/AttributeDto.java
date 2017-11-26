package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public class AttributeDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String profileId;// PROFILE_ID
	private String attName;// 属性名称
	private String attKey;// 属性key
	private String attType;// 属性类型
	private String valueType;// 值类型
	private String valueDemand;// 值要求
	private String valueRegex;// 正则规则
	private String remark;// 备注
	private boolean checked; // 默认选中，用于后台表与表关联时，修改功能默认选中数据
	
	public AttributeDto() {
		super();
	}
	
	public AttributeDto(AttributeEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setProfileId(data.getProfileId());
			this.setAttName(data.getAttName());
			this.setAttKey(data.getAttKey());
			this.setAttType(data.getAttType());
			this.setValueType(data.getValueType());
			this.setValueDemand(data.getValueDemand());
			this.setValueRegex(data.getValueRegex());
			this.setRemark(data.getRemark());
			this.setChecked(data.getChecked());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public String getAttKey() {
		return attKey;
	}

	public void setAttKey(String attKey) {
		this.attKey = attKey;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
