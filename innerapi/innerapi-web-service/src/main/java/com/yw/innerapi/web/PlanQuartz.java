package com.yw.innerapi.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.utils.log.LoggerUtil;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.innerapi.controller.BaseController;

/**
 *<pre>
 * 功   能: 定时器
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-14下午5:05:35
 * Q  Q: 308053847
 *</pre>
 */
public class PlanQuartz extends BaseController{
	
	private final LoggerUtil log = LoggerUtil.getLogger(getClass());
	@Autowired
	private IConfigapiService configapiService;//
	
	private void start(){
		try {
			log.info("-----定时任务开始执行------------------------------------");
			{
				this.configapiService.deviceStatusPlan();
			}
            log.info("-----定时任务结束执行------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
