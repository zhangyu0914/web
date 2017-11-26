package com.yw.common.beansUtils.utils.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 * Created by XingMing.Li on 2015/8/11.
 */
public class SpringContextUtils{
	private static final String ROP_CONFIG_PROPERTIES="rop_config_properties";
    private static WebApplicationContext springContext;
    public static WebApplicationContext getSpringContext() {
        return springContext;
    }
    public static void setSpringContext(WebApplicationContext springContext) {
        SpringContextUtils.springContext = springContext;
    }

    public static String getRopConfProperties(String name){

    	Properties pro=(Properties) springContext.getBean(ROP_CONFIG_PROPERTIES);
    	return pro.getProperty(name);
    }
}
