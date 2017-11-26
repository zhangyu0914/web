package com.yw.common.web;


import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.ehcache.config.generator.model.elements.ConfigurationElement;

import com.yw.common.beansUtils.utils.ErrorType;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.ip.IpUtil;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功   能: 容器启动时加载数据 
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-27下午6:22:57
 * Q  Q: 308053847
 *</pre>
 */
public class JdbcListener implements ServletContextListener {
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("---------------Jdbc START!------------------------------------");
		try {
			//判断单机环境/DOCKER环境
			log.info("判断单机环境/DOCKER环境");
			String dbIp = System.getenv().get("YW_MYSQL_PORT_3306_TCP_ADDR");
			log.info("YW_MYSQL_PORT_3306_TCP_ADDR:" + dbIp);
			if (!StringUtils.isBlank(dbIp)) {//docker 环境
				
				log.info("是DOCKER环境:" + dbIp);
				//替换文件内容
				String DOCKER_DB_USERNAME = "root";
				String DOCKER_DB_PASSWORD = StringUtils.druidPwd("password");
				String packageName = StringUtils.getPackageName(ErrorType.class, "/",3);
				URL url = FileUtil.getClasses(FileUtil.class);
				StringBuffer fileContent = new StringBuffer();
				fileContent.append("driver=com.mysql.jdbc.Driver" + "\n");
				fileContent.append("url=jdbc:mysql://"+dbIp+":3306/webplatform?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" + "\n");
				fileContent.append("user=" + DOCKER_DB_USERNAME + "\n");
				fileContent.append("password=" + DOCKER_DB_PASSWORD + "\n");
				FileUtil.createFile(fileContent.toString(), url.getFile() + packageName + "/web/jdbc.properties");
				log.info("文件写入成功:" + dbIp);
			}else{
				log.info("是单机环境");
			}
			
			log.info("本机IP:" + IpUtil.getLocalIp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("---------------Jdbc STOP!------------------------------------");
	}
}
