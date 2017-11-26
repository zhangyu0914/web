package com.yw.common.beansUtils.utils;

import java.util.UUID;

/**
 *<pre>
 * 功       能: UUID工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class UUIDUtil {
	
	
	/**
	 * <pre>
	 * 说   明: UUID TODO UUIDUTIL
	 * 创建者:
	 * 日   期: 2014-6-24下午2:59:17
	 * Q  Q:
	 * </pre>
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 *<pre>
	 * 说       明: 截取指定长度的UUID
	 * @param endIndex
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-20下午4:03:50
	 *</pre>
	 */
	public static String getUUID(Integer endIndex) {
		if (endIndex == null) {
			return null;
		}
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, endIndex);
	}
	
	/**
	 * <pre>
	 * 说       明: 获取基本SIGN的UUID  TODO UUIDUTIL
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-9下午1:41:16
	 * </pre>
	 */
	public static String getUUIDSign() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 16);// 必须是8的倍数才行
	}
	
	
}
