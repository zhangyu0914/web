package com.github.abel533.echarts.add;

import com.yw.common.beansUtils.utils.string.StringUtils;

public enum LegendEnum {
	
	APP_TOTAL("应用总数", "APP_TOTAL","#939","0"),//
	APP_NORMAL("正常应用", "APP_NORMAL","#61A0A8","0"),//
	APP_ERROR("异常应用", "APP_ERROR","#D48265","1"),//
	
	EQ_NORMAL("正常设备", "EQ_NORMAL","#79b9ff","0"),//
	EQ_ERROR("异常设备", "EQ_ERROR","#ffe07d","0"),//
	
	EQ_ONLINE("在线设备", "EQ_ONLINE","#94abea ","0"),//
	EQ_OFLINE("离线设备", "EQ_OFLINE","#91ead6","0"),//
	
	EQ_BABY("婴儿防盗", "EQ_BABY","#84a3fc","0"),//
	EQ_PEOPLE("人员定位", "EQ_PEOPLE","#b8e986","0"),//
	EQ_ASSET("资产定位", "EQ_ASSET","#cccccc","0"),//
	
	VIEW_EQ_ONLINE("设备在线", "EQ_ONLINE","#4bd8ff","0"),//
	VIEW_EQ_OFLINE("设备离线", "EQ_OFLINE","#ffe07d","0"),//
	
	VIEW_EQ_NORMAL("正常设备", "EQ_NORMAL","#50e3c2","0"),//
	VIEW_EQ_ERROR("异常设备", "EQ_ERROR","#ff7c7c","0"),//
	
	;

	private String name;//名称
	private String code;//编码
	private String rgbColor;//rgb颜色
	private String value;

	LegendEnum(String name, String code, String rgbColor, String value) {
		this.setName(name);
		this.setCode(code);
		this.setRgbColor(rgbColor);
		this.setValue(value);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String getTypeName(String key){
		if (StringUtils.isBlank(key)) {
			return null;
		}else if (key.equals(APP_TOTAL.toString())) {
			return APP_TOTAL.getName();
		}else if (key.equals(APP_NORMAL.toString())) {
			return APP_NORMAL.getName();
		}else if (key.equals(APP_ERROR.toString())) {
			return APP_ERROR.getName();
		}
		return null;
	}
}
