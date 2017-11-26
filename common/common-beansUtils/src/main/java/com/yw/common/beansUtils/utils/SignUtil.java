package com.yw.common.beansUtils.utils;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.yw.common.beansUtils.utils.md5.MD5Util;
import com.yw.common.beansUtils.utils.string.StringUtils;


/**
 *<pre>
 * 功       能: 参数签名
 * 涉及版本: V1.0.0 
 * 创  建  者: 施俊帆(Vickey)
 * 日       期: 2014-11-11上午10:07:22
 * Q    Q: 
 *</pre>
 */
public class SignUtil {
	
	private static Logger log = Logger.getLogger(SignUtil.class);
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param method
	 * @param methodUrl
	 * @param map
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 施俊帆(Vickey)
	 * 日       期: 2014-11-11上午10:02:46
	 *</pre>
	 */
	public static String getSign(String method,String methodUrl,Map<String, Object> map, String signKey) throws Exception{
		String signKeys = Constants.getMethodParams(methodUrl);
		String[] keys = {};
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(method);
		if(signKeys!=null && signKeys.length()>0){
			keys = signKeys.split(",");
			for(String key: keys){
				if(map.containsKey(key)){
					String value = String.valueOf(map.get(key));
					stringBuilder.append(key).append("=").append(value);
				}else{
					throw new Exception("参数加密失败 key = ["+key+"]");
				}
			}// TODO 处理SIGN密钥
			stringBuilder.append(signKey);
        }
		return MD5Util.md5(stringBuilder.toString());
	}
	
	/**
	 * <pre>
	 * 说       明: 给密码加盐
	 * @param userName TODO SIGNUTIL
	 * @param userPwd
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-30上午11:11:03
	 * </pre>
	 */
	public static String[] AddSalt(String userName, String userPwd,
			String randomSalt) {
		if (StringUtils.isBlankOr(userName, userPwd)) {
			return null;
		}
		String algorithmName = "md5";
		String salt1 = userName;// 盐1
		String salt2 = null;
		if (!StringUtils.isBlank(randomSalt)) {// 如果传入了随机数，就是登录操作，验证密码是否一致
			salt2 = randomSalt;
		} else {
			salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();// 盐2:随机数
		}
		int hashIterations = 2;// 指定散列次数
		SimpleHash hash = new SimpleHash(algorithmName, userPwd, salt1 + salt2,
				hashIterations);
		return new String[] { hash.toHex(), salt2 };
	}
}
