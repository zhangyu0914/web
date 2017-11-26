package com.yw.common.beansUtils.utils.mail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;


/**
 * 邮件发送类
 * 
 * @author Andy.Chen
 * 
 */
public class Mail {
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	//邮件server地址
	public static String EMAIL_HOST;
	//邮件server端口
	public static String EMAIL_PORT;
	//邮件发送帐号
	public static String EMAIL_NAME;
	//邮件发送密码
	public static String EMAIL_PASSWORD;
	
	public static final String FORMAT_TEXT = "text";
	public static final String FORMAT_HTML = "html";
	
	/**
	 * 发送邮件
	 * @param title 收件人姓名
	 * @param email 收件邮箱号
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param format  邮件发送格式：txt\html
	 * @param eafList 附件
	 * 		
	 */
	public static boolean sendMail(String title, String email, String subject,String content, String format, List<EmailAttachFile> eafList) {
		boolean status=false;
		// 设置邮件信息
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(EMAIL_HOST);
		mailInfo.setMailServerPort(EMAIL_PORT);
		mailInfo.setValidate(true);
		mailInfo.setUserName(EMAIL_NAME);
		mailInfo.setPassword(EMAIL_PASSWORD);// 您的邮箱密码
		mailInfo.setFromAddress(EMAIL_NAME);
		mailInfo.setToAddress(email);// 要发送的邮箱
		mailInfo.setSubject(subject);// 邮件主题
		mailInfo.setPersonalName(title);//签名
		mailInfo.setContent(content);
		// 发送邮件
		MailSender sms = new MailSender();
		//添加附件
		if (null !=eafList &&0 < eafList.size()) {
			for (EmailAttachFile emailAttachFile : eafList) {
				mailInfo.getAttachFiles().add(emailAttachFile);
			}
			return sms.sendAttachMail(mailInfo);// 发送html格式　
			
		}
		
		if ("txt".equalsIgnoreCase(format)) {
			status=sms.sendTextMail(mailInfo);// 发送文体格式
		} else if ("html".equalsIgnoreCase(format)) {
			status=sms.sendHtmlMail(mailInfo);// 发送html格式　
		}
		return status;
	}
	
	public static void main(String[] args) {

		Mail.EMAIL_HOST = "smtp.ym.163.com";
		Mail.EMAIL_PORT = "25";
		Mail.EMAIL_NAME = "mail@i-hygeia.com";
		Mail.EMAIL_PASSWORD = "i-hygeia123456";
		
		Mail.sendMail("易问医", "vickey.cll@foxmail.com", "易问医", "content", "html", null);
		
	
		
	}

}
