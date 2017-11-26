package com.yw.common.web;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.SpringUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.md5.MD5Util;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.common.controller.BaseController;

/**
 * <pre>
 * 功   能: 
 * 创建者: 陈    林(Vickey)
 * 日   期: 2014-7-21下午4:39:16
 * Q  Q: 308053847
 * </pre>
 */
public class SSOInterceptor extends BaseController implements
		HandlerInterceptor {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	public static List<String> excludeUrls; // 不需要拦截的URL
	public static final String SIGN = "sign";
	public static final String TOKEN = "token";
	
	/**
	 * <pre>
	 * 说       明: 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-16下午6:20:35
	 * </pre>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String basePath = UrlUtil.getRequestUrl(request);
		String action = UrlUtil.getAction(request);
		return true;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午2:21:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	private boolean validateToken(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		ResultUtil resultUtils = new ResultUtil();
		/**
		 * 判断参数加密合法性
		 */
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();

		// 只判断带参数的URL
		if (request.getParameterMap() != null) {

			// 参数合法性验证
			String tokenValue = StringUtils.getEnumeration(request).get(TOKEN);
			if (StringUtils.isBlank(tokenValue)) {

				log.error("没有TOKEN参数");
				PrintWriteUtil.printJsonNoSign(response, 
						resultUtils.setCode(ErrorTypeEnum.FAILURE_BASE_HEADER_LACK_PARAMS).setData("token"));
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午2:21:16
	 * Q    Q: 308053847
	 * </pre>
	 */
	private boolean validateSign(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		ResultUtil resultUtils = new ResultUtil();
		/**
		 * 判断参数加密合法性
		 */
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Map<String, String> paramsMap = new LinkedHashMap<String, String>();
		String md5Params = "";

		// 只判断带参数的URL
		if (request.getParameterMap() != null) {

			String controller = SpringUtil.getController(handlerMethod
					.getBean().getClass());
			String[] v = handlerMethod.getMethod()
					.getAnnotation(RequestMapping.class).value();
			if (v == null || v.length == 0 || controller == null
					|| controller.equals("")) {
				return false;
			}
			String methodName = v[0];
			String propertiesParams = ErrorTypeEnum.getMethodParams(controller
					+ "/" + methodName);

			// 只判断已配置方法参数加密的URL
			if (propertiesParams != null && !propertiesParams.equals("")) {

				String[] params = propertiesParams.split(",");
				Map<String, String[]> pmap = request.getParameterMap();
				String keyTemp = null;
				for (Iterator<String> iterator = pmap.keySet().iterator(); iterator
						.hasNext();) {
					keyTemp = iterator.next();
					paramsMap.put(keyTemp,
							pmap.get(keyTemp) != null ? pmap.get(keyTemp)[0]
									: null);
				}

				for (String str : params) {
					md5Params += str + "=" + paramsMap.get(str);
				}
				String serverSign = methodName + md5Params
						+ UrlUtil.getSign(null, null);
				// 参数合法性验证
				String paramsMD5 = MD5Util.md5(serverSign);
				String clientSignMD5 = paramsMap.get(SIGN);
				if (!paramsMD5.equals(clientSignMD5)) {

					log.info("服务端sign加密前的值：" + serverSign);
					log.info("服务端sign加密后的值：" + paramsMD5);
					log.info("客户端sign加密后的值：" + clientSignMD5);

					log.error("SIGN签名失败");
					resultUtils.setData(paramsMap.get(SIGN) + "[sin值应该是:"
							+ paramsMD5 + "]");
					UrlUtil.printJson(response, resultUtils,
							UrlUtil.getSign(null, null));
					return false;
				}
			}
		}
		return true;
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

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}


}
