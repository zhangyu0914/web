package com.yw.webplatform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.excel.ExcelReader;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.ILicenseService;

/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:07
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("license")
@Controller
public class LicenseController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public ILicenseService licenseService;//许可证表
    
	/**
	 * <pre>
	 * 说       明: 应用许可证报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:12:29
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("appLicenseReport")
    @ResponseBody
    public ResultUtil appLicenseReportV100(HttpServletRequest request,
    		HttpServletResponse response, HomePageEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.licenseService.appLicenseReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备许可证报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:12:29
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("deviceLicenseReport")
    @ResponseBody
    public ResultUtil deviceLicenseReportV100(HttpServletRequest request,
    		HttpServletResponse response, HomePageEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.licenseService.deviceLicenseReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 应用许可证查询接口
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:59:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("findAppLicense")
    @ResponseBody
    public ResultUtil findAppLicenseV100(HttpServletRequest request,
    		HttpServletResponse response, LicenseEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.licenseService.findAppLicense(entity,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备许可证查询接口
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:59:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("findDeviceLicense")
    @ResponseBody
    public ResultUtil findDeviceLicenseV100(HttpServletRequest request,
    		HttpServletResponse response, LicenseEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.licenseService.findDeviceLicense(entity,page);
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
    		HttpServletResponse response, LicenseEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.licenseService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
