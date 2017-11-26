package com.yw.common.beansUtils.utils.file;

import java.util.ResourceBundle;

/**
 *<pre>
 * 功       能: 项目参数工具类
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-5-18上午11:38:49
 * Q    Q: 308053847
 *</pre>
 */
public class ConfigUtil {

	private final static String target= "com/yw/config/target";

	/**
	 *<pre>
	 * 说       明: 通过键获取值
	 * @param propertyPathAndFileName
	 * @param key
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午3:39:13
	 *</pre>
	 */
	public static String get(String key) {
		ResourceBundle bundle = java.util.ResourceBundle.getBundle(target);
		return bundle.getString(key);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午3:39:20
	 *</pre>
	 */
	public static String getServer() {
		return get("SERVER").toUpperCase();
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param propertyPathAndFileName
	 * @param key
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午3:39:24
	 *</pre>
	 */
	public static Integer getServerVersion() {
		return Integer.valueOf(get("SERVER_VERSION"));
	}
	

	/**
	 *<pre>
	 * 说       明: 
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午3:55:53
	 *</pre>
	 */
	public static String getRedisKey() {
		return ConfigUtil.getServer().toUpperCase() + "-" + ConfigUtil.getServerVersion();
	}
}
