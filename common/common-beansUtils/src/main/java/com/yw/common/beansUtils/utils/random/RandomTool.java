package com.yw.common.beansUtils.utils.random;

import java.util.Random;

/**
 * 数字密码思想: 1、将所有数字存储在一个数组中intarray
 * 2、每次生成一个随机数，将intarray中对应此随机数的字符取出，并从intarray中删除此字符(做标记)
 * 3、如果用来存储密码的StringBuffer长度小于密码长度，再重复步骤2 这样能减少无谓的判断，高效生成所需要的随机码
 * 
 * *数字和字母组合密码思想: 1、将所有数字和字母存储在2个数组intarray_dc(存数字)和chararray(存字母)中
 * 2、每次生成1个随机数，将数组中对应此随机数的字符取出，并从数组中删除此字符(做标记)
 * 3、如果用来存储密码的StringBuffer长度小于密码长度，再重复步骤2 这样能减少无谓的判断，高效生成所需要的随机码
 */
class Data {
	public int num;
	private int count;
	private char su;

	public Data(int i, int flag) {
		num = i;
		count = flag;
	}

	public Data(char c, int flag) {
		su = c;
		count = flag;
	}

	public void Setcou(int flag) {
		count = flag;
	}

	public int Getnum() {
		return num;
	}

	public char GetChar() {
		return su;
	}

	public int Getcount() {
		return count;
	}
};

public class RandomTool {

	private Data intarray[];
	private Data chararray[];
	private Data intarray_dc[];
	private StringBuffer s1;// 保存产生的数字密码
	private StringBuffer s2;
	private StringBuffer s3;// 保存产生的数字和字母组合密码

	public RandomTool() {
		intarray = new Data[10];
		intarray_dc = new Data[10];
		chararray = new Data[52];
		s2 = new StringBuffer();
		s2.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		s3 = new StringBuffer();
		for (int i = 0; i < 52; i++) {
			Data temp1 = new Data(s2.charAt(i), 0);
			chararray[i] = temp1;
		}
		for (int i = 0; i < 10; i++) {
			Data temp = new Data(i, 0);
			intarray[i] = temp;
			Data temp1 = new Data(i, 0);
			intarray_dc[i] = temp1;
		}
	}

	/***************************************************************************
	 * 产生数字和字母组成的随机数
	 * 
	 * @param length
	 *            欲产生的密码长度
	 * @return 随机数字符串(数字和字母组合)
	 */
	public String randomNum(int length) {
		while (s3.length() < length) {
			int t1 = (int) Math.floor(62 * Math.random());
			int k = s3.length() / 62;
			if (t1 < 10 && intarray_dc[t1].Getcount() == k) {
				s3.append(intarray_dc[t1].Getnum());
				intarray_dc[t1].Setcou(k + 1);
			} else if (t1 >= 10 && chararray[t1 - 10].Getcount() == k) {
				s3.append(chararray[t1 - 10].GetChar());
				chararray[t1 - 10].Setcou(k + 1);
			}
		}
		return s3.toString();
	}

	/***************************************************************************
	 * 产生数字组成的随机数
	 * 
	 * @param length
	 *            欲产生的密码长度
	 * @return 随机数字符串(数字)
	 */
	public String randomNum1(int length) {
		while (s1.length() < length) {
			int t = (int) Math.floor(10 * Math.random());
			int k = s1.length() / 10;
			if (intarray[t].Getcount() == k) {
				s1.append(intarray[t].Getnum());
				intarray[t].Setcou(k + 1);
			}
		}
		return s1.toString();
	}

	/**
	 * *************************************************************************
	 * 生成纯数字的6位不重复随机数
	 * 
	 * @return 随机数字符串(数字)
	 */
	public String randomNum() {
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random rand = new Random();
		for (int i = 10; i > 1; i--) {
			int index = rand.nextInt(i);
			int tmp = array[index];
			array[index] = array[i - 1];
			array[i - 1] = tmp;
		}
		int result = 0;
		String re = "";
		for (int i = 0; i < 6; i++) {
			re += result * 10 + array[i] + "";
		}
		return re;
	}
	
	/**
	 * <pre>
	 * 说   明:  获取指定范围内的随机数 TODO RANDOMUTIL
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-10-16上午10:59:56
	 * </pre>
	 */
	public static Integer getRandom(Integer start, Integer end)
			throws Exception {
		Random r = new Random();
		// r.nextInt((终 - 始)+1) + 始;
		// 比如我要求 65 到 90之间的随机数，注：包含结束值
		return r.nextInt((end - start) + 1) + start;
	}
}
