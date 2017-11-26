package com.yw.webplatform.web;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.entity.UserLogEntity;
import com.yw.common.beansUtils.utils.Constants;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.UserLogVisitTypeEnum;
import com.yw.common.beansUtils.utils.spring.PropertyConfigurer;
import com.yw.innerapi.api.IConfigapiService;

/**
 *<pre>
 * 功   能: 容器启动时加载数据 
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午6:22:57
 * Q  Q: 308053847
 *</pre>
 */
public class TomcatListener implements ServletContextListener {
	
	private static Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	private IConfigurationService configurationService;//
	private IConfigapiService configapiService;//
	
	private static ExecutorService es = null;
	/**
	 * <pre>
	 * 说       明: 设置线程池
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月8日下午5:21:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static ExecutorService getExecutorInstance() {
		if (es == null) {
			es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
		}
		return es;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("---------------TOMCAT START!------------------------------------");
		try {
			ServletContext context = sce.getServletContext();
	        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	        configurationService = (IConfigurationService) ctx.getBean("configurationService");
	        configapiService = (IConfigapiService) ctx.getBean("configapiService");
	        
	        configurationService.initData();// 配置表初始化数据功能
	        
	        getExecutorInstance().execute(new Runnable() {
				@Override
				public void run() {
					try {
				        configapiService.ioAdd();
						configapiService.devicePut();
						configapiService.warningSet();
					} catch (Exception e) {
						log.error("登录信息失败！！！", e);
					}
				}
			});
	        
	        log.debug("启动成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("---------------TOMCAT STOP!------------------------------------");
	}
}
