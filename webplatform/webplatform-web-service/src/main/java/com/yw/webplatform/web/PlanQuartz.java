package com.yw.webplatform.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.webplatform.api.IAuthLogService;
import com.yw.webplatform.api.IIoDataService;
import com.yw.webplatform.api.ILicenseService;
import com.yw.webplatform.controller.BaseController;

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
	@Autowired
	private IIoDataService ioDataService;
	@Autowired
	private IAuthLogService authLogService;
	@Autowired
	private ILicenseService licenseService;
	@Autowired
	private IConfigapiService configapiService;//
	
	private void start(){
		try {
			log.info("-----定时任务开始执行------------------------------------");
			{
				this.ioDataService.insertTestData(DateUtils.getSysStringTime(null));//生成测试数据
				this.authLogService.updateOnlineForTimeOUt(null);//已超时会话强制下线
				this.licenseService.findExLicense();//查询已过期的许可证
				this.configapiService.deviceStatusPlan();
			}
            log.info("-----定时任务结束执行------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deviceStatusPlan(){
		try {
			log.info("-----定时任务开始执行[deviceStatusPlan]------------------------------------");
			{
				this.configapiService.deviceStatusPlan();//定时更新设备状态
			}
            log.info("-----定时任务结束执行[deviceStatusPlan]------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
