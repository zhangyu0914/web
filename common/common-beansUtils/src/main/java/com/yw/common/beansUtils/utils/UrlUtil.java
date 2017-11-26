package com.yw.common.beansUtils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.beansUtils.utils.des.QEncodeUtil;
import com.yw.common.beansUtils.utils.resultUtil.IhygeiaResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功       能: URL请求工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:22:26
 * Q    Q: 308053847
 * </pre>
 */
public class UrlUtil {

	private static String ENCODING_UTF8 = "UTF-8";
	private static Logger log = Logger.getLogger(UrlUtil.class);

	/**
	 * <pre>
	 * 说   明:
	 * @param reqUrl TODO URLUTIL
	 * @param parameters
	 * @param signKey 
	 * @return
	 * @throws Exception 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-3上午10:00:39
	 * </pre>
	 */
	public  static String post(String reqUrl,
			Map<String, Object> parameters, String signKey) throws Exception {
		HttpURLConnection urlConn = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = parameters.get(key);
				if (value == null) {
					continue;
				}
				if (value.getClass().isArray()) {
					Object[] objs = (Object[]) value;
					for (Object o : objs) {
						params.append(key.toString());
						params.append("=");
						params.append(o.toString());
						params.append("&");
					}
				} else {
					params.append(key.toString());
					params.append("=");
					params.append(value.toString());
					params.append("&");
				}
			}
			String methodName = null;
			String methodUrl = null;
			if (reqUrl != null) {
				methodName = reqUrl.substring(reqUrl.lastIndexOf("/") + 1,
						reqUrl.lastIndexOf("."));// 截取方法名
				methodUrl = StringUtils.getSubStr(reqUrl, 2);
				if (methodUrl.contains(Constants.CHAR_ACTION)) {
					methodUrl = methodUrl.replaceAll(Constants.CHAR_ACTION, "");
				}
				if (methodUrl.contains("/")) {
					methodUrl = methodUrl.replaceAll("/", "");
				}
			}

			if (!StringUtils.isBlank(signKey)) {
				String sign = SignUtil.getSign(methodName, methodUrl,
						parameters, signKey);
				params.append(Constants.SIGN + "=").append(sign);
			}
			log.info(reqUrl + "?" + URLDecoder.decode(params.toString()));
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(60000);
			urlConn.setReadTimeout(60000);
			urlConn.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			log.info("REQUEST BODY : ".concat(params.toString()));
			urlConn.getOutputStream().write(b, 0, b.length);
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();

