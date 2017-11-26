package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IAppService;

/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:07
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("app")
@Controller
public class AppController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IAppService appService;//应用表
    
	/**
	 * <pre>
	 * 说       明: APP功能APP报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:09:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("appReport")
    @ResponseBody
    public ResultUtil appReportV100(HttpServletRequest request,
    		HttpServletResponse response, AppLicenseEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.appReport(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: APP功能I0统计报表接口多个应用统计
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:10:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("appIOReport")
    @ResponseBody
    public ResultUtil appIOReportV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.appIOReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: I0统计报表接口单个应用分时间统计
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:10:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("appDetailReport")
	@ResponseBody
	public ResultUtil appDetailReportV100(HttpServletRequest request,
			HttpServletResponse response, AppLicenseEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.appService.appDetailReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	/**
	 * <pre>
	 * 说       明: APP拥有设备的权限
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:50:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("findAppFunction")
	@ResponseBody
	public ResultUtil findAppFunctionV100(HttpServletRequest request,
			HttpServletResponse response, String tid, InterfacePage<AppEntity> page) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.appService.findAppFunction(tid,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	@RequestMapping("findAppDevice")
	@ResponseBody
	public ResultUtil findAppDeviceV100(HttpServletRequest request,
			HttpServletResponse response, String fkAppTid, InterfacePage<DeviceEntity> page) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.appService.findAppDevice(fkAppTid,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	/**
	 *<pre>
	 * 说       明: 查询数据
	 * @param request
	 * @param response
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 21:44:07
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 查询
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年6月15日 下午3:01:24
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("findApp")
    @ResponseBody
    public ResultUtil findAppV200(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.findApp(entity, page);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
	 *<pre>
	 * 说       明: 查询数据
	 * @param request
	 * @param response
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 21:44:07
	 *</pre>
	 */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.findAll(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
	 * <pre>
	 * 说       明: 查询所有应用名称
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月7日 下午3:23:20
	 * Q     Q: 982234234
	 * </pre>
	 */
    @RequestMapping("findAllAppName")
    @ResponseBody
    public ResultUtil findAllAppNameV200(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.findAllAppName(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 根据应用ID查询版本号
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年7月7日下午3:11:26
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findVersionByAppId")
    @ResponseBody
    public ResultUtil findVersionByAppIdV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appService.findVersionByAppId(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
