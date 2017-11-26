package com.yw.appapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *<pre>
 * 功       能: 专门用于返回视图的控制器
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年7月3日上午11:36:39
 * Q    Q: 1784286916
 *</pre>
 */
//@RequestMapping("demo")
@Controller
public class DemoController extends BaseController {

	/**
	 * 
	*<pre>
	* 说      明:  返回index.jsp视图
	* @param index
	* @return String
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日下午12:35:16
	*</pre>
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request) throws Exception{
		return "redirect:/index.jsp";
	}
	
	/**
	 * 
	*<pre>
	* 说      明: 返回index.jsp视图 
	* @param info
	* @return String
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日下午12:36:24
	*</pre>
	 */
	@RequestMapping("info")
	public String info(HttpServletRequest request) throws Exception{
		return "info";
	}
	
	/**
	 * 
	*<pre>
	* 说      明:  返回control.jsp视图
	* @param control
	* @return String
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日下午1:35:08
	*</pre>
	 */
	@RequestMapping("control")
	public String control(HttpServletRequest request) throws Exception{
		return "control";
	}
	
	/**
	 * 
	*<pre>
	* 说      明:  返回devicebind.jsp视图
	* @param devicebind
	* @return String
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日下午5:08:40
	*</pre>
	 */
	@RequestMapping("devicebind")
	public String devicebind(HttpServletRequest request) throws Exception{
		return "devicebind";
	}
	
	/**
	 * 
	*<pre>
	* 说      明:  返回devicebinddel视图
	* @param devicebinddel
	* @return String
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日下午5:09:14
	*</pre>
	 */
	@RequestMapping("devicebinddel")
	public String devicebinddel(HttpServletRequest request) throws Exception{
		return "devicebinddel";
	}
	
	/**
	 * <pre>
	 * 说       明: 返回appStatusSet视图
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月10日 下午5:42:20
	 * Q     Q: 982234234
	 * </pre>
	 */
	@RequestMapping("appStatusSet")
	public String appStatusSet(HttpServletRequest request) throws Exception{
		return "appStatusSet";
	}
	
	/**
	 * <pre>
	 * 说       明: 返回appStatusPost视图
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月10日 下午5:42:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	@RequestMapping("appStatusPost")
	public String appStatusPost(HttpServletRequest request) throws Exception{
		return "appStatusPost";
	}
	

}
