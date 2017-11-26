package com.yw.common.beansUtils.entity;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.MwconfigDto;

/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:37
 * Q    Q: 308053847
 *</pre>
 */
public class MwconfigEntity extends BaseEntity {

	private static final long serialVersionUID = -2997110715664L;
	
	@Length(min=0, max=50, message = "COMMON.MWCONFIG.CONFIG_PROJECT")
	private String config_project;// 项目名称
	@Length(min=0, max=20, message = "COMMON.MWCONFIG.CONFIGCLASS")
	private String configClass;// 配置类别
	@Length(min=0, max=50, message = "COMMON.MWCONFIG.CONFIGNAME")
	private String configName;// 配置名称
	@Length(min=0, max=50, message = "COMMON.MWCONFIG.CONFIGVALUE")
	private String configValue;// 配置值
	@Length(min=0, max=20, message = "COMMON.MWCONFIG.CONFIGUNIT")
	private String configUnit;// 单位
	@Length(min=0, max=50, message = "COMMON.MWCONFIG.CONFIGDNAME")
	private String configDname;// 配置项中文名称
	@Length(min=0, max=255, message = "COMMON.MWCONFIG.REMARK")
	private String remark;// 备注
	private Integer readOnly;// 只读,0:READ:只读,1:WRITE:可写
	@Length(min=0, max=255, message = "COMMON.MWCONFIG.TYPECONTENT")
	private String typeContent;// 类型内容
	@Length(min=0, max=255, message = "COMMON.MWCONFIG.CONFIGTYPE")
	private String configType;// 配置类型
	
	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public MwconfigEntity() {
		super();
	}
	
	//TID参数构造方法
	public MwconfigEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public MwconfigEntity(MwconfigDto data) {
		super();

		if (data != null) {
			
		}
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
