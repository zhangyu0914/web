package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IOrganizationService;

/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:15
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("organization")
@Controller
public class OrganizationController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IOrganizationService organizationService;//机构
    
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
	 * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV100(HttpServletRequest request,
    		HttpServletResponse response, OrganizationEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.organizationService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     *<pre>
     * 说       明: 添加
     * 涉及版本: V1.0.0
     * 创  建  者: zhangyu
     * 日       期: 2017年5月11日 上午11:33:59
     * Q     Q: 982234234
     *</pre>
     */
    @RequestMapping("insertOrganization")
    @ResponseBody
    public ResultUtil insertOrganzationV200(HttpServletRequest request,
    		HttpServletResponse response, OrganizationEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.organizationService.insertOrganization(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     *<pre>
     * 说       明: 删除
     * 涉及版本: V1.0.0
     * 创  建  者: zhangyu
     * 日       期: 2017年5月11日 上午11:33:20
     * Q     Q: 982234234
     *</pre>
     */
    @RequestMapping("deleteOrganization")
    @ResponseBody
    public ResultUtil deleteOrganizationV200(HttpServletRequest request,
    		HttpServletResponse response, OrganizationEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.organizationService.deleteOrganization(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     *<pre>
     * 说       明: 更新
     * 涉及版本: V1.0.0
     * 创  建  者: zhangyu
     * 日       期: 2017年5月11日 上午11:32:36
     * Q     Q: 982234234
     *</pre>
     */
    @RequestMapping("updateOrganization")
    @ResponseBody
    public ResultUtil updateUserInfoV200(HttpServletRequest request,
    		HttpServletResponse response, OrganizationEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.organizationService.updateOrganization(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
