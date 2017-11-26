package com.yw.common.beansUtils.utils.wechat.message;

public class TextMessage extends BaseMessage {

	private Text text;

	public TextMessage() {
		this.msgtype = "text";
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

}
