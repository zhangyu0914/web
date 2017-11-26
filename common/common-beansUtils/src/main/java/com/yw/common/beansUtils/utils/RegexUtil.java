package com.yw.common.beansUtils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 正则表达式匹配工具类
 * 创建者: Zhaoxin.Zhao
 * 日   期: 2015-7-20下午12:23:51
 * Q  Q: 1269763159
 * </pre>
 */
public class RegexUtil {
	

	private RegexUtil() {
		
	}

	/*******************
	 * 匹配 整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean checkIsNum(String number) {
		String regex = "^-?\\d+$";
		return regex(number, regex);
	}

	/**********************************
	 * 匹配 正整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean checkIsPosNum(String number) {
		String regex = "^[0-9]*[1-9][0-9]*$";
		return regex(number, regex);
	}

	/**************
	 * 匹配 Email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkIsEmail(String email) {
		String regex = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";
		return regex(email, regex, Pattern.CASE_INSENSITIVE);
	}

	/********************
	 * 匹配 URL
	 * 
	 * @param url
	 * @return
	 */
	public static boolean checkIsURL(String url) {
		String regex = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";
		return regex(url, regex);
	}

	// public static boolean checkIsURL2(String url){
	// String regex = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";
	// return regex(url, regex);
	// }

	/***********************************
	 * 匹配 电话
	 * <p>
	 * 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或 010-12345678
	 * 或 12345678912
	 * </p>
	 * 
	 * @param phone
	 * @return
	 * 
	 */
	public static boolean checkIsPhone(String phone) {
		String regex = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
		return regex(phone, regex);
	}

	/*************************
	 * 匹配 身份证
	 * <p>
	 * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或
	 * XXXXXXXXXXXXXXX(15位)XXXXXXXXXXXXXXXXXX(18位)
	 * </p>
	 * 
	 * @param card
	 * @return
	 */
	public static boolean checkIsCard(String card) {
		String regex = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";
		return regex(card, regex);
	}

	/***************
	 * 匹配 邮编
	 * <p>
	 * 格式为: XXXXXX(6位)
	 * </p>
	 * 
	 * @param zip
	 * @return
	 */
	public static boolean checkIsZip(String zip) {
		String regex = "^[0-9]{6}$";
		return regex(zip, regex);
	}

	/****************
	 * 匹配 浮点数
	 * 
	 * @param floatNum
	 * @return
	 */
	public static boolean checkIsFloat(String floatNum) {
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		return regex(floatNum, regex);
	}

	/****************
	 * 匹配 正浮点数
	 * 
	 * @param floatNum
	 * @return
	 */
	public static boolean checkIsFloat2(String floatNum) {
		String regex = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
		return regex(floatNum, regex);
	}

	/*************
	 * 匹配由26个英文字母组成的字符串
	 * 
	 * @param data
	 * @return
	 */
	public static boolean checkIsCh(String data) {
		String regex = "^[A-Za-z]+$";
		return regex(data, regex);
	}

	/****************
	 * 匹配有数字组成的字符串
	 * 
	 * @param data
	 * @return
	 */
	public static boolean checkIsDig(String data) {
		String regex = "^[0-9]+$";
		return regex(data, regex);
	}
	
	public static boolean checkIsDig(Object data) {
		if (data == null) {
			return false;
		}
		String regex = "^[0-9]+$";
		return regex(data.toString(), regex);
	}

	/***********
	 * 匹配由数字或26个英文字母组成的字符串
	 * 
	 * @param data
	 * @return
	 */
	public static boolean checkIsChDig(String data) {
		String regex = "^[A-Za-z0-9]+$";
		return regex(data, regex);
	}

	/************
	 * 匹配由数字、26个英文字母、汉字组成的字符串
	 * 
	 * @param data
	 * @return
	 */
	public static boolean checkIsChEnDig(String data) {
		String regex = "^[A-Za-z0-9\u4e00-\u9fa5]+$";
		return regex(data, regex);
	}
	
	/**
	 * <pre>
	 * 说       明: 汉字判断
	 * 涉及版本: V3.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年8月15日上午9:52:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static boolean checkCh(String data) {
		String regex = "^[\u4e00-\u9fa5]+$";
		return regex(data, regex);
	}

	/*****************
	 * 匹配图象
	 * 
	 * <p>
	 * 此版本只支持jpg，dmp，png，gif，jpeg格式的匹配
	 * </p>
	 * 
	 * @param imgName
	 * @return
	 */
	public static boolean checkIsImg(String imgName) {
		if (StringUtils.isBlank(imgName))
			return false;
		imgName = imgName.toLowerCase();
		if (imgName.endsWith(".jpg") || imgName.endsWith("dmp")
				|| imgName.endsWith("png") || imgName.endsWith("gif")
				|| imgName.endsWith("jpeg"))
			return true;
		return false;
	}

	/*********************
	 * 匹配 IP
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean checkIsIP(String ip) {
		StringBuilder builder = new StringBuilder(100);
		builder.append("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.")
				.append("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.")
				.append("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.")
				.append("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])");
		String regex = builder.toString();
		builder = null;
		return regex(ip, regex);
	}

	/**************
	 * 去除HTML
	 * 
	 * @param html
	 * @return
	 */
	public static String regexHTML(String html) {
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		return matcher.replaceAll("");
	}

	/**********************
	 * 正则表达式 匹配
	 * 
	 * @param data
	 *            匹配的字符串
	 * @param regex
	 *            正则表达式
	 * @return
	 */
	public static boolean regex(String data, String regex) {
		return regex(data, regex, -1);
	}

	/***********************
	 * 正则表达式 匹配
	 * 
	 * @param data
	 *            匹配的字符串
	 * @param regex
	 *            正则表达式
	 * @param flag
	 *            匹配标志，请查看Jdk中Pattern类
	 * @return
	 */
	public static boolean regex(String data, String regex, int flag) {
		if (StringUtils.isBlank(data) || StringUtils.isBlank(regex))
			return false;
		Pattern p = null;
		if (flag == -1)
			p = Pattern.compile(regex);
		else
			p = Pattern.compile(regex, flag);
		Matcher m = p.matcher(data);
		return m.matches();
	}

	public static void main(String[] args) {
		System.out.println(checkCh("在"));
	}
	
	/**
	 *<pre>
	 * 说       明: 根据KEY\VALUE,获取JSON格式数据
	 * @param sb
	 * @param key
	 * @param value
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-12-4下午2:04:44
	 *</pre>
	 */
	public static StringBuffer getJsonParam(StringBuffer sb, String key, Object value){
		return sb.append("'"+key+"':'"+value+"',");//不论数字还是字符串
	}
	
}
