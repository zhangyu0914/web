package com.yw.common.beansUtils.entity.importFile;


/**
 * <pre>
 * 功       能: 导入许可证应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class MwCfgEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String config_project;// 项目名称
	private String config_class;// 配置名称
	private String config_name;// 配置名称
	private String config_value;// 配置值
	private String config_unit;// 单位
	private String config_dname;// 配置项中文名称
	private String remark;// 备注
	private Integer read_only;// 只读,0:READ:只读,1:WRITE:可写
	private String type_content;// 类型内容
	private String config_type;// 配置类型
	private String create_time;// 创建时间
	
	public MwCfgEntity() {
		super();
	}

	public String getConfig_name() {
		return config_name;
	}

	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}

	public String getConfig_value() {
		return config_value;
	}

	public void setConfig_value(String config_value) {
		this.config_value = config_value;
	}

	public String getConfig_unit() {
		return config_unit;
	}

	public void setConfig_unit(String config_unit) {
		this.config_unit = config_unit;
	}

	public String getConfig_dname() {
		return config_dname;
	}

	public void setConfig_dname(String config_dname) {
		this.config_dname = config_dname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRead_only() {
		return read_only;
	}

	public void setRead_only(Integer read_only) {
		this.read_only = read_only;
	}

	public String getType_content() {
		return type_content;
	}

	public void setType_content(String type_content) {
		this.type_content = type_content;
	}

	public String getConfig_type() {
		return config_type;
	}

	public void setConfig_type(String config_type) {
		this.config_type = config_type;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getConfig_class() {
		return config_class;
	}

	public void setConfig_class(String config_class) {
		this.config_class = config_class;
	}

	public String getConfig_project() {
		return config_project;
	}

	public void setConfig_project(String config_project) {
		this.config_project = config_project;
	}
	
}
