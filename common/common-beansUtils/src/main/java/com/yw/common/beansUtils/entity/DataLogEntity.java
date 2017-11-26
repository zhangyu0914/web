package com.yw.common.beansUtils.entity;

/**
 *<pre>
 * 功       能: 数据日志查询
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年7月26日下午3:23:09
 * Q    Q: 1784286916
 *</pre>
 */
public class DataLogEntity {
	
	private String _id;//主键
	private String content;//日志内容
	private String sn;//设备号
	private String model_id;//型号id
	private Integer model_version;//型号版本
	private Boolean auth_status;//是否认证
	private String cmd_type;//命令类型
	private String time;//
	
	public DataLogEntity(){
		super();
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
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
	public boolean isAuth_status() {
		return auth_status;
	}
	public void setAuth_status(boolean auth_status) {
		this.auth_status = auth_status;
	}
	public String getCmd_type() {
		return cmd_type;
	}
	public void setCmd_type(String cmd_type) {
		this.cmd_type = cmd_type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
