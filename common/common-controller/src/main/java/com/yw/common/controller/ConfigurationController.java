package com.yw.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.entity.ConfigurationEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 字典数据表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 13:17:22
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("configuration")
@Controller
public class ConfigurationController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IConfigurationService configurationService;//字典数据表
    
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
	 * 日       期: 2017-03-13 13:17:22
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, ConfigurationEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configurationService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月5日下午2:45:46
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("insertConfiguration")
    @ResponseBody
    public ResultUtil insertConfigurationV100(HttpServletRequest request,
    		HttpServletResponse response, ConfigurationEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configurationService.insertConfiguration(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年6月21日上午9:28:10
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("sysParam")
    public String sysParamV200(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	try {
    		Map<String, String> map = this.configurationService.sysParam();
    		request.setAttribute("map", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "sysParam";
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月5日下午2:45:46
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("changeLogLevel")
    @ResponseBody
    public ResultUtil changeLogLevelV200(HttpServletRequest request,
    		HttpServletResponse response, String logLevel) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		if (StringUtils.isBlank(logLevel)) {
    			log.debug("debug");
    			log.info("info");
    			return resultUtil.setData("logLevel").setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS);
			}
    		Logger logger = Logger.getLogger(logLevel);
    		logger.setLevel(Level.toLevel(logLevel));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil.setData(logLevel).setCode(ErrorTypeEnum.SUCCESS);
    }
}
