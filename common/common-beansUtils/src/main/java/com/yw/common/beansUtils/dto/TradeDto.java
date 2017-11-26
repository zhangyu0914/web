package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
public class TradeDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String tradeName;// 厂商名称
	private String tradeCode;// 厂商代码
	private String address;// 地址
	
	public TradeDto() {
		super();
	}
	
	public TradeDto(TradeEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setTradeCode(data.getTradeCode());
			this.setTradeName(data.getTradeName());
			this.setAddress(data.getAddress());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
