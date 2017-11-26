package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IAppLicenseService;

/**
 *<pre>
 * 功       能: 型号许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("appLicense")
@Controller
public class AppLicenseController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IAppLicenseService appLicenseService;//型号许可证表
    
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, AppLicenseEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appLicenseService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
