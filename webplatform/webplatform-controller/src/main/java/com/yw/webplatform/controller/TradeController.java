package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.ITradeService;

/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("trade")
@Controller
public class TradeController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public ITradeService tradeService;//厂商
    
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, TradeEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.tradeService.findPage(entity, page);
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
     * 日       期: 2017年6月15日 上午9:55:13
     * Q     Q: 982234234
     * </pre>
     */
    @RequestMapping("findAll")
    @ResponseBody
    public ResultUtil findAllV200(HttpServletRequest request,
    		HttpServletResponse response, TradeEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.tradeService.findAll(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
