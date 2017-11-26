package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IUserInfoService;

/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:18
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("userInfo")
@Controller
public class UserInfoController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IUserInfoService userInfoService;//用户表
    
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
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 登录
     * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年3月13日下午1:04:41
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultUtil loginV100(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.login(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 退出
     * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年3月13日下午1:04:35
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("loginOut")
    @ResponseBody
    public ResultUtil loginOutV100(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.loginOut(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 添加用户
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午4:26:38
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("insertUserInfo")
    @ResponseBody
    public ResultUtil insertUserInfoV200(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.insertUserInfo(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除用户
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午4:26:38
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("deleteUserInfo")
    @ResponseBody
    public ResultUtil deleteUserInfoV200(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.deleteUserInfo(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 修改用户信息
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月25日下午4:26:38
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public ResultUtil updateUserInfoV200(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.updateUserInfo(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年6月1日 下午4:47:22
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("userCheck")
    @ResponseBody
    public ResultUtil userCheckV200(HttpServletRequest request,
    		HttpServletResponse response, UserInfoEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.userInfoService.userCheck(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
