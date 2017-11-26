package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IAttributeService;

/**
 *<pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:06
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("attribute")
@Controller
public class AttributeController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IAttributeService attributeService;//属性表
    
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
    		HttpServletResponse response, AttributeEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.attributeService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 添加
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年5月12日 下午2:32:08
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("insertAttribute")
    @ResponseBody
    public ResultUtil insertAttributeV200(HttpServletRequest request,
    		HttpServletResponse response, AttributeEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.attributeService.insertAttribute(entity);
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
    @RequestMapping("deleteAttribute")
    @ResponseBody
    public ResultUtil deleteAttributeV200(HttpServletRequest request,
    		HttpServletResponse response, AttributeEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.attributeService.deleteAttribute(entity);
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
    @RequestMapping("updateAttribute")
    @ResponseBody
    public ResultUtil updateAttributeV200(HttpServletRequest request,
    		HttpServletResponse response, AttributeEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.attributeService.updateAttribute(entity);
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
     * 日       期: 2017年5月15日 上午9:37:50
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV200(HttpServletRequest request,
    		HttpServletResponse response, AttributeEntity entity,String modelId) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.attributeService.findAll(entity,modelId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
