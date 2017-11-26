package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.PushMsgEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IPushMsgService;

/**
 *<pre>
 * 功       能: 推送消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("pushMsg")
@Controller
public class PushMsgController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IPushMsgService pushMsgService;//推送消息
    
	/**
	 * <pre>
	 * 说       明: 推送MQTT消息
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月14日下午4:46:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("pushMQTT")
    @ResponseBody
    public ResultUtil pushMQTTV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody PushMQTTEntity entity ) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.pushMsgService.pushMQTT(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
 
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, PushMsgEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.pushMsgService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
