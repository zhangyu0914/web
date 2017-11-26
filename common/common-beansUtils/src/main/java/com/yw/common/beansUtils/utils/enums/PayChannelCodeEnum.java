package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 
 * 涉及版本: V3.0.0
 * 创  建  者: Vickey
 * 日       期: 2015-10-12 19:17:39
 * Q    Q: 308053847
 *</pre>
 */
public enum PayChannelCodeEnum {
	
	YINLIAN("银联", "yinlian"),
	ZHIFUBAO("支付宝", "zhifubao"),
	WEIXINZHIFU("微信支付", "weixinzhifu"),
	BAIDUQIANBAO("百度钱包", "baiduqianbao"),
	APPLEPAY("Apple Pay", "applepay"),
	;

	private String name;
	private String code;

	PayChannelCodeEnum(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
