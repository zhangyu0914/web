package com.yw.common.beansUtils.utils.resultUtil;

import java.io.Serializable;
import java.sql.Timestamp;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.Constants.ErrorCode;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;

/**
 * <pre>
 * 功   能: 返回结果类，用于得到状态及返回信息，方便前端查看
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-24上午9:39:51
 * Q  Q: 308053847
 * </pre>
 */
public class IhygeiaResultUtil implements Serializable {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	private static final long serialVersionUID = 5984957786423092079L;
	private String msg;
	private Object data;
	private int code;
	private InterfacePage page;
	private Timestamp systemTime = new Timestamp(System.currentTimeMillis());// 当前系统时间，作用：客户端计算多久前评论的

	public IhygeiaResultUtil() {
		this.msg = "操作失败";
		this.code = 1002;
	}

	public IhygeiaResultUtil(Constants.ErrorCode errorCode) {
		this.msg = errorCode.getMessage();
		this.code = errorCode.getErrCode();
	}

	public String getMsg() {
		return msg;
	}

	public IhygeiaResultUtil setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public IhygeiaResultUtil setData(Object data) {
		this.data = data;
		return this;
	}

	public int getCode() {
		log.info(msg);
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public IhygeiaResultUtil setCodeEnum(ErrorCode errorCode) {
		this.msg = errorCode.getMessage();
		this.code = errorCode.getErrCode();
		return this;
	}

	public InterfacePage getPage() {
		return page;
	}

	public IhygeiaResultUtil setPage(InterfacePage page) {
		this.page = page;
		return this;
	}

	public Timestamp getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Timestamp systemTime) {
		this.systemTime = systemTime;
	}

}
