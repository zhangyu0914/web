package com.yw.common.beansUtils.entity;

import java.util.HashMap;
import java.util.Map;

import com.yw.common.beansUtils.utils.enums.PayChannelCodeEnum;
import com.yw.common.beansUtils.utils.unionpay.acp.demo.DemoBase;
import com.yw.common.beansUtils.utils.unionpay.acp.sdk.SDKConfig;
import com.yw.common.beansUtils.utils.unionpay.acp.sdk.SDKUtil;

public class UnionPay extends DemoBase {
	
	
	public Map<String,String>ServerToRequestQuery(String merId,String orderId,String txnTime)
	{
		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		data.put("encoding", "UTF-8");
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 
		data.put("txnType", "00");
		// 交易子类型 
		data.put("txnSubType", "00");
		// 业务类型
		data.put("bizType", "000000");
		// 渠道类型，07-PC，08-手机
		data.put("channelType", "08");
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码，请改成自己的商户号
		data.put("merId", merId);
		// 商户订单号，请修改被查询的交易的订单号
		data.put("orderId", orderId);
		// 订单发送时间，请修改被查询的交易的订单发送时间
		data.put("txnTime", txnTime);

		data = SDKUtil.signData(data,SDKUtil.encoding_UTF8);

		// 交易请求url 从配置文件读取
		String url = SDKConfig.getConfig().getSingleQueryUrl();

		Map<String, String> resmap = SDKUtil.submitUrl(data, url,SDKUtil.encoding_UTF8);

		System.out.println("请求报文=["+data.toString()+"]");
		System.out.println("应答报文=["+resmap.toString()+"]");
		
		return resmap;
	}

	public Map<String,String> ServerToRequestPay(String merId, String forntUrl,
			String backUrl, String orderId, String txnTime, String txnAmt) {

		/**
		 * 参数初始化 在java main 方式运行时必须每次都执行加载 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		 */
//		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		data.put("encoding", "UTF-8");
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型
		data.put("bizType", "000201");
		// 渠道类型，07-PC，08-手机
		data.put("channelType", "08");
		// 前台通知地址 ，控件接入方式无作用
		data.put("frontUrl", forntUrl);
		// 后台通知地址
		data.put("backUrl",backUrl);
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码，请改成自己的商户号，测试商户号： 777290058112650
		data.put("merId", merId);
		// 商户订单号，8-40位数字字母
		data.put("orderId",orderId);
		// 订单发送时间，取系统时间
		data.put("txnTime",txnTime);
		// 交易金额，单位分
		data.put("txnAmt", txnAmt);
		// 交易币种  156 人民币
		data.put("currencyCode", "156");
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		data.put("reqReserved", PayChannelCodeEnum.YINLIAN.getCode());
		// 订单描述，可不上送，上送时控件中会显示该信息
		// data.put("orderDesc", "订单描述");

		data = SDKUtil.signData(data,SDKUtil.encoding_UTF8);

		// 交易请求url 从配置文件读取
		String requestAppUrl = SDKConfig.getConfig().getAppRequestUrl();

		Map<String, String> resmap = SDKUtil.submitUrl(data, requestAppUrl, SDKUtil.encoding_UTF8);
		return resmap;
	}
}
