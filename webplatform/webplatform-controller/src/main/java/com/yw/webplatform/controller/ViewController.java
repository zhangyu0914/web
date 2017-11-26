package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IViewService;

/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("view")
@Controller
public class ViewController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IViewService viewService;//预览

	/**
	 * <pre>
	 * 说       明: 应用异常状态、设备异常状态
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月8日下午5:04:06
	 * Q    Q: 308053847
	 * </pre>
	 */
    @RequestMapping("findWarningReport")
    @ResponseBody
    public ResultUtil findWarningReportV200(HttpServletRequest request,
    		HttpServletResponse response, HomePageEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.viewService.findWarningReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 应用信息、设备信息、设备信息、设备上线总数
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月8日下午5:04:06
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findAppDeviceReport")
    @ResponseBody
    public ResultUtil findAppDeviceReportV200(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.viewService.findAppDeviceReport(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 平台负载、内存/硬盘/CPU、推送信息量报表
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月8日下午5:04:06
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findPlatformReport")
    @ResponseBody
    public ResultUtil findPlatformReportV200(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.viewService.findPlatformReport(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 平台对应用/设备输入输出流量、接口访问量报表
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月8日下午5:04:06
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findIOAndInterface")
    @ResponseBody
    public ResultUtil findIOAndInterfaceV200(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.viewService.findIOAndInterface(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
