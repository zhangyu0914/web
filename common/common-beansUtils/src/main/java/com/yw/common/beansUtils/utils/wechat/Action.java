package com.yw.common.beansUtils.utils.wechat;

public enum Action {
	
	SUBSCRIBE("关注微信公众号", 0),
	SUBSCRIBE_BY_DOCTOR_QR_CODE("通过扫描医生二维码关注微信公众号", 1),
	PRE_REGISTER_SUCCESS("预注册成功", 2),
	
	PAY_PERSONAL_SERVICE("支付常规服务", 3),
	APPLY_FREE_PERSONALL_SERVICE("申请免费常规服务", 4),
	
	APPLY_PROJECT_SERVICE("申请项目服务", 5),
	APPLY_FREE_PROJECT_SERVICE("申请免费项目服务", 6),
	
	AGREE_PERSONAL_SERVICE("同意常规服务申请", 7),
	AGREE_FREE_PERSONALL_SERVICE("同意免费常规服务申请", 8),
	
	AGREE_PROJECT_SERVICE("同意项目服务申请", 9),
	AGREE_FREE_PROJECT_SERVICE("同意免费项目服务申请", 10),
	
	PROJECT_SERVICE_EFFECT("项目服务生效", 11), // 包括收费、免费的项目服务
	
	REFUSE_PERSONAL_SERVICE("拒绝常规服务申请", 12),
	REFUSE_FREE_PERSONAL_SERVICE("拒绝免费常规服务申请", 13),
	
	REFUSE_PROJECT_SERVICE("拒绝项目服务申请", 14),
	REFUSE_FREE_PROJECT_SERVICE("拒绝免费项目服务申请", 15),
	
	REFUND("退款", 16),
	REFUND_SUCCESS("退款成功", 17),
	;

	private String name;
	private int code;

	Action(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
