package com.yw.common.beansUtils.utils.wechat.message;

public class Article {
	// 图文消息名称
	private String title;
	// 图文消息描述
	private String description;
	// 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80，
	private String picurl;
	// 点击图文消息跳转链接
	private String url;

	public Article() {

	}

	public Article(String title, String description, String picurl, String url) {
		this.title = title;
		this.description = description;
		this.picurl = picurl;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
