package com.yw.appapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.appapi.api.IControlLogService;
import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;


/**
 *<pre>
 * 功       能: 对外提供controlLog访问接口
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年7月1日下午8:39:07
 * Q    Q: 1784286916
 *</pre>
 */
@RequestMapping("controlLog")
@Controller
public class ControlLogController extends BaseController {
	
	@Autowired
	private IControlLogService controlLogService;
}
