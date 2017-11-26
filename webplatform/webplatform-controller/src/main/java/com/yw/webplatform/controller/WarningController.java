package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IWarningService;

/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:06
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("warning")
@Controller
public class WarningController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IWarningService warningService;//报警表
    
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
    		HttpServletResponse response, WarningEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.warningService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 警告数据修改
     * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年3月13日下午9:49:09
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("warningChange")
    @ResponseBody
    public ResultUtil warningChangeV100(HttpServletRequest request,
    		HttpServletResponse response, WarningEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.warningService.warningChange(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月5日上午9:57:26
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("getWarningCount")
    @ResponseBody
    public ResultUtil getWarningCountV100(HttpServletRequest request,
    		HttpServletResponse response, WarningEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.warningService.getWarningCount(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
