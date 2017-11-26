package com.yw.common.beansUtils.utils.fanyi;

import java.util.List;

/**
 * <pre>
 * 功       能: 百度翻译模型
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-10-29下午3:22:12
 * Q    Q: 308053847
 * </pre>
 */
public class TranslateMode {
	private String from;// 原文语言
	private String to;// 目标语言
	private List<Data> data;// 返回数据

	public class Data {
		private String dst;// 目标结果
		private String src;// 原文

		public String getDst() {
			return dst;
		}

		public void setDst(String dst) {
			this.dst = dst;
		}

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

}