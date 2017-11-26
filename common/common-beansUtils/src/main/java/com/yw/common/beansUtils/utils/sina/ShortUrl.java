package com.yw.common.beansUtils.utils.sina;

/**
 * <pre>
 * 功       能: 长链接转短链接
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-11-9上午11:28:51
 * Q    Q: 308053847
 * </pre>
 */
public class ShortUrl {
	private String url_short;// 转换后的短链接，如："http://t.cn/RyhQ2V2",
	private String url_long;// 要转换的长链接，如："http://www.baidu.com",
	private Integer type;// 类型，如：39

	public ShortUrl() {
		super();
	}

	public String getUrl_short() {
		return url_short;
	}

	public void setUrl_short(String url_short) {
		this.url_short = url_short;
	}

	public String getUrl_long() {
		return url_long;
	}

	public void setUrl_long(String url_long) {
		this.url_long = url_long;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
