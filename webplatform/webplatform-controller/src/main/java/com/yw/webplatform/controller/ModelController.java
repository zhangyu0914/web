package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IModelService;

/**
 *<pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:06
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("model")
@Controller
public class ModelController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IModelService modelService;//设备型号表
    
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
    		HttpServletResponse response, ModelEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.modelService.findPage(entity, page);
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
     * 日       期: 2017年5月15日 上午9:33:36
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV200(HttpServletRequest request,
    		HttpServletResponse response, ModelEntity entity,String attId) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.modelService.findAll(entity,attId);
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
     * 日       期: 2017年5月15日 下午2:47:12
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("deleteModel")
    @ResponseBody
    public ResultUtil deleteModelV200(HttpServletRequest request,
    		HttpServletResponse response, ModelEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.modelService.deleteModel(entity);
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
     * 日       期: 2017年5月15日 下午2:47:12
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("updateModel")
    @ResponseBody
    public ResultUtil updateModelV100(HttpServletRequest request,
    		HttpServletResponse response, ModelEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.modelService.updateModel(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 查询
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年6月15日 上午10:01:41
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("findAllModel")
    @ResponseBody
    public ResultUtil findAllModelV200(HttpServletRequest request,
    		HttpServletResponse response, ModelEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.modelService.findAllModel(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
}
