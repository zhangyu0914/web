package com.yw.common.beansUtils.utils.wechat;

public enum ParamKey {
	
	PATIENT_TID("patientTid"),
	DOCTOR_TID("doctorTid"),
	ORDER_TID("orderTid"),	
	DOCTOR_NAME("医生姓名"),
	DEPATMENT_NAME_AND_DOCTOR_NAME("科室-医生姓名"),
	SERVICE_TYPE("服务类型"),
	PAY_DEADLINE("付款截止时间"),
	;

	private String name;

	ParamKey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
