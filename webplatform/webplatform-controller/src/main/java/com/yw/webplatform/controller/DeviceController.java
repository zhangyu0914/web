package com.yw.webplatform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.entity.RouteEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceService;

/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:06
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("device")
@Controller
public class DeviceController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IDeviceService deviceService;//设备表
	
	/**
	 * <pre>
	 * 说       明: 设备配置查询
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:11:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("changeSetting")
    @ResponseBody
    public ResultUtil changeSettingV100(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody List<DeviceEntity> data) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		if (StringUtils.isBlank(data)) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
    		List<DeviceEntity> list = JavaBeanUtil.jsonToList(JavaBeanUtil.javaBeanToString(data), DeviceEntity.class);
    		resultUtil = this.deviceService.changeSetting(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备配置查询
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:11:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("findSetting")
    @ResponseBody
    public ResultUtil findSettingV100(HttpServletRequest request,
    		HttpServletResponse response, DeviceEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.deviceService.findSetting(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备菜单感知终端报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:19:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("deviceViewReport")
    @ResponseBody
    public ResultUtil deviceViewReportV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.deviceService.deviceViewReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备网关设备报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:19:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("gatewayReport")
    @ResponseBody
    public ResultUtil gatewayReportV100(HttpServletRequest request,
    		HttpServletResponse response, AppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.deviceService.gatewayReport(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: 设备路由
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:50:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("findDeviceRoute")
	@ResponseBody
	public ResultUtil findDeviceRouteV100(HttpServletRequest request,
			HttpServletResponse response, RouteEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.deviceService.findDeviceRoute(entity);
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
	 * 日       期: 2017-03-13 21:44:06
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, DeviceEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.deviceService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
