package com.yw.webplatform.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Option;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IHomePageService;


/**
 * <pre>
 * 功       能: 首页
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年3月14日下午12:54:57
 * Q    Q: 308053847
 * </pre>
 */
@RequestMapping("homePage")
@Controller
public class HomePageController extends BaseController {
	
	@Autowired
	private IHomePageService reportService;//
	
	/**
	 * <pre>
	 * 说       明: 首页应用
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午12:55:08
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping(value = "appReport")
	@ResponseBody
	public ResultUtil appReportV100(HttpServletRequest request,
			HttpServletResponse response, HomePageEntity entity) {
		ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.reportService.appReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
	}
	
	/**
	 * <pre>
	 * 说       明: 首页设备
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午12:55:14
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("deviceReport")
    @ResponseBody
    public ResultUtil deviceReportV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.reportService.deviceReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 首页平台负载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午6:24:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("platformReport")
    @ResponseBody
    public ResultUtil platformReportV100(HttpServletRequest request,
    		HttpServletResponse response, PlatformDataEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.reportService.platformReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 首页平台IO/设备IO
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午6:24:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("ioReport")
    @ResponseBody
    public ResultUtil ioReportV100(HttpServletRequest request,
    		HttpServletResponse response, HomePageEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.reportService.ioReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
