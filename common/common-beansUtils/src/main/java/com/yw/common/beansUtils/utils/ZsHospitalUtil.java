package com.yw.common.beansUtils.utils;

import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;

public class ZsHospitalUtil {

	private static final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	public static final String AES_KEY = "2015-AES-KEY-HIS";

	/**
	 * @description 分装post表单参数
	 * @version 1.0.0
	 * @date 2014/10/20
	 * @param httpost
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private static HttpPost postForm(HttpPost httpost,
			Map<String, Object> params) throws Exception {
		if (!(null == params || "".equals(params) || "null".equals(params))) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
			}
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		}
		return httpost;
	}

	public static String doPostDecrypt(String url, Map<String, Object> params)
			throws Exception {

		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
		post = postForm(post, params);

		HttpResponse response = httpClient.execute(post);
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();
		Integer statusCode = response.getStatusLine().getStatusCode();
		log.info("response status: " + statusCode);
		String charset = EntityUtils.getContentCharSet(entity);
		log.info("response charset：" + charset);
		String resultData = null;
		if (statusCode == HttpStatus.SC_OK) {

			// 得到客户段响应的内容，并进行解密
			resultData = aesDecryptNew(NetTool.read(entity.getContent()),
					AES_KEY);
			log.info(resultData);
		}
		httpClient.getConnectionManager().shutdown();
		return resultData;
	}

	public static String aesDecryptNew(byte[] encrypt, String key)
			throws Exception {
		return new String(base64Decode(new String(aesDecrypt(encrypt,
				key.getBytes()))), "utf-8");
	}

	public static byte[] aesDecrypt(byte[] data, byte rawKeyData[])
			throws GeneralSecurityException {
		// 处理密钥
		SecretKeySpec key = new SecretKeySpec(rawKeyData, "AES");
		// 解密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder()
				.decodeBuffer(base64Code);
	}

	public static Object formatDataToJson(String resultData, Type typeClass)
			throws Exception {
		Object data = null;
		if (!(null == resultData || "".equals(resultData) || "null"
				.equals(resultData))) {
			if (typeClass instanceof Type)
				data = JSON.parseObject(resultData, typeClass);
			else
				data = resultData;
		}
		return data;
	}
	
	/**
	 * @description 发送HTTP请求,post方式，并将需返回的数据进行解密
	 * @version 1.0.0
	 * @date 2014/10/20
	 * @param url
	 * @param params
	 * @param typeClass
	 *            响应数据JSON转换的对象Type
	 * @return
	 * @throws Exception
	 */
	public static Object doPostDecrypt(String url, Map<String, Object> params,
			Type typeClass) throws Exception {
		String resultData = doPostDecrypt(url, params);
		return formatDataToJson(resultData, typeClass);
	}
	
	public static String doPostDecryptGetResult(String url, Map<String, Object> params) throws Exception {
		return doPostDecrypt(url, params);
	}
	
	public static List<Map<String,Object>> getDataMap(String url, Map<String, Object> params) throws Exception{
		
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		
		String json = doPostDecryptGetResult(url, params);
		JSONObject jsonObj = JSON.parseObject(json);
		JSONArray arr = jsonObj.getJSONArray("data");
		
		Map<String,Object> dataMap = null;
		int size = arr.size();
		for(int i=0;i<size;i++){
			JSONObject obj = arr.getJSONObject(i);
			dataMap = new HashMap<String, Object>();
			dataMap.put("code", obj.get("code"));
			dataMap.put("name", obj.get("name"));
			data.add(dataMap);
		}
		
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		
		String code = "01";
		int resourceType = 1;
		
		code = "7217";
		resourceType = 3;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("isValidate", "1");
		params.put("resourceType", resourceType);
		params.put("code", code);
		String url = "http://192.168.1.236:8081/zsapp/v110/api/hospital/getDeptResource.action";
		
		System.out.println(getDataMap(url, params));
		
	}
	
}
