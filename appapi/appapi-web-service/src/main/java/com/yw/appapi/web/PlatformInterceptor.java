package com.yw.appapi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yw.appapi.controller.BaseController;
import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAuthLogService;

/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年4月28日下午2:26:19
 * Q    Q: 308053847
 * </pre>
 */
public class PlatformInterceptor extends BaseController implements
		HandlerInterceptor {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	public static List<String> excludeUrls; // 不需要拦截的URL
	@Autowired
	private IAuthLogService authLogService;//
	
	/**
	 * <pre>
	 * 说       明: 调用方法前执行
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月29日上午10:36:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.info("==============执行顺序: 1、preHandle================");
		String basePath = UrlUtil.getRequestUrl(request);
		String action = UrlUtil.getAction(request);

		log.info("访问了：" + basePath);

		if (excludeUrls.contains(action)) {// 判断权限:针对不需要认证的接口
			return true;
		}
		if (Boolean.valueOf(ConfigurationEnum.DEBUG_MODEL.getValue())) {
			log.info("调试模式开启");
			return true;
		}
		// 根据会话验证访问接口权限
		String token = UrlUtil.getRequestParameter(request).get("token") + "";
		if (StringUtils.isBlank(token)) {
			
			AppResultUtil resultUtil = new AppResultUtil();
			resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("token");//缺少参数
			PrintWriteUtil.printJsonNoSign(response, resultUtil);
			return false;
		}
		AuthLogEntity authLog = this.authLogService.findVaildToken(new AuthLogEntity(null, null,token));
		if (StringUtils.isBlank(authLog)) {
			
			AppResultUtil resultUtil = new AppResultUtil();
			resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_SHIRO_TOKEN);//认证失败
			PrintWriteUtil.printJsonNoSign(response, resultUtil);
			return false;
		}
		authLog.setTokenTimeout(DateUtils.getDateAfterHour(null, 
				Integer.valueOf(ConfigurationEnum.AUTH_TOKEN_HOUR.getValue())));
		this.authLogService.update(authLog);//自动续时
		return true;
	}
	
	/**
	 * <pre>
	 * 说       明: 调用方法结束后但渲染视图之前执行
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月29日上午10:37:27
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		 log.info("==============执行顺序: 2、postHandle================");    
	}

	/**
	 * <pre>
	 * 说       明: 调用方法结束后执行
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月29日上午10:37:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		 log.info("==============执行顺序: 3、afterCompletion================");    

	}


	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		try {

			this.excludeUrls = excludeUrls;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
