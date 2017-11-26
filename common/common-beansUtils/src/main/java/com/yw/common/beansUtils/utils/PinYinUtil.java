package com.yw.common.beansUtils.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.log4j.Logger;

/**
 *<pre>
 * 功       能: 汉字转换拼音工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:40:20
 * Q    Q: 308053847
 *</pre>
 */
public class PinYinUtil {
	
	private static Logger log = Logger.getLogger(PinYinUtil.class);

	/**
	 *<pre>
	 * 说       明: 转换一个字符串
	 * @param str TODO PINYINUTIL
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:29:29
	 *</pre>
	 */
	public static String getStringPinYin(String str) {
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// 如果str.charAt(i)非汉字，则保持原样
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin);
			}
		}
		return sb.toString();
	}

	/**
	 *<pre>
	 * 说       明: 获取首字母简拼
	 * @param str TODO PINYINUTIL
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:29:41
	 *</pre>
	 */
	public static String getStringJianPin(String str) {
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// 如果str.charAt(i)非汉字，则保持原样
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin.charAt(0));
			}
		}
		return sb.toString();
	}
	
	/**
	 *<pre>
	 * 说       明: 转换单个字符
	 * @param c TODO PINYINUTIL
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:28:59
	 *</pre>
	 */
	public static String getCharacterPinYin(char c) {
		String[] pinyin = null;
		try {
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		// 如果c不是汉字，toHanyuPinyinStringArray会返回null
		if (pinyin == null)
			return null;
		// 只取一个发音，如果是多音字，仅取第一个发音
		return pinyin[0];
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getStringPinYin("中关村"));
		System.out.println(getStringJianPin("中关村"));
	}
}
