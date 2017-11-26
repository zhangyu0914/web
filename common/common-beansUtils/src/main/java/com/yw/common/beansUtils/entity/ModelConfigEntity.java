package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.ModelConfigDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 型号配置信息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-23 15:00:30
 * Q    Q: 308053847
 *</pre>
 */
public class ModelConfigEntity extends BaseEntity {

	private static final long serialVersionUID = -2991045660840L;
	
	@Length(min=0, max=50, message = "WEBPLATFORM.MODELCONFIG.FKMODELTID")
	private String fkModelTid;// 型号外键
	@Length(min=0, max=100, message = "WEBPLATFORM.MODELCONFIG.CONFIGID")
	private Integer configId;// 配置ID
	@Length(min=0, max=100, message = "WEBPLATFORM.MODELCONFIG.CONFIGNAME")
	private String configName;// 配置名称
	private String configType;// 配置类型
	private String flows;// 配置类型

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public ModelConfigEntity() {
		super();
	}
	
	//TID参数构造方法
	public ModelConfigEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public ModelConfigEntity(ModelConfigDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public ModelConfigEntity(String fkModelTid, Integer configId) {
		super();
		this.fkModelTid = fkModelTid;
		this.configId = configId;
	}

	public ModelConfigEntity(String tid, String fkModelTid, Integer configId,
			String configName, String configType, String flows) {
		super();
		this.setTid(tid);
		this.fkModelTid = fkModelTid;
		this.configId = configId;
		this.configName = configName;
		this.configType = configType;
		this.flows = flows;
	}

	public String getFkModelTid() {
		return fkModelTid;
	}

	public void setFkModelTid(String fkModelTid) {
		this.fkModelTid = fkModelTid;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getFlows() {
		return flows;
	}

	public void setFlows(String flows) {
		this.flows = flows;
	}


}
