package com.yw.common.beansUtils.dto;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:37
 * Q    Q: 308053847
 *</pre>
 */
public class MwconfigDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String config_project;// 项目名称
	private String configClass;// 配置类别
	private String configName;// 配置名称
	private String configValue;// 配置值
	private String configUnit;// 单位
	private String configDname;// 配置项中文名称
	private String remark;// 备注
	private Integer readOnly;// 只读,0:READ:只读,1:WRITE:可写
	private String typeContent;// 类型内容
	private String configType;// 配置类型

	public MwconfigDto() {
		super();
	}
	
	public MwconfigDto(MwconfigEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setConfigClass(data.getConfigClass());
			this.setConfigName(data.getConfigName());
			this.setConfigValue(data.getConfigValue());
			this.setConfigUnit(data.getConfigUnit());
			this.setConfigDname(data.getConfigDname());
			this.setRemark(data.getRemark());
			this.setReadOnly(data.getReadOnly());
			this.setTypeContent(data.getTypeContent());
			this.setConfigType(data.getConfigType());
			this.setConfig_project(data.getConfig_project());
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

	public String getConfigClass() {
		return configClass;
	}

	public void setConfigClass(String configClass) {
		this.configClass = configClass;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigUnit() {
		return configUnit;
	}

	public void setConfigUnit(String configUnit) {
		this.configUnit = configUnit;
	}

	public String getConfigDname() {
		return configDname;
	}

	public void setConfigDname(String configDname) {
		this.configDname = configDname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Integer readOnly) {
		this.readOnly = readOnly;
	}

	public String getTypeContent() {
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfig_project() {
		return config_project;
	}

	public void setConfig_project(String config_project) {
		this.config_project = config_project;
	}

	
}
