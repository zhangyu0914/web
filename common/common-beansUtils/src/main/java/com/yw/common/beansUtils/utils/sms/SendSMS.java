package com.yw.common.beansUtils.utils.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.utils.NetTool;
import com.yw.common.beansUtils.utils.resultUtil.AskDrResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功   能: 吉信通短信发送平台
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-10-3上午9:57:24
 * Q  Q: 308053847
 *</pre>
 */
public class SendSMS extends BaseDto{
	
	public static void main(String[] args) {
		try {
			String content = "[易问医]13816832809，您于2016年10月19日完成一次焦虑自测量表（简化版）测试且自动注册成为易问医用户，查看更多疾病知识请登录易问医app。您的易问医帐号为患者本人手机号，密码为123456(密码可以在易问医app中修改)易问医下载链接http://t.cn/RPlc4jb";
//			String content = "易问医";
			AskDrResultUtil resultUtil = sendSms("15800979547", content, "she1990111", "sunhw1226", "http://service.winic.org/sys_port/gateway/?");
			System.out.println(resultUtil.getData().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param mobile
	 * @param content
	 * @param smsUserName
	 * @param smsPassword
	 * @param smsGateway
	 * @return
	 * @throws UnsupportedEncodingException
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-3-4上午10:04:38
	 *</pre>
	 */
	@SuppressWarnings("unused")
	public static AskDrResultUtil sendSms(String mobile, String content,
			String smsUserName, String smsPassword, String smsGateway)throws UnsupportedEncodingException {
		AskDrResultUtil resultUtil = new AskDrResultUtil();
		Integer x_ac = 10;// 发送信息
		HttpURLConnection httpconn = null;
		String result = "-20";
		String memo = content.length() < 70 ? content.trim() : content.trim().substring(0, 70);
		StringBuilder sb = new StringBuilder();
		sb.append(smsGateway);
		sb.append("id=").append(smsUserName);
		sb.append("&pwd=").append(smsPassword);
		sb.append("&to=").append(mobile);
		sb.append("&content=").append(URLEncoder.encode(content, "gb2312")); // 注意乱码的话换成gb2312编码
		Map<String, String> resultData = new HashMap<String, String>();
		resultData.put("code", "");
		resultData.put("msg", "发送失败");
		
		try {
			URL url = new URL(sb.toString());
			httpconn = (HttpURLConnection) url.openConnection();
            result = new String(NetTool.read(httpconn.getInputStream()));
            if (!StringUtils.isBlank(result)) {
				
            	if (result.indexOf("000") == 0) {
					
            		resultData.put("code", result);
            		resultData.put("msg", "备用发送成功");
				}else{
					resultData.put("msg", "发送失败[" + result + "]");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpconn != null) {
				httpconn.disconnect();
				httpconn = null;
			}
		}
		return resultUtil.setData(resultData);
	}
	
	/**
	*<pre>
	* 说 明: 生成验证码
	* @param numberFlag
	* @param length
	* @return
	* 创建者: 陈  宏(Luke)
	* 日 期: 2014-7-25上午11:18:36
	*</pre>
	 */
	public static String createRandom(boolean numberFlag, int length){  
		  String retStr = "";  
		  String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";  
		  int len = strTable.length();  
		  boolean bDone = true;  
		  do {  
		   retStr = "";  
		   int count = 0;  
		   for (int i = 0; i < length; i++) {  
		    double dblR = Math.random() * len;  
		    int intR = (int) Math.floor(dblR);  
		    char c = strTable.charAt(intR);  
		    if (('0' <= c) && (c <= '9')) {  
		     count++;  
		    }  
		    retStr += strTable.charAt(intR);  
		   }  
		   if (count >= 2) {  
		    bDone = false;  
		   }  
		  } while (bDone);  
		  
		  return retStr;  
		 }  
}
