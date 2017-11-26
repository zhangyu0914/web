package com.yw.common.beansUtils.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: UUID工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class ListUtil {
	
	private static Logger log = Logger.getLogger(ListUtil.class);
	/**
	 * <pre>
	 * 说   明:  拼接值
	 * @param list TODO LISTUTIL
	 * @return
	 * @throws Exception 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-13下午8:05:03
	 * </pre>
	 */
	public static String getAppendStr(List<String> list) throws Exception {
		if (list == null || list.isEmpty()) {
			return null;
		}
		String str = "";
		for (String string : list) {
			str += string + ",";
		}
		return StringUtils.resplaceStr(str);
	}
	
	public static void main(String[] args) {
		
	}
}
