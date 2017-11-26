package com.yw.common.beansUtils.utils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 *<pre>
 * 功       能: 数据处理工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:15:01
 * Q    Q: 308053847
 *</pre>
 */
public class NumberUtil {

	private static Logger log = Logger.getLogger(NumberUtil.class);
	public static final String PATTERN_DOUBLE_2 = "######0.00";
	public static final String PATTERN_DOUBLE_1 = "######0.0";
	public static final String PATTERN_DOUBLE = "######0";
	
	public final static String[] units = { "", "十", "百", "千", "万", "十万", "百万",
		"千万", "亿", "十亿", "百亿", "千亿", "万亿" };
	public final static char[] numArray = { '零', '一', '二', '三', '四', '五', '六',
		'七', '八', '九' };

	public static void main(String[] args) {
		for(int i=0; i<=10; i++){
			System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ genRandomNum(13));
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * @param amount 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-13下午7:22:26
	 * </pre>
	 */
	public static String getTrimAmount(String amount) {
		if (amount == null || amount.trim().equals("")) {
			return "0";
		}
		NumberFormat nf = NumberFormat.getInstance();

		nf.setGroupingUsed(false); // 不显示千位逗号
		return nf.format(new BigDecimal(amount));
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param amount 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-13下午7:42:18
	 * </pre>
	 */
	public static String getTrimAmount(Double amount) {
		if (amount == null) {
			return "0";
		}
		return getTrimAmount(amount + "");
	}
	
	public static BigDecimal getBigDecimal(Double amount) {
		if (amount == null) {
			return null;
		}
		return new BigDecimal(getTrimAmount(amount + ""));
	}

	/**
	 * <pre>
	 * 说       明: 
	 * @param amount 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-13下午7:42:13
	 * </pre>
	 */
	public static String getTrimAmount(BigDecimal amount) {
		if (amount == null) {
			return "0";
		}
		return getTrimAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP)
				.toString());
	}
	
	/**
	 *<pre>
	 * 说       明: 生成随机数  
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23上午11:58:32
	 *</pre>
	 */
	public static String productRandomCode() {
		SecureRandom ng = new SecureRandom();
		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);
		randomBytes[6] &= 0x0f; /* clear version */
		randomBytes[6] |= 0x40; /* set to version 4 */
		int msb = 0;
		int lsb = 0;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (randomBytes[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (randomBytes[i] & 0xff);
		Long i = Long.valueOf(msb + System.currentTimeMillis() + lsb);
		return Integer.toHexString(i.intValue())
				+ String.valueOf(System.currentTimeMillis() % 1000);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param num 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:25:34
	 *</pre>
	 */
	public static String formatNumber(int num) {
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			String unit = units[(len - 1) - i];
			if (isZero) {
				if (0 == num) {
					return sb.append(numArray[n]).toString();
				}else if ('0' == val[i - 1]) {
					continue;
				} else {
					if (!String.valueOf(numArray[n]).equals("一")
							&& len != 2) {
						
						if (String.valueOf(numArray[n]).equals("零")
								&& (len == 1 || (i  < len - 1))) {
							
							sb.append(numArray[n]);
						}
					}
				}
			} else {
				if (String.valueOf(numArray[n]).equals("一")) {
					
					if (i != 0 || len != 2) {//解决一十一问题
						sb.append(numArray[n]);
					}
				}else{
					sb.append(numArray[n]);
				}
				sb.append(unit);
			}
		}
		if (sb.length() > 1 
				&& sb.substring(sb.length()-1, sb.length()).equals("零")) {
			return sb.substring(0,sb.length() -1);//去掉末尾零
		}
		return sb.toString();
	}

	/**
	 *<pre>
	 * 说       明: 
	 * @param decimal 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:25:43
	 *</pre>
	 */
	private static String formatDecimal(double decimal) {
		String decimals = String.valueOf(decimal);
		int decIndex = decimals.indexOf(".");
		int integ = Integer.valueOf(decimals.substring(0, decIndex));
		int dec = Integer.valueOf(decimals.substring(decIndex + 1));
		String result = formatNumber(integ) + "." + formatFractionalPart(dec);
		return result;
	}

	/**
	 *<pre>
	 * 说       明: 
	 * @param decimal 
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:25:48
	 *</pre>
	 */
	private static String formatFractionalPart(int decimal) {
		char[] val = String.valueOf(decimal).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int n = Integer.valueOf(val[i] + "");
			sb.append(numArray[n]);
		}
		return sb.toString();
	}
	
	/**
	 *<pre>
	 * 说       明: 生成指定范围内且指定长度的随机数
	 * @param pwd_len
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-3-29下午4:19:33
	 *</pre>
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param data
	 * @param pattern
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-17下午10:28:09
	 *</pre>
	 */
	public static String formatDoubleStr(double data, String pattern) {
		if (pattern == null) {
			pattern = PATTERN_DOUBLE_2;
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(data);
	}
	
	public static float formatDouble(double data, String pattern) {
		String str = formatDoubleStr(data, pattern);
		return Float.valueOf(str);
	}
	
}
