package com.yw.common.beansUtils.utils.weixin;

public class WXConfig {
	
	public static final String ENCODE ="UTF-8";
	
	//session存储access_token
	public static final String WX_ACCESS_TOKEN = "wx_access_token";
	
	//=======【微信数据请求地址】===================================
	//获取auth_code地址
	public static final String AUTH_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
	//获取access_token地址
	public static final String OAUTH2_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	//创建公众号二维码地址
	public static final String WEIXIN_MP_QECODE_CREATE_URL ="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
	//OpenId获取地址
	public static final String GET_OPENID_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
}
