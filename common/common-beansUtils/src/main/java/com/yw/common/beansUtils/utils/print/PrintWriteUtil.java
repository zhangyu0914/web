package com.yw.common.beansUtils.utils.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.des.QEncodeUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.resultUtil.AskDrResultUtil;
import com.yw.common.beansUtils.utils.resultUtil.IhygeiaResultUtil;

/**
 *<pre>
 * 功       能: 用于向浏览器输出信息工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:55:27
 * Q    Q: 308053847
 *</pre>
 */
public class PrintWriteUtil {
	
	private static Logger log = Logger.getLogger(PrintWriteUtil.class);
	
	/**
	 *<pre>
	 * 说       明: 输出到浏览器
	 * @param response
	 * @param flag
	 * @param msg
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-5-4下午1:54:35
	 *</pre>
	 */
	public static PrintWriter writeResultToBrowser(HttpServletResponse response,
			boolean flag,String msg){
		msg = msg == null ? "" : msg;
		//从servletActionContext上下文中,获取response对象
		//设置向浏览器返回信息的格式为text/html格式
		response.setContentType("text/html");
		//设置response编码为utf-8
		response.setCharacterEncoding("utf-8");
		
		PrintWriter ps = null;
		try {
			ps = response.getWriter();
			String r = "{success : true,flag: "+flag+",'msg':'" + msg + "'}";
			ps.write(r);
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.flush();
				ps.close();
			}
		}
		return ps;
	}
	
	
	/**
	 *<pre>
	 * 说       明: 输出到浏览器
	 * @param response
	 * @param msg
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-5-4下午1:52:51
	 *</pre>
	 */
	public static PrintWriter writeResultToBrowser(HttpServletResponse response,String msg){
		msg = msg == null ? "" : msg;
		//从servletActionContext上下文中,获取response对象
		//设置向浏览器返回信息的格式为text/html格式
		response.setContentType("text/html");
		//设置response编码为utf-8
		response.setCharacterEncoding("utf-8");
		
		PrintWriter ps = null;
		try {
			ps = response.getWriter();
			ps.write(msg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(ps != null){
				ps.flush();
				ps.close();
			}
		}
		return ps;
	}
	
	/**
	 *<pre>
	 * 说       明: 输出到浏览器
	 * @param response
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-5-4下午1:54:43
	 *</pre>
	 */
	public static PrintWriter writeResultToBrowser(HttpServletResponse response){
		//设置向浏览器返回信息的格式为text/html格式
		response.setContentType("text/html");
		//设置response编码为utf-8
		response.setCharacterEncoding("utf-8");
		
		PrintWriter ps = null;
		try {
			ps = response.getWriter();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * <pre>
	 * 说       明: JUNIT调试时,统一输出调试信息
	 * @param resultUtil TODO PRINTUTIL
	 * @param stackTraceElement 
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-25上午10:31:50
	 * </pre>
	 */
	public static void junitPrint(AskDrResultUtil resultUtil, StackTraceElement[] stackTraceElement)
			throws Exception {
		
		String methodName = stackTraceElement[1].getMethodName();
		System.out.println(methodName + ":DATA=" + JSON.toJSON(resultUtil.getData()));
		System.out.println(methodName + ":返回结果=" + JSON.toJSONString(resultUtil) + "\n");
	}
	
	public static void junitPrint(ResultUtil resultUtil, StackTraceElement[] stackTraceElement)
			throws Exception {
		
		String methodName = stackTraceElement[1].getMethodName();
		System.out.println(methodName + ":DATA=" + JSON.toJSON(resultUtil.getData()));
		System.out.println(methodName + ":返回结果=" + JSON.toJSONString(resultUtil) + "\n");
	}
	
	public static void junitPrint(AppResultUtil resultUtil, StackTraceElement[] stackTraceElement)
			throws Exception {
		
		String methodName = stackTraceElement[1].getMethodName();
		System.out.println(methodName + ":DATA=" + JSON.toJSON(resultUtil.getData()));
		System.out.println(methodName + ":返回结果=" + JSON.toJSONString(resultUtil) + "\n");
	}
	
	/**
	 * <pre>
	 * 说   明:  输出JSON格式数据 TODO PRINTUTIL
	 * 
	 * @param response
	 * @param result
	 * @param signKey 
	 * @throws IOException 
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-25下午4:11:20
	 * </pre>
	 */
	public static void printJson(HttpServletResponse response, Object result,
			String signKey) throws IOException, GeneralSecurityException {
		response.setContentType("text/plain;charset=" + Constants.ENCODING_UTF8);
		response.getOutputStream().write(
				QEncodeUtil.aesEncryptNew(new String(JSON.toJSONBytes(result),
						"utf-8"), signKey));
		response.getOutputStream().close();
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 医患通端: 
	 * @param response
	 * @param result
	 * @param signKey
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-19下午5:23:31
	 *</pre>
	 */
	public static void printJsonNoSign(HttpServletResponse response, Object result) throws IOException, GeneralSecurityException {
		response.setContentType("text/plain;charset=" + Constants.ENCODING_UTF8);
		response.getOutputStream().write(new String(JSON.toJSONBytes(result),"utf-8").getBytes());
		response.getOutputStream().close();
	}

	/**
	 * <pre>
	 * 说       明: TODO PRINTUTIL
	 * 
	 * @param response
	 * @param result
	 * @throws IOException 
	 * 涉及版本: V1.0.0
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-11-6下午7:02:17
	 * </pre>
	 */
	public static IhygeiaResultUtil printJsonStr(HttpServletResponse response,
			Object result) throws Exception {
		String resultUtil = JSON.toJSONString(result).replace("\"", "'");
		response.setContentType("text/plain;charset=" + Constants.ENCODING_UTF8);
		response.getWriter().write(
				"{success:true,resultUtil:" + resultUtil + "}");
		return new IhygeiaResultUtil();
	}
	
	public static void main(String[] args) {
		
	}
}
