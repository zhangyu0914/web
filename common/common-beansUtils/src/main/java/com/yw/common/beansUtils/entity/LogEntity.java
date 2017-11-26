package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.utils.date.DateUtils;


/**
 * <pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 * </pre>
 */
public class LogEntity {

	private static final long serialVersionUID = -2985516909312L;
	private String _id;//
	private String content;//
	private String log_level;//
	private String module;//
	private String project;//
	private Integer level_int;//
	private String time;//

	public LogEntity() {
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

	public String getLog_level() {
		return log_level;
	}

	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getLevel_int() {
		return level_int;
	}

	public void setLevel_int(Integer level_int) {
		this.level_int = level_int;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
