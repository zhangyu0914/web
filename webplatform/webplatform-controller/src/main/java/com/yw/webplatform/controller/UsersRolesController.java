package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IUsersRolesService;

/**
 *<pre>
 * 功       能: 角色表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("usersRoles")
@Controller
public class UsersRolesController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IUsersRolesService usersRolesService;//角色表
    
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午4:11:09
	 * Q    Q: 308053847
	 * </pre>
	 */
    @RequestMapping("insertRoles")
    @ResponseBody
    public ResultUtil insertRolesV200(HttpServletRequest request,
    		HttpServletResponse response, UsersRolesEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersRolesService.insertRoles(entity);
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
     * 日       期: 2017年4月25日下午4:51:28
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, UsersRolesEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersRolesService.findPage(entity, page);
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
     * 日       期: 2017年4月25日下午4:51:25
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV200(HttpServletRequest request,
    		HttpServletResponse response, UsersRolesEntity entity,String usersId) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersRolesService.findAll(entity, usersId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除角色
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午4:51:25
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("deleteRoles")
    @ResponseBody
    public ResultUtil deleteRolesV200(HttpServletRequest request,
    		HttpServletResponse response, UsersRolesEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersRolesService.deleteRoles(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 修改角色信息
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午4:51:25
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("updateRoles")
    @ResponseBody
    public ResultUtil updateRolesV200(HttpServletRequest request,
    		HttpServletResponse response, UsersRolesEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.usersRolesService.updateRoles(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
