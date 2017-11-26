package com.yw.common.beansUtils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 检测是否为移动端设备访问
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 参       考: http://blog.csdn.net/xiaoxian8023/article/details/37527133
 * 日       期: 2015-4-22下午5:50:15
 * Q    Q: 308053847
 *</pre>
 */
public class CheckMobile {
	
	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),  
	// 字符串在编译时会被转码一次,所以是 "\\b"  
	// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)  
	static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"  
	        +"|windows (phone|ce)|blackberry"  
	        +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"  
	        +"|laystation portable)|nokia|fennec|htc[-_]"  
	        +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";  
	static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"  
	        +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";  
	
	//移动设备正则匹配：手机端、平板
	static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);  
	static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);  
	  
	/**
	 * 检测是否是移动设备访问
	 * 
	 * @Title: check
	 * @Date : 2014-7-7 下午01:29:07
	 * @param userAgent 浏览器标识
	 * @return true:移动设备接入，false:pc端接入
	 */
	public static boolean check(String userAgent){  
	    if(StringUtils.isBlank(userAgent)){  
	        return false;
	    }
	    userAgent = userAgent.toLowerCase();
	    // 匹配  
	    Matcher matcherPhone = phonePat.matcher(userAgent);  
	    Matcher matcherTable = tablePat.matcher(userAgent);  
	    if(matcherPhone.find() || matcherTable.find()){  
	    	System.out.println("是移动用户");
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
}
