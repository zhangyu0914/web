package com.yw.common.beansUtils.entity;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.TradeDto;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
public class TradeEntity extends BaseEntity {

	private static final long serialVersionUID = -2994853458426L;
	
	@Length(min=0, max=100, message = "WEBPLATFORM.TRADE.TRADENAME")
	private String tradeName;// 厂商名称
	@Length(min=0, max=10, message = "WEBPLATFORM.TRADE.TRADECODE")
	private String tradeCode;// 厂商代码
	@Length(min=0, max=100, message = "WEBPLATFORM.TRADE.ADDRESS")
	private String address;// 地址

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	
	
	//无参构造方法
	public TradeEntity() {
		super();
	}
	
	//TID参数构造方法
	public TradeEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public TradeEntity(TradeDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	/**
	 * @param tradeCode
	 */
	public TradeEntity(String tid, String tradeCode) {
		super();
		this.setTid(tid);
		this.tradeCode = tradeCode;
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
