package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IDeviceAttributeService;

/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-22 10:59:29
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("deviceAttribute")
@Controller
public class DeviceAttributeController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IDeviceAttributeService deviceAttributeService;//设备属性
    
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
	 * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, DeviceAttributeEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.deviceAttributeService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
