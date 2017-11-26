package com.yw.common.beansUtils.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yw.common.beansUtils.utils.resultUtil.IhygeiaResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.common.beansUtils.utils.validation.ValidationResult;
import com.yw.common.beansUtils.utils.validation.ValidationUtils;

/**
 *<pre>
 * 功       能: UUID工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class SpringUtil {
	
	private static Logger log = Logger.getLogger(SpringUtil.class);
	
	/**
	 * <pre>
	 * 说       明: 获取SPRING的REQUEST TODO SPRINGUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-7下午3:35:25
	 * </pre>
	 */
	public static HttpServletRequest getHttpServletReqeust() throws Exception {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}
	
	/**
	 * <pre>
	 * 说       明:  TODO SPRINGUTIL
	 * @param c
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-11-11上午9:44:56
	 * </pre>
	 */
	public static String getController(Class c) {
		RequestMapping requestMapping = (RequestMapping) c
				.getAnnotation(RequestMapping.class);
		String s = requestMapping.value()[0];
		return s.replace("interface/", "");
	}
	
	/**
	 * <pre>
	 * 说       明: 验证信息 TODO SPRINGUTIL
	 * @param errors
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-22下午3:47:33
	 * </pre>
	 */
	public static IhygeiaResultUtil validate(Object... objArray) throws Exception {
		if (!StringUtils.isBlankOr(objArray)) {
			for (Object obj : objArray) {

				ValidationResult vr = ValidationUtils.validateEntity(obj);
				if (!vr.isHasErrors()) {
					return null;
				}
				return new IhygeiaResultUtil().setMsg(vr.getErrorMsg().values()
						.toArray()[0].toString());
			}
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 说       明: 根据URL得到所属模块
	 * @param request TODO SPRINGUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-14下午7:10:24
	 * </pre>
	 */
	public static String getModule(HttpServletRequest request) throws Exception {

		String action = UrlUtil.getAction(request);
		char[] x = action.split("/")[2].toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < x.length; i++) {
			if (Character.isUpperCase(x[i])) {
				break;
			}
			sb.append(x[i]);
		}
		return sb.toString().toUpperCase();
	}
	
	
	
	
}
