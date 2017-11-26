package com.yw.appapi.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yw.appapi.api.IControlLogService;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: 切面类，在切面类中获取参数和返回值
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年4月28日下午2:26:19
 * Q    Q: 308053847
 * </pre>
 */
public class ControlLogAspect{

	
	@Autowired
	private IControlLogService controlLogService;

	//这里的返回值就是业务方法的返回值
	//ProceedingJoinPoint（执行时的切入点）可以获取业务方法的相关信息，参数，返回值，执行方法
	public Object getParamAndResult(ProceedingJoinPoint joinPoint) throws Throwable {
		//获取token
		if (StringUtils.isBlank(RequestContextHolder.getRequestAttributes())) {
			Object[] args = joinPoint.getArgs();
			return joinPoint.proceed(args);
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getParameter("token");
		//获取请求参数
		Object[] args = joinPoint.getArgs();
		//执行业务方法，获取返回值
		Object result = joinPoint.proceed(args);
		//将参数和返回值插入t_control_log表中
		try {
			AppResultUtil logResult = controlLogService.insert(args, result, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
