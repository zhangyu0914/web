package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.List;

import com.yw.common.beansUtils.dto.ModelConfigDto;
import com.yw.common.beansUtils.entity.ModelConfigEntity;
import com.yw.common.beansUtils.entity.importFile.ImportModelEntity;
import com.yw.common.beansUtils.entity.importFile.MConfigEntity;
import com.yw.common.beansUtils.entity.importFile.ModelPropertyEntity;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: 导入模型定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportModelDto {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// 唯一标示
	private String name;// 名称
	private Integer flag;// 标签：1:标签类设备，0：非标签类设备
	private Integer version;// 版本
	private String enterprise_code;// 机构编码
	private List<ModelPropertyDto> property;// 功能
	private List<MConfigDto> config;// 配置项

	public ImportModelDto() {
		super();
	}
	
	public ImportModelDto(ImportModelEntity data) {
		if (data != null) {
			
			this.setId(data.getId());
			this.setName(data.getName());
			this.setEnterprise_code(data.getEnterprise_code());
			this.setFlag(data.getFlag());
			if (!StringUtils.isBlank(data.getVersions())) {
				
				this.setVersion(data.getVersions().get(0).getVersion());
				if (data.getVersions().get(0).getProperty() != null) {
					
					for (ModelPropertyEntity property : data.getVersions().get(0).getProperty()) {
						this.setPropertyObj(new ModelPropertyDto(property));
					}
				}
				if (data.getVersions().get(0).getConfig() != null) {
					
					for (MConfigEntity config : data.getVersions().get(0).getConfig()) {
						this.setConfigObj(new MConfigDto(config));
					}
				}
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEnterprise_code() {
		return enterprise_code;
	}

	public void setEnterprise_code(String enterprise_code) {
		this.enterprise_code = enterprise_code;
	}

	public List<ModelPropertyDto> getProperty() {
		return property;
	}

	public void setProperty(List<ModelPropertyDto> property) {
		this.property = property;
	}
	
	public void setPropertyObj(ModelPropertyDto property) {
		if (this.property == null) {
			this.property = new ArrayList<ModelPropertyDto>();
		}
		this.property.add(property);
	}

	public List<MConfigDto> getConfig() {
		return config;
	}

	public void setConfig(List<MConfigDto> config) {
		this.config = config;
	}
	
	public void setConfigObj(MConfigDto config) {
		if (this.config == null) {
			this.config = new ArrayList<MConfigDto>();
		}
		this.config.add(config);
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
