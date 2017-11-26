package com.yw.common.beansUtils.utils.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.resultUtil.AskDrResultUtil;

/**
 * <pre>
 * 功   能: 互亿无线短信发送平台
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-10-3上午9:53:57
 * Q  Q: 308053847
 * </pre>
 */
public class SMSIhuyiUtil {

	private static final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	/**
	 *<pre>
	 * 说   明:  发送
	 * @param mobile
	 * @param content
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-10-4上午11:11:54
	 *</pre>
	 */
	public static AskDrResultUtil sendSms(String mobile, String content,
			String smsUserName, String smsPassword, String smsGateway) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(smsGateway);
		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		NameValuePair[] data = {// 提交短信
				new NameValuePair("account", smsUserName),
				new NameValuePair("password",smsPassword),
				new NameValuePair("mobile", mobile),
				new NameValuePair("content", content), };

		method.setRequestBody(data);
		client.executeMethod(method);

		String SubmitResult = method.getResponseBodyAsString();

		Document doc = DocumentHelper.parseText(SubmitResult);
		Element root = doc.getRootElement();

		String code = root.elementText("code");
		String msg = root.elementText("msg");
		log.info(code+"["+msg+"]");
		Map<String, String> resultData = new HashMap<String, String>();
		resultData.put("code", code);
		resultData.put("msg", msg);
		if (code.equals("2")) {
			return new AskDrResultUtil(ErrorTypeEnum.SUCCESS).setData(resultData);
		}
		return new AskDrResultUtil(ErrorTypeEnum.FAILURE).setData(resultData);

	}

	/**
	 * <pre>
	 * 说 明: 生成验证码
	 * @param numberFlag
	 * @param length
	 * @return
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-25上午11:18:36
	 * </pre>
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
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

	public static void main(String[] args) {
		try {
//			SMS_HYWX_USER_NAME = "cf_ronghan";
//			SMS_HYWX_PASSWORD = "123456";
//			SMS_HYWX_GATEWAY = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
//			System.out.println(SMSIhuyiUtil.sendSms("13641809635", content).getData());
			
			/*AskDrResultUtil resultUtil = SMSIhuyiUtil.sendSms(
					"1", 
					"价格类型:免费，李兴明购买服务成功，价格为：0元",
					"cf_ronghan",
					"123456",
					"http://106.ihuyi.cn/webservice/sms.php?method=Submit");
			System.out.println(resultUtil.getCodeEnum().toString());*/
			String to_phone = "15021008737";
			String link = "http://t.cn/RPlc4jb";
			String hywx_content = String.format("亲爱的用户，您已成功注册上海中山医院，您的账号%s及密码可直接用于登录易问医。易问医是专业的医患随访互动工具，您可通过易问医和主诊医师保持联系，在治疗中得到医生的有效指导，点击链接 %s 安装易问医", 
					to_phone,
					link);
			String jxt_content = "[易问医]" + hywx_content;
			Map<String, String>  resultData = (Map<String, String>) SendSMS.sendSms(
					to_phone,
					 jxt_content,
					"she1990111",
					"sunhw1226",
					"http://service.winic.org/sys_port/gateway/?").getData();
	System.out.println(resultData.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
