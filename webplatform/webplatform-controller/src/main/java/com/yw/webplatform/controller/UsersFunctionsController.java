package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IUsersFunctionsService;

/**
 *<pre>
 * 功       能: 权限表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("usersFunctions")
@Controller
public class UsersFunctionsController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IUsersFunctionsService usersFunctionsService;//权限表
    
	/**
	 *<pre>
	 * 说       明: 查询数据
	 * @param request
	 * @param response
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.findPage(entity, page);
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
     * 日       期: 2017年4月25日下午3:48:13
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.findAll(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 新增
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午3:48:13
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("insertFunction")
    @ResponseBody
    public ResultUtil insertFunctionV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.insertFunction(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除权限信息
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午3:48:13
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("deleteFunctions")
    @ResponseBody
    public ResultUtil deleteFunctionsV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.deleteFunctions(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 修改权限信息
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午3:48:13
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("updateFunctions")
    @ResponseBody
    public ResultUtil updateFunctionsV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.updateFunctions(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 权限修改前查询
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午3:48:13
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findParent")
    @ResponseBody
    public ResultUtil findParentV200(HttpServletRequest request,
    		HttpServletResponse response, UsersFunctionsEntity entity, String rolesId) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersFunctionsService.findParent(entity,rolesId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
