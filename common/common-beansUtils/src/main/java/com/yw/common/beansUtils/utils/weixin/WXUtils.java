package com.yw.common.beansUtils.utils.weixin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.ZxingUtil;
import com.yw.common.beansUtils.utils.file.FileUtil;

public class WXUtils {
	public static OauthDataEntity getWeiXinOauth2Token(String appId,String appSecret) throws Exception{

		String oauth2Url = WXConfig.OAUTH2_URL;

		OauthDataEntity wat = null;
		String requestUrl = oauth2Url.format(oauth2Url, appId, appSecret);
		JSONObject ret=UrlUtil.httpGet(requestUrl);
		if(ret!=null){
			wat =JavaBeanUtil.jsonToJavaBean(ret.toJSONString(), OauthDataEntity.class);
		}
		return wat;
	}
	
	public static QrcodeResponse getWeiXinQrcode(QrcodeRequest request,String token) throws Exception{

		String oauth2Url = WXConfig.WEIXIN_MP_QECODE_CREATE_URL;

		QrcodeResponse wat = null;
		String requestUrl = oauth2Url.format(oauth2Url, token);
		JSONObject ret=UrlUtil.httpPost(requestUrl,JSON.parseObject(JSON.toJSONString(request)),false);
		if(ret!=null){
			wat =JavaBeanUtil.jsonToJavaBean(ret.toJSONString(), QrcodeResponse.class);
		}
		return wat;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OauthDataEntity oauth=getWeiXinOauth2Token("wx505378afff17042f","d33110c5fd45ffb5b1b7869f633f5192");
		
			if(oauth!=null){
				QrcodeRequest request=new QrcodeRequest();
				request.setAction_name("QR_LIMIT_STR_SCENE");
				request.setAction_info(null, "qrcodeType=0&doctorId=7dcbb489-db9b-4c09-8c5e-18e805624d78");
				QrcodeResponse ret=getWeiXinQrcode(request,oauth.getAccess_token());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				File file = new File("F:\\temp\\1483446957792.jpg");
				   FileInputStream in = null;
				   
				   try {
				   in = new FileInputStream(file);
				   byte[] buffer = new byte[in.available()];
				   in.read(buffer);
				   out.write(buffer);
				   } catch (Exception e) {
				   e.printStackTrace();
				   } finally {
				   try {
				   if (in != null)
				   in.close();
				   } catch (IOException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
				   }
				   }
				FileUtil.writeFile("F:\\temp\\logo.jpg",ZxingUtil.encode(ret.getUrl(), 900, 900, out.toByteArray(), true));
				
				System.out.println(JavaBeanUtil.javaBeanToString(ret));
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
