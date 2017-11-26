package com.yw.common.web;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;

/**
 *<pre>
 * 功   能: 容器启动时加载数据 
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午6:22:57
 * Q  Q: 308053847
 *</pre>
 */
public class TomcatListener implements ServletContextListener {
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	private IConfigurationService configurationService;//
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("---------------TOMCAT START!------------------------------------");
		try {
			ServletContext context = sce.getServletContext();
	        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	        configurationService = (IConfigurationService) ctx.getBean("configurationService");
	        if (configurationService.initData()) {// 配置表初始化数据功能
				
	        	SubThread subThread = new SubThread(RedisUtil.pool, RedisTypeEnum.NOTIFY.getCode()+"_config_sync");//订阅配置信息
	        	subThread.start();
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("---------------TOMCAT STOP!------------------------------------");
	}
}
