package com.yw.webplatform.web;


import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.ip.IpUtil;
import com.yw.common.beansUtils.utils.log.LoggerUtil;
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
	
	private  final LoggerUtil log = LoggerUtil.getLogger(this.getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------------Jdbc START!------------------------------------");
		try {
			//判断单机环境/DOCKER环境
			System.out.println("-------------DOCKER---------------------");
			{
				String dbIp = System.getenv().get("IS_DOCKER");
				System.out.println("-------------YW_MYSQL_PORT_3306_TCP_ADDR:" + dbIp);
				if (!StringUtils.isBlank(dbIp)) {//docker 环境
					
					System.out.println("-------------IS DOCKER_MYSQL:" + dbIp);
					//替换文件内容
					String DOCKER_DB_USERNAME = "root";
					String DOCKER_DB_PASSWORD = StringUtils.druidPwd("password");
					String packageName = StringUtils.getPackageName(TomcatListener.class, "/",3);
					URL url = FileUtil.getClasses(FileUtil.class);
					StringBuffer fileContent = new StringBuffer();
					fileContent.append("driver=com.mysql.jdbc.Driver" + "\n");
					fileContent.append("url=jdbc:mysql://yw_mysql:3306/webplatform?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true" + "\n");
					fileContent.append("user=" + DOCKER_DB_USERNAME + "\n");
					fileContent.append("password=" + DOCKER_DB_PASSWORD + "\n");
					FileUtil.createFile(fileContent.toString(), url.getFile() + packageName + "/web/jdbc.properties");
					System.out.println("-------------FILE WRITE SUCCESS:" + fileContent);
				}else{
					System.out.println("-------------IS LOCAL");
				}
			}
			System.out.println("-------------LOCAL IP:" + IpUtil.getLocalIp());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("---------------Jdbc STOP!------------------------------------");
	}
}
