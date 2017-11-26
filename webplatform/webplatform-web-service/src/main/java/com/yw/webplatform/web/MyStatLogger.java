package com.yw.webplatform.web;

import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.support.logging.Log;

/**
 * <pre>
 * 功   能: Druid会定时（在SPRING中配置）调用此类，把数据传过来，以保存DruidDataSource的监控记录
 * 创建者: 陈    林(Vickey)
 * 日   期: 2014-8-1下午4:29:32
 * Q  Q: 308053847
 * </pre>
 */
public class MyStatLogger extends DruidDataSourceStatLoggerAdapter implements
		DruidDataSourceStatLogger {
	
	@Override
	public void log(DruidDataSourceStatValue statValue) {
		System.out.println(statValue);
	}

	@Override
	public void configFromProperties(Properties properties) {
		System.out.println(properties);
	}

	@Override
	public void setLogger(Log logger) {
		System.out.println(logger);
	}

	@Override
	public void setLoggerName(String loggerName) {
		System.out.println(loggerName);
	}
}
