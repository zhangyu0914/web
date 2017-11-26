package com.yw.common.web;

import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.controller.BaseController;

/**
 *<pre>
 * 功   能: 定时器
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-14下午5:05:35
 * Q  Q: 308053847
 *</pre>
 */
public class PlanQuartz extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	private void start(){
		try {
			log.info("-----定时任务开始执行------------------------------------");
			{
			}
            log.info("-----定时任务结束执行------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