			InputStream in = urlConn.getInputStream();
			log.debug("RESPONSE_CODE IS " + urlConn.getResponseCode());
			System.out.println("RESPONSE_CODE IS " + urlConn.getResponseCode()+"   length:"+urlConn.getContentLength());
			byte[] bytes = NetTool.read(in);
			System.out.println("----------------------RESPONSE----------------------"+bytes.length);
			responseContent = new String(bytes, "utf-8");
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		return responseContent;
	}
	
	public  static String post(String reqUrl, String parameters) throws Exception {
		HttpURLConnection urlConn = null;
		String responseContent = null;
		try {
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(60000);
			urlConn.setReadTimeout(60000);
			urlConn.setDoOutput(true);
			if (!StringUtils.isBlank(parameters)) {
				
				byte[] b = parameters.toString().getBytes();
				log.info("REQUEST BODY : ".concat(parameters.toString()));
				urlConn.getOutputStream().write(b, 0, b.length);
			}
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();

			InputStream in = urlConn.getInputStream();
			log.debug("RESPONSE_CODE IS " + urlConn.getResponseCode());
			System.out.println("RESPONSE_CODE IS " + urlConn.getResponseCode()+"   length:"+urlConn.getContentLength());
			byte[] bytes = NetTool.read(in);
			System.out.println("----------------------RESPONSE----------------------"+bytes.length);
			responseContent = new String(bytes, "utf-8");
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		return responseContent;
	}

	/**
	 * <pre>7
	 * 说       明: 
	 * @param reqUrl TODO URLUTIL
	 * @param params
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:17:14
	 * </pre>
	 */
	public static String postNoSecret(String reqUrl, String params)
			throws Exception {
		HttpURLConnection urlConn = null;
		String responseContent = null;
		try {
			URL url = null;
			if (!StringUtils.isBlank(params)) {
				url = new URL(reqUrl + "?" +  params);
			}else{
				url = new URL(reqUrl);
			}
			log.info("---url---" + url);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(30000);
			urlConn.setReadTimeout(30000);
			urlConn.setDoOutput(true);
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();

			InputStream in = urlConn.getInputStream();
			byte[] bytes = NetTool.read(in);
			responseContent = new String(bytes);

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		return responseContent;
	}

	/**
	 * <pre>
	 * 说       明: 不需要SIGN的POST请求 TODO URLUTIL
	 * @param reqUrl
	 * @param parameters
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-14下午12:01:22
	 * </pre>
	 */
	public static String postNoSecret(String reqUrl, Map<String, ?> parameters,
			boolean isAllEncode) throws Exception {
		String paramsStr = "";
		if (null != parameters && parameters.size() > 0) {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = parameters.get(key);
				if (value == null) {
					continue;
				}
				if (value.getClass().isArray()) {
					Object[] objs = (Object[]) value;
					for (Object o : objs) {
						params.append(key.toString());
						params.append("=");
						if (!isAllEncode) {
							params.append(URLEncoder.encode(o.toString(),
									Constants.ENCODING_UTF8));
						} else {
							params.append(o.toString());
						}
						params.append("&");
					}
				} else {
					params.append(key.toString());
					params.append("=");
					if (!isAllEncode) {
						params.append(URLEncoder.encode(value.toString(),
								Constants.ENCODING_UTF8));
					} else {
						params.append(value.toString());
					}
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}

			paramsStr = params.toString();
			params = null;
			if (isAllEncode) {
				paramsStr = URLEncoder.encode(paramsStr,
						Constants.ENCODING_UTF8);
			}
		}
		return postNoSecret(reqUrl, paramsStr);
	}
	
	/**
	 *<pre>
	 * 说       明: 不设置参数的URL请求
	 * @param reqUrl
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-19上午11:36:06
	 *</pre>
	 */
	public  static String postNoSecret(String reqUrl) throws Exception {
		HttpURLConnection urlConn = null;
		String responseContent = null;
		try {
			
			URL url = new URL(reqUrl.trim());
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setConnectTimeout(5000);
			urlConn.setReadTimeout(15000);
			urlConn.setDoOutput(true);
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();

			InputStream in = urlConn.getInputStream();
			log.debug("RESPONSE_CODE IS " + urlConn.getResponseCode());
			byte[] bytes = NetTool.read(in);
			responseContent = new String(bytes);

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		return responseContent;
	}

	/**
	 * 获取完整请求URL TODO URLUTIL
	 * 
	 * @param reqUrl
	 * @param parameters
	 * @param isAllEncode
	 * @return
	 * @throws Exception
	 */
	public static String getParamsStr(Map<String, ?> parameters,
			boolean isAllEncode) throws Exception {

		String paramsStr = "";
		if (null != parameters && parameters.size() > 0) {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = parameters.get(key);
				if (value == null) {
					continue;
				}
				if (value.getClass().isArray()) {
					Object[] objs = (Object[]) value;
					for (Object o : objs) {
						params.append(key.toString());
						params.append("=");
						if (!isAllEncode) {
							params.append(URLEncoder.encode(o.toString(),
									Constants.ENCODING_UTF8));
						} else {
							params.append(o.toString());
						}
						params.append("&");
					}
				} else {
					params.append(key.toString());
					params.append("=");
					if (!isAllEncode) {
						params.append(URLEncoder.encode(value.toString(),
								Constants.ENCODING_UTF8));
					} else {
						params.append(value.toString());
					}
					params.append("&");
				}
			}
			if (params.length() > 0) {
				params.deleteCharAt(params.length() - 1);
			}

			paramsStr = params.toString();
			log.info("编码之前的请求URL参数[" + paramsStr + "]");
			params = null;
			if (isAllEncode) {
				paramsStr = URLEncoder.encode(paramsStr,
						Constants.ENCODING_UTF8);
			}
		}
		return paramsStr;
	}

	/**
	 * <pre>
	 * 说   明:
	 * @param reqUrl TODO URLUTIL
	 * @param parameters
	 * @param signKey 
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-3上午10:03:35
	 * </pre>
	 */

	public static IhygeiaResultUtil postToResultUtil(String reqUrl, Map parameters,
			String signKey) throws Exception {
		String result = post(reqUrl, parameters, signKey);
		System.out.println("===========+++++++++++++++++++++============:"+result);
		return parse(result, IhygeiaResultUtil.class);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param reqUrl TODO URLUTIL
	 * @param parameters
	 * @param type
	 * @param signKey
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:17:50
	 * </pre>
	 */
	public static IhygeiaResultUtil postToResultUtil(String reqUrl, Map parameters,
			Type type, String signKey) throws Exception {
		String result = post(reqUrl, parameters, signKey);
		return parse(result, IhygeiaResultUtil.class);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param reqUrl TODO URLUTIL
	 * @param jsonObj
	 * @param signKey
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:17:57
	 * </pre>
	 */
	public static IhygeiaResultUtil postJsonToResultUtil(String reqUrl,
			Object jsonObj, String signKey) throws Exception {
		byte[] result = postJson(reqUrl, jsonObj, signKey);
		Type t = IhygeiaResultUtil.class;
		return parse(new String(result, Constants.ENCODING_UTF8),
				IhygeiaResultUtil.class);
	}

	/**
	 * <pre>
	 * 说       明: 根据URL，得到数据对象的数据 TODO URLUTIL
	 * @param reqUrl : 要访问的接口地址
	 * @param parameters : 上行参数
	 * @param cls : 转换的实体类
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-30下午3:07:41
	 * </pre>
	 */
	public  static <W extends Object> W post(String reqUrl,
			Map<String, Object> parameters, Class cls, String signKey)
			throws Exception {
		return (W) JSON.parseObject(post(reqUrl, parameters, signKey), cls);
	}

	/**
	 * <pre>
	 * 说   明:  反转对象 TODO URLUTIL
	 * @param result
	 * @param clz
	 * @return 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-3上午10:03:39
	 * </pre>
	 */
	public static <T> T parse(String result, Type clz) {
		return JSON.parseObject(result, clz);
	}

	/**
	 * <pre>
	 * 说   明:  反转对象 TODO URLUTIL
	 * @param result
	 * @param clz
	 * @return 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-3上午10:09:39
	 * </pre>
	 */
	public static <T> T parse(Object result, Type clz) {
		if (result == null) {
			return null;
		}
		return JSON.parseObject(result.toString(), clz);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param result TODO URLUTIL
	 * @param class1
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:18:21
	 * </pre>
	 */
	public static <T> List<T> parseArray(Object result, Class<T> class1) {
		if (result == null) {
			return null;
		}
		return JSON.parseArray(result.toString(), class1);
	}

	/**
	 * <pre>
	 * 说   明:  JSON数据请求 TODO URLUTIL
	 * 
	 * @param url
	 * @param obj
	 * @param signKey 
	 * @return
	 * @throws Exception 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-4下午6:04:05
	 * </pre>
	 */
	public static byte[] postJson(String url, Object obj, String signKey)
			throws Exception {
		return postJson(url, JSONObject.toJSONString(obj), signKey);
	}

	/**
	 * <pre>
	 * 说       明: JSON数据请求 TODO URLUTIL
	 * @param url
	 * @param list
	 * @param signKey 
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-30下午3:03:21
	 * </pre>
	 */
	public static byte[] postJson(String url, List list, String signKey)
			throws Exception {
		return postJson(url, JSONArray.toJSONString(list), signKey);
	}

	/**
	 * <pre>
	 * 说   明:  JSON数据请求 TODO URLUTIL
	 * 
	 * @param url
	 * @param json
	 * @param signKey 
	 * @return
	 * @throws Exception 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-4下午6:00:59
	 * </pre>
	 */
	public static byte[] postJson(String url, String json, String signKey)
			throws Exception {
		log.info(json);
		// start
		String methodName = null;
		String methodUrl = null;
		if (url != null) {
			methodName = url.substring(url.lastIndexOf("/") + 1,
					url.lastIndexOf("."));// 截取方法名
			methodUrl = StringUtils.getSubStr(url, 2);
			if (methodUrl.contains(".action")) {
				methodUrl = methodUrl.replaceAll(".action", "");
			}
			if (methodUrl.contains("/")) {
				methodUrl = methodUrl.replaceAll("/", "");
			}
			int start = methodUrl.indexOf("?");
			if (start != -1) {
				methodUrl = methodUrl.substring(0, start);
			}
		}
		int start = url.indexOf("?");

		if (start != -1) {
			String flage = url.substring(start + 1);
			String[] pp = flage.split("&");
			Map map = new HashMap();
			for (int i = 0; i < pp.length; i++) {
				String kv = pp[i];
				String[] keyValue = kv.split("=");
				if (keyValue.length > 1) {
					map.put(keyValue[0], keyValue[1]);
				}
			}
			String datasign = null;
			try {
				datasign = SignUtil
						.getSign(methodName, methodUrl, map, signKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			url = url.toString().concat(String.format("&sign=%s", datasign));
		}

		HttpPost request = new HttpPost(url);
		HttpEntity entity = new StringEntity(json, Constants.ENCODING_UTF8);
		request.setEntity(entity);
		request.addHeader("Content-Type", "application/json;charset="
				+ Constants.ENCODING_UTF8);
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client = new DefaultHttpClient(httpParameters);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return QEncodeUtil.aesDecryptNewReturnByte(
					NetTool.read(response.getEntity().getContent()), signKey);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明:  得到请求的URL TODO URLUTIL
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-13上午11:40:35
	 * </pre>
	 */
	public static String getRequestUrl(HttpServletRequest request)
			throws Exception {
		String requestURI = request.getRequestURI();
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + requestURI;
		Map<String, Object> paramsMap = getRequestParameter(request);
		if (paramsMap == null || paramsMap.size() == 0) {
			return url;
		}
		return url + "?" + getRequestParams(request);
	}

	/**
	 * <pre>
	 * 说       明: 获取参数值 TODO URLUTIL
	 * @param request
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-14下午4:58:22
	 * </pre>
	 */
	public static String getRequestParams(HttpServletRequest request)
			throws Exception {
		String tempKey = "";
		String p = "";
		Map<String, Object> paramsMap = getRequestParameter(request);
		for (Iterator<String> iterator = paramsMap.keySet().iterator(); iterator
				.hasNext();) {
			tempKey = iterator.next();
			p += tempKey + "=" + paramsMap.get(tempKey) + "&";
		}
		p = StringUtils.resplaceStr(p);
		return p;
	}

	/**
	 * <pre>
	 * 说       明: 获取访问ACTION TODO URLUTIL
	 * @param request
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-14上午11:47:15
	 * </pre>
	 */
	public static String getAction(HttpServletRequest request) throws Exception {
		if (request == null) {
			request = SpringUtil.getHttpServletReqeust();
		}
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		if (contextPath == null) {
			return null;
		}
		String url = requestURI.substring(contextPath.length());
		String action = StringUtils.replaceSlash(url.replace(
				Constants.CHAR_ACTION, ""));
		return action;
	}

	/**
	 * <pre>
	 * 说       明: 得到访问链接 TODO URLUTIL
	 * @param request
	 * @return http://192.168.1.175:4042/app
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-24下午2:33:16
	 * </pre>
	 */
	public static String getServerName() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			String url = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort();
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V3.3.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年9月26日下午6:40:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String getServerName(HttpServletRequest request) {
		try {
			String url = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort();
			return url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * <pre>
	 * 说       明: 得到访问链接上下文
	 * @return http://192.168.1.175:4042/app/v100/interface/users/findUsers.action
	 * 涉及版本: V1.0.0  TODO URLUTIL
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-24下午2:40:08
	 * </pre>
	 */
	public static String getContext() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			String requestURI = request.getRequestURI();
			return getServerName() + requestURI;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param request TODO URLUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:19:45
	 * </pre>
	 */
	public static Map<String, Object> getRequestParameter(
			HttpServletRequest request) throws Exception {
		Map<String, String[]> pmap = request.getParameterMap();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String keyTemp = null;
		for (Iterator<String> iterator = pmap.keySet().iterator(); iterator
				.hasNext();) {
			keyTemp = iterator.next();
			paramsMap.put(keyTemp,
					pmap.get(keyTemp) != null ? pmap.get(keyTemp)[0] : null);
		}
		return paramsMap;
	}

	/**
	 * <pre>
	 * 说   明:
	 * @param request TODO URLUTIL
	 * @return
	 * @throws IOException 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-17上午10:02:05
	 * </pre>
	 */
	public static String getRequestJSONParams(HttpServletRequest request)
			throws IOException {
		StringBuffer json = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			json.append(line);
		}
		return json.toString();
	}

	/**
	 * <pre>
	 * 说   明:  跳转到指定URL TODO URLUTIL
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @throws IOException 创建者: 陈    林(Vickey)
	 *                     日   期: 2014-7-21下午5:27:49
	 * </pre>
	 */
	public static void redirect(HttpServletRequest request,
			HttpServletResponse response, String url) throws IOException {
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + url);
	}

	/**
	 * <pre>
	 * 说       明: HTTP参数传为 json 格式 TODO JSONUTIL
	 * @param params : a=1,b=2,c=3
	 * @return {"a":"1","b":"2","c":"3"}
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-10上午10:40:20
	 * </pre>
	 */
	public static String httpParamsToJson(String params) throws Exception {
		if (StringUtils.isBlankOr(params)) {
			return "{}";
		}
		StringBuffer sb = new StringBuffer();
		String[] body = params.split(",");
		for (int i = 0; i < body.length; i++) {

			sb.append("\"" + body[i].split("=")[0] + "\"" + ":" + "\""
					+ body[i].split("=")[1] + "\"");
			if (i != (body.length - 1)) {
				sb.append(",");
			}
		}
		return "{" + sb.toString() + "}";
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V3.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年5月27日下午3:38:28
	 * Q    Q: 308053847
	 * </pre>
	 * @param postOrGet TODO
	 */
	public static Map<String, Object> postJiraLogin(String url, Map params, String postOrGet)
			throws Exception {
		String methodName = null;
		String methodUrl = null;
		int start = url.indexOf("?");


		HttpPost request = new HttpPost(url);
		HttpEntity entity = new StringEntity(JSONObject.toJSONString(params), 
				Constants.ENCODING_UTF8);
		request.setEntity(entity);
		request.addHeader("Content-Type", "application/json;charset="
				+ Constants.ENCODING_UTF8);
		HttpParams httpParameters = new BasicHttpParams();
		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);
		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		HttpClient client = new DefaultHttpClient(httpParameters);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			 String data =  new String(NetTool.read(response.getEntity().getContent()));
			 return JavaBeanUtil.jsonToMap(data);
		}
		return null;
	}
	
	
	/**
	 * <pre>
	 * 说       明: 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 涉及版本: V3.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年8月3日上午11:57:01
	 * Q    Q: 308053847
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 * </pre>
	 */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

	public static void main(String[] args) {
		try {
			URL myURL = new URL("https://192.168.10.148:8443/automation/interface/bak/tables/findAll.action"); 
			  
	        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象 
	        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection(); 
	  
	        // 取得该连接的输入流，以读取响应内容 
	        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream()); 
	  
	        // 读取服务器的响应内容并显示 
	        int respInt = insr.read(); 
	        while (respInt != -1) { 
	            System.out.print((char) respInt); 
	            respInt = insr.read(); 
	        } 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param url
	 * @param jsonParam
	 * @param noNeedResponse
	 * @return
	 */
	public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.parseObject(str);
                } catch (Exception e) {
                	log.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
        	log.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }
 
 
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
            	log.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
        	log.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
    
    public static String getSign(String token, String isKf) throws Exception {
		return QEncodeUtil.AES_KEY;
	}
    
    public static void printJson(HttpServletResponse response, Object result,
			String signKey) throws IOException, GeneralSecurityException {
		response.setContentType("text/plain;charset=" + ENCODING_UTF8);
		response.getOutputStream().write(
				QEncodeUtil.aesEncryptNew(new String(JSON.toJSONBytes(result),
						"utf-8"), signKey));
		response.getOutputStream().close();
	}
    
    /**
	 *<pre>
	 * 说       明: 
	 * @param request
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-7下午9:43:11
	 *</pre>
	 */
	public static String getUrl() throws Exception {
		HttpServletRequest request = null;
		try {
			request = ((ServletRequestAttributes)RequestContextHolder
					.getRequestAttributes()).getRequest();
		} catch (Exception e) {
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()
				+"://"
				+request.getServerName()
				+":"+request.getServerPort()+path+"/";
		return basePath;
	}
}
