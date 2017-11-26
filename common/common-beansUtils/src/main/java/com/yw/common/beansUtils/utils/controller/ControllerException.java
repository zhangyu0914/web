package com.yw.common.beansUtils.utils.controller;

import com.yw.common.beansUtils.utils.resultUtil.IhygeiaResultUtil;

/**
 * <pre>
 * 功       能: 控制器异常处理
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-7-14下午5:23:32
 * Q    Q: 308053847
 * </pre>
 */
public class ControllerException extends Exception {
	private static final long serialVersionUID = -7025719488300557260L;
	
	private IhygeiaResultUtil resultUtil;//下行结果数据
	private String logMsg;//下行日志信息

	public ControllerException() {

	}

	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(String message, IhygeiaResultUtil resultUtil) {
		super(message);
		this.setResultUtil(resultUtil);
	}

	public ControllerException(Throwable cause) {
		super(cause);
	}
	
	public ControllerException(Throwable cause, IhygeiaResultUtil resultUtil, String logMsg) {
		super(cause);
		this.setResultUtil(resultUtil);
		if (logMsg != null) {
			this.setLogMsg(logMsg + "异常");
		}
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ControllerException(String message, Throwable cause, IhygeiaResultUtil resultUtil) {
		super(message, cause);
		this.setResultUtil(resultUtil);
	}

	public IhygeiaResultUtil getResultUtil() {
		return resultUtil;
	}

	public void setResultUtil(IhygeiaResultUtil resultUtil) {
		this.resultUtil = resultUtil;
	}

	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}
}
