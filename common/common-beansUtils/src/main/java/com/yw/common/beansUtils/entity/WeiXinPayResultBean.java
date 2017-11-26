package com.yw.common.beansUtils.entity;

import java.io.Serializable;
import java.util.Map;

public class WeiXinPayResultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6140302681204559683L;

	private String appid;
	private String bankType;
	private String cashFee;
	private String feeType;
	private String isSubscribe;
	private String mchId;
	private String nonceStr;
	private String openid;
	private String outTradeNo;
	private String resultCode;
	private String returnCode;
	private String sign;
	private String timeEnd;
	private String totalFee;
	private String tradeType;
	private String transactionId;

	public WeiXinPayResultBean() {
		super();
	}
	
	public WeiXinPayResultBean(Map<String,String> data) {
		if(null != data){
			this.setAppid(data.get("appid"));
			this.setBankType(data.get("bank_type"));
			this.setCashFee(data.get("cash_fee"));
			this.setFeeType(data.get("fee_type"));
			this.setIsSubscribe(data.get("is_subscribe"));
			this.setMchId(data.get("mch_id"));
			this.setNonceStr(data.get("nonce_str"));
			this.setOpenid(data.get("openid"));
			this.setOutTradeNo(data.get("out_trade_no"));
			this.setResultCode(data.get("result_code"));
			this.setReturnCode(data.get("return_code"));
			this.setSign(data.get("sign"));
			this.setTimeEnd(data.get("time_end"));
			this.setTotalFee(data.get("total_fee"));
			this.setTradeType(data.get("trade_type"));
			this.setTransactionId(data.get("transaction_id"));
		}
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCashFee() {
		return cashFee;
	}

	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("appid:").append(appid).append("，");
		sb.append("bankType:").append(bankType).append("，");
		sb.append("cashFee:").append(cashFee).append("，");
		sb.append("feeType:").append(feeType).append("，");
		sb.append("isSubscribe:").append(isSubscribe).append("，");
		sb.append("mchId:").append(mchId).append("，");
		sb.append("nonceStr:").append(nonceStr).append("，");
		sb.append("openid:").append(openid).append("，");
		sb.append("outTradeNo:").append(outTradeNo).append("，");
		sb.append("resultCode:").append(resultCode).append("，");
		sb.append("returnCode:").append(returnCode).append("，");
		sb.append("sign:").append(sign).append("，");
		sb.append("timeEnd:").append(timeEnd).append("，");
		sb.append("totalFee:").append(totalFee).append("，");
		sb.append("tradeType:").append(tradeType).append("，");
		sb.append("transactionId:").append(transactionId).append("");
		
		return sb.toString();
	}

}
