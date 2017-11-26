package com.yw.common.beansUtils.utils.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.yw.common.beansUtils.utils.des.DescryptCoder;
import com.yw.common.beansUtils.utils.des.EncryptCoder;

/**
 *<pre>
 * 功   能: 加密工具类
 * 创建者: 陈    林(Vickey)
 * 日   期: 2014-7-16下午3:43:38
 * Q  Q: 308053847
 *</pre>
 */
public class MD5Util {

	public static void main(String[] args) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("userName", "13200000002");
    	paramsMap.put("userPwd", "e10adc3949ba59abbe56e057f20f883e");
    	paramsMap.put("clientType", "1");
    	String sign = "loginuserPwd="+paramsMap.get("userPwd")+"clientType="+paramsMap.get("clientType")+"userName="+paramsMap.get("userName")+"09435949-1b3f-41";
    	paramsMap.put("sign", MD5Util.md5("123456"));//用户ID
		System.out.println(sign);
		System.out.println(EncryptCoder.encrypt("123456", "234923238jdhd73hdkd"));
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 返回BYTE数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月7日下午2:22:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static byte[] md5Byte(String source) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(source.getBytes());
		return md.digest();
	}
}
