package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IInterfaceService;

/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:14
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("interface")
@Controller
public class InterfaceController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IInterfaceService interfaceService;//接口
    
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, InterfaceEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		page.setPageSize(100000);
    		resultUtil = this.interfaceService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 添加
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月24日下午3:09:50
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("insertInterface")
    @ResponseBody
    public ResultUtil insertInterfaceV200(HttpServletRequest request,
    		HttpServletResponse response, InterfaceEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.interfaceService.insertInterface(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 更新
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年5月22日 下午4:39:27
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("updateInterface")
    @ResponseBody
    public ResultUtil updateInterfaceV200(HttpServletRequest request,
    		HttpServletResponse response, InterfaceEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.interfaceService.updateInterface(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除
     * 涉及版本: V2.0.0 
     * 创  建  者: zhangyu
     * 日       期: 2017年5月22日 下午4:39:57
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("deleteInterface")
    @ResponseBody
    public ResultUtil deleteInterfaceV200(HttpServletRequest request,
    		HttpServletResponse response, InterfaceEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.interfaceService.deleteInterface(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
