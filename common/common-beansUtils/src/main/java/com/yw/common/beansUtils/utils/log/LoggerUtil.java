package com.yw.common.beansUtils.utils.log;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.yw.common.beansUtils.entity.LogEntity;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 自定义日志内，往后可以对日志进行处理
 * 创建者: 陈    林(Vickey)
 * 日   期: 2014-7-2下午11:39:50
 * Q  Q:
 * </pre>
 */
public final class LoggerUtil implements Serializable {

	private static final long serialVersionUID = -9203134631719728072L;
	private String name;
	private Class<?> cls;

	public LoggerUtil(String name) {
		this.name = name;
	}

	public LoggerUtil(Class<?> cls) {
		this.cls = cls;
	}

	public static LoggerUtil getLogger(String name) {
		return new LoggerUtil(name);
	}

	public static LoggerUtil getLogger(Class<?> cls) {
		return new LoggerUtil(cls);
	}

	public Logger getLog(String name, Class<?> cls) {
		if (name == null || name.equals("")) {
			return Logger.getLogger(cls);
		}
		return Logger.getLogger(name);
	}

	public void debug(Object message) {
		this.getLog(name, cls).debug(message);
	}

	public void info(Object message) {
		this.getLog(name, cls).info(message);
	}

	public void error(Object message) {
		this.getLog(name, cls).error(message);
	}

	public void error(Object message, Throwable e) {
		this.getLog(name, cls).error(message, e);
	}
	
	/**
	 * <pre>
	 * 说       明: 把日志推送到NODEJS日志中心
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月20日下午1:27:32
	 * Q    Q: 308053847
	 * </pre>
	 * @throws Exception 
	 */
	public static boolean pushLog(String content, String module, String logLevel) throws Exception{
		LogEntity log = new LogEntity();
		log.setContent(content);
		log.setProject("webplatform");
		log.setModule(module);
		log.setLog_level(logLevel);
		if (StringUtils.isBlank(RedisUtil.REDIS_HOST)) {
			RedisUtil.init();
		}
		return RedisUtil.lpush("log", log);
	}
}
