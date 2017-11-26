package com.yw.common.beansUtils.utils.result;

import java.io.Serializable;
import java.sql.Timestamp;

import com.yw.common.beansUtils.utils.ErrorType;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 返回结果类，用于得到状态及返回信息，方便前端查看
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-24上午9:39:51
 * Q  Q: 308053847
 * </pre>
 */
public class AppResultUtil<T> implements Serializable {

	private static final long serialVersionUID = 5984957786423092079L;
	private String msg;
	private T data;
	private int code;
	private String system_time = DateUtils.getSysStringTime();// 当前系统时间，作用：客户端计算多久前评论的

	public AppResultUtil() {
		initError();
	}

	public AppResultUtil initError() {
		return this.init(1002, "操作失败", null);
	}

	public AppResultUtil initSuccess() {
		return this.initSuccess(null);
	}

	public AppResultUtil initSuccess(T data) {
		return init(0, "操作成功", data);
	}

	public AppResultUtil init(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public AppResultUtil setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public T getData() {
		if (StringUtils.isBlank(data)) {
			return (T) new Object();
		}
		return data;
	}

	public AppResultUtil setData(T data) {
		this.data = data;
		return this;
	}

	public int getCode() {
		return code;
	}

	public AppResultUtil setCode(ErrorTypeEnum code) {
		this.code = Integer.valueOf(code.getCode());
		this.msg = code.getMessage();
		return this;
	}

	public String getSystem_time() {
		return system_time;
	}

	public void setSystem_time(String system_time) {
		this.system_time = system_time;
	}

}
