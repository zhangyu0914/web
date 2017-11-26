package com.yw.common.beansUtils.utils.wechat.message;

public class NewsMessage extends BaseMessage {

	private News news;

	public NewsMessage() {
		this.msgtype = "news";
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
