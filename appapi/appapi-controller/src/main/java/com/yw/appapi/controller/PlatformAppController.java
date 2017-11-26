package com.yw.appapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.appapi.api.IPlatformAppService;
import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.ip.IpUtil;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.resultUtil.ControlResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAuthLogService;

/**
 *<pre>
 * 功       能: 平台提供APP访问接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("platform/api/v100")
@Controller
public class PlatformAppController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IPlatformAppService platformAppService;//平台提供APP访问接口
	@Autowired
	public IAuthLogService authLogService;//应用认证
    
	
	/**
	 * <pre>
	 * 说       明: 设备状态查询接口(A1001)
	 * 涉及版本: V2.0.0
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月28日下午2:43:43
	 * Q    Q: 308053847
	 * </pre>
	 */
    @RequestMapping("device/get")
    @ResponseBody
    public AppResultUtil deviceGetV200(HttpServletRequest request,
    		HttpServletResponse response, String sn) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.deviceGet(sn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备历史数据查询(A1002)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("history_device_data/get")
    @ResponseBody
    public AppResultUtil historyDeviceDataGetV200(HttpServletRequest request,
    		HttpServletResponse response, PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.historyDeviceDataGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备在离线历史数据查询接口(A1003)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("onoffline_device_data/get")
    @ResponseBody
    public AppResultUtil onofflineDeviceDataGetV200(HttpServletRequest request,
    		HttpServletResponse response, PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.onofflineDeviceDataGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 下行历史数据查询接口(A1004)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("history_descending_order/get")
    @ResponseBody
    public AppResultUtil historyDescendingOrderGetV200(HttpServletRequest request,
    		HttpServletResponse response, PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.historyDescendingOrderGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备控制(A1005)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    
    @RequestMapping(value="device/control", method=RequestMethod.POST)
    @ResponseBody
    public void deviceControlV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody ControlEntity data) throws Exception{
    	Object resultUtil = new Object();
    	try {
    		resultUtil = this.platformAppService.deviceControl(data);
    		PrintWriteUtil.printJsonNoSign(response, resultUtil);
    	} catch (Exception e) {
    		e.printStackTrace();
    			
    		ControlResultUtil controlResult = new ControlResultUtil(data);
    		controlResult.getInfo().setAck("errmsg");
			PrintWriteUtil.printJsonNoSign(response, controlResult);
    	}
    }
    
    
    /**
     * <pre>
     * 说       明: 应用认证接口(A1006)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月26日下午2:39:08
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("app/auth")
    @ResponseBody
    public AppResultUtil appAuthV200(HttpServletRequest request,
    		HttpServletResponse response, PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		if (StringUtils.isBlank(entity)) {
    			entity = new PlatformAppEntity();
			}
    		entity.setClient_url(request.getRequestURI());
    		entity.setClient_ip(IpUtil.getIp(request));
    		resultUtil = this.authLogService.auth(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备绑定(A1007)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("devicebind/set")
    @ResponseBody
    public AppResultUtil devicebindSetV200(HttpServletRequest request,
    		HttpServletResponse response, String app_instance, String sn, String token) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.devicebindSet(app_instance, sn, token);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备解绑(A1008)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("devicebind/del")
    @ResponseBody
    public AppResultUtil devicebindDelV200(HttpServletRequest request,
    		HttpServletResponse response, String app_instance, String sn, String token) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.devicebindDel(app_instance, sn, token);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    

    /**
     * <pre>
     * 说       明: 应用状态心跳提交接口(A1009)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping(value="appstatus/set", method=RequestMethod.POST)
    @ResponseBody
    public AppResultUtil appstatusSetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.appstatusSet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 应用错误提交接口(A1010)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping(value="appstatus/post", method=RequestMethod.POST)
    @ResponseBody
    public AppResultUtil appstatusPostV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody PlatformAppEntity entity) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.appstatusPost(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 设备实时状态查询接口(A1011)
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月28日下午2:43:43
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping(value="device_real_time/get")
    @ResponseBody
    public AppResultUtil deviceRealTimeGetV200(HttpServletRequest request,
    		HttpServletResponse response, String sn) throws Exception{
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.platformAppService.deviceRealTimeGet(sn);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
