package com.yw.common.beansUtils.entity;

/**
 *<pre>
 * 功       能: 原始数据日志查询
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年8月10日上午10:24:34
 * Q    Q: 1784286916
 *</pre>
 */
public class RawDataLogEntity {
	
	private String _id; //主键
	private String content; //日志内容
	private String ip; //ip
	private String result; //结果
	private String time; 
	
	public RawDataLogEntity() {
		// TODO Auto-generated constructor stub
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

}
