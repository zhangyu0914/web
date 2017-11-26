package com.yw.webplatform.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.utils.UrlUtil;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IInterfaceLogService;

public class InterfaceLogInterceptor {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
	private IInterfaceLogService interfaceLogService;

	public Object logExecuteTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		if (StringUtils.isBlank(RequestContextHolder.getRequestAttributes())) {//方便JUNIT调试
			return joinPoint.proceed(args);
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String action = UrlUtil.getAction(request);
		String token = request.getParameter("token");
		//开始时间
		long startTime = System.currentTimeMillis();
		
		Object obj=joinPoint.proceed(args);
		if (StringUtils.isBlank(token)) {
			return obj;
		}
		//结束时间
		long endTime = System.currentTimeMillis();
		int executeTime=(int) (endTime-startTime);
		log.info(" URL "+joinPoint.getTarget().getClass() + "." + " 耗时 "+ (endTime - startTime) + "ms");
		InterfaceLogEntity entity=new InterfaceLogEntity();
		entity.setTimeConsuming(executeTime);
		this.interfaceLogService.interfaceLog(entity, token, action);
		return obj;
	}
}
