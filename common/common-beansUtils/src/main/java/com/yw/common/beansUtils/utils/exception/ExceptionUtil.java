package com.yw.common.beansUtils.utils.exception;

import java.util.Map;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.JavaBeanUtil;


/**
 *<pre>
 * 功       能: 异常工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class ExceptionUtil {
	
	private static Logger log = Logger.getLogger(ExceptionUtil.class);
	
	public static final String EXCEPTION_DESCRIP = "{"
			+ "\"java.lang.ArithmeticException\":\"算术异常类\","
			+ "\"java.lang.NullPointerException\":\"空指针异常类\","
			+ "\"java.lang.ClassCastException\":\"类型强制转换异常\","
			+ "\"java.lang.NegativeArraySizeException\":\"数组负下标异常\","
			+ "\"java.lang.ArrayIndexOutOfBoundsException\":\"数组下标越界异常\","
			+ "\"java.io.EOFException\":\"文件已结束异常\","
			+ "\"java.io.FileNotFoundException\":\"文件未找到异常\","
			+ "\"java.lang.NumberFormatException\":\"字符串转换为数字异常\","
			+ "\"java.sql.SQLException\":\"操作数据库异常\","
			+ "\"java.io.IOException\":\"输入输出异常\","
			+ "\"java.lang.NoSuchMethodException\":\"方法未找到异常\","
			+ "\"java.lang.ArithmeticException\":\"算术条件异常\","
			+ "\"java.lang.ArrayStoreException\":\"数组存储异常\","
			+ "\"java.lang.ClassNotFoundException\":\"找不到类异常\","
			+ "\"java.lang.CloneNotSupportedException\":\"不支持克隆异常\","
			+ "\"java.lang.EnumConstantNotPresentException\":\"枚举常量不存在异常\","
			+ "\"java.lang.Exception\":\"根异常\","
			+ "\"java.lang.IllegalAccessException\":\"违法的访问异常\","
			+ "\"java.lang.IllegalMonitorStateException\":\"违法的监控状态异常\","
			+ "\"java.lang.IllegalStateException\":\"违法的状态异常\","
			+ "\"java.lang.IllegalThreadStateException\":\"违法的线程状态异常\","
			+ "\"java.lang.IndexOutOfBoundsException\":\"索引越界异常\","
			+ "\"java.lang.InstantiationException\":\"实例化异常\","
			+ "\"java.lang.InterruptedException\":\"被中止异常\","
			+ "\"java.lang.NegativeArraySizeException\":\"数组大小为负值异常\","
			+ "\"java.lang.NoSuchFieldException\":\"属性不存在异常\","
			+ "\"java.lang.RuntimeException\":\"运行时异常\","
			+ "\"java.lang.SecurityException\":\"安全异常\","
			+ "\"java.lang.StringIndexOutOfBoundsException\":\"字符串索引越界异常\","
			+ "\"java.lang.TypeNotPresentException\":\"类型不存在异常\","
			+ "\"java.lang.UnsupportedOperationException\":\"不支持的方法异常\","
			+ "\"java.text.ParseException\":\"日期格式转换异常\"" + "}";//
	
	/**
	 * <pre>
	 * 说       明: 获取对应的异常解释
	 * @param ex TODO EXCETIONUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-16上午10:00:32
	 * </pre>
	 */
	public static String getException(Exception ex) throws Exception {
		if (ex == null || ex.getCause() == null
				|| ex.getCause().getClass() == null
				|| ex.getCause().getClass().getName() == null) {

			return null;
		}
		Map<String, Object> map = JavaBeanUtil.jsonToMap(EXCEPTION_DESCRIP);
		if (map == null || map.isEmpty()) {
			return null;
		}
		return map.get(ex.getCause().getClass().getName()) + "";
	}
	
	public static void main(String[] args) {
		
	}
}
