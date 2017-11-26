package com.yw.common.beansUtils.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *<pre>
 * 功       能: UNICODE编码互转工具类
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-10-28下午3:56:37
 * Q    Q: 308053847
 *</pre>
 */
public class UnicodeUtil {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String s2 =  "数据长度过长";
		System.out.println("转换汉字为Unicode码: " + s2);
		String unicode = UnicodeUtil.stringToUnicode(s2);
		System.out.println("s2_unicode_Hex: " + unicode);
		
		System.out.println("Unicode:" + unicode);
		String s3 = UnicodeUtil.unicodeToString(unicode);
		System.out.println("转换Unicode码为汉字: " + s3);
		
	}

	/**
	 *<pre>
	 * 说       明: 把中文字符串转换为十六进制Unicode编码字符串
	 * @param s
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-28下午3:56:57
	 *</pre>
	 */
	public static String stringToUnicode(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			if (ch > 255)
				str += "\\u" + Integer.toHexString(ch);
			else
				str += "\\" + Integer.toHexString(ch);
		}
		return str;
	}

	/**
	 *<pre>
	 * 说       明: 把十六进制Unicode编码字符串转换为中文字符串
	 * @param str
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-28下午3:57:06
	 *</pre>
	 */
	public static String unicodeToString(String str) {

		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch + "");
		}
		return str;
	}

	/**
	 *<pre>
	 * 说       明: 把中文字符串转换为十六进制GBK编码字符串
	 * @param s
	 * @return
	 * @throws UnsupportedEncodingException
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-28下午3:57:12
	 *</pre>
	 */
	public static String stringToGBK(String s)
			throws UnsupportedEncodingException {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			String ch = String.valueOf(s.charAt(i));
			// 取字符串编码示例 默认字符为GBK
			byte[] sgbk = ch.getBytes("gbk");
			String shex = "";
			for (int j = 0; j < sgbk.length; j++) {
				// System.out.println(sb[i]);
				String hex = Integer.toHexString(sgbk[j] & 0xFF);
				if (hex.length() == 1) {
					hex = "0" + hex;
				}
				shex = shex + hex;
			}
			str = str + " 0x" + shex.toUpperCase();
		}

		return str;
	}
}
