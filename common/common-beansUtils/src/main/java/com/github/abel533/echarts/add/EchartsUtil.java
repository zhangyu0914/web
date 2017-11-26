package com.github.abel533.echarts.add;

import java.io.Serializable;

/**
 *<pre>
 * 功       能: 
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2016-5-21下午2:14:35
 * Q    Q: 308053847
 *</pre>
 */
public class EchartsUtil implements Serializable{

	private static final long serialVersionUID = 8937620961120144165L;
	
	public static final int XSKIP = 10;//X轴间隔值
	public static final int XSKIP_PIE = 40;//X轴饼图预留空间
	public static final String[] color = new String[]{
		"#2F4554","#C23531","#61A0A8","#D48265","#91C7AE",
		"#749F83","#CA8622","#BDA29A","#6E7074","#546570",
		"#C4CCD3","red","#0C6","#069",
		"#0CC","#963","#969","#F90","#3F9","#9CF"};

	public static void main(String[] args) {
		System.out.println(formatStrToVertical("上线", 4, 1));
	}
	/**
	 *<pre>
	 * 说       明: 字符器格式化为垂直显示
	 * @param str
	 * @param splitCount TODO
	 * @param maxWhile TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-21下午2:17:07
	 *</pre>
	 */
	public static String formatStrToVertical(String str, Integer splitCount, Integer maxWhile){
		if(str == null || str.equals("")){
			return null;
		}
		if (splitCount == null) {
			splitCount = 1;//默认1个一组
		}
		if (splitCount > str.length()) {
			splitCount = str.length();
		}
		StringBuffer sb = new StringBuffer();
		String temp = "";
		Integer startIndex = 0;
		Integer end = 0;
		Integer whileIndex = 0;
		do {
			whileIndex ++;
			end = startIndex + splitCount;
			if (end >= str.length()) {
				end = str.length();
			}
			temp = str.substring(startIndex, end);
			startIndex += splitCount;
			sb.append(temp + "\n");
			if (maxWhile != null && whileIndex >= maxWhile) {
				break;//指定循环次数
			}
		} while (startIndex < str.length());
		if(maxWhile != null && maxWhile == 1){
			return sb.toString().replace("\n", "");
		}
		String data = sb.toString().substring(0,sb.length() - 1);
		return data;//去掉最后一个换行
	}
}
