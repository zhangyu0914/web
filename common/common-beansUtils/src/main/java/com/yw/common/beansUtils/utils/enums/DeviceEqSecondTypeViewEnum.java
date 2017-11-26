package com.yw.common.beansUtils.utils.enums;

/**
 *<pre>
 * 功       能: 设备感知类型
 * 涉及版本: V1.0.0
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:41
 * Q    Q: 308053847
 *</pre>
 */
public enum DeviceEqSecondTypeViewEnum {

	VIEW_BABY("婴儿标签","#84a3fc",0),//
	VIEW_MOTHER("母亲标签", "#b8e986",1),//
	VIEW_ENVIRONMENT("环境标签","#cccccc",2),//
	
	VIEW_ASSET_TAG("资产标签","#FFF68F",3),//
	VIEW_BABY_PROTECTION_TAGS("婴儿防盗标签","#FFE4E1",4),//
	VIEW_RECHARGEABLE_BABY_PROTECTION_TAGS("可充电婴儿防盗标签","#FF6A6A",5),//
	VIEW_CALL_UNIT("呼叫单元","#FF6A6A",6),//
	VIEW_CARD_TAG("卡片标签","#FF6A6A",7),//
	VIEW_THE_NURSE_LABEL("护士标签","#FF6A6A",9),//
	VIEW_SMART_INTERACTIVE_TERMINAL("智能交互终端","#FF6A6A",10),//
	VIEW_SMART_MATTRESS_MOTHERBOARD("智能床垫主板","#FF6A6A",11),//
	VIEW_SMART_BED_SENSOR("智能床传感器","#FF6A6A",12),//
	VIEW_SMART_BED_NETWORK_ADAPTER_WY("智能床网络适配器B2-M-WY","#FF6A6A",13),//
	VIEW_SMART_BED_NETWORK_ADAPTER_GA("智能床网络适配器B2-M-GA","#FF6A6A",14),//
	VIEW_THE_PATIENT_LABEL("病人标签","#FF6A6A",15),//
	VIW_RECHARGEABLE_BABY_PROTECTION_TAGS("可充电婴儿防盗标签","#FF6A6A",16),//
	VIW_READER("阅读器","#FF6A6A",17),//

	
	;

	private String name;
	private String rgbColor;
	private Integer code;

	DeviceEqSecondTypeViewEnum(String name, String rgbColor, Integer code) {
		this.name = name;
		this.rgbColor = rgbColor;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public static DeviceEqSecondTypeViewEnum getTypeName(Integer code) {
		if (code == null) {
			return null;
		}else if (code == VIEW_BABY.getCode()) {
			return VIEW_BABY;
		}else if (code == VIEW_MOTHER.getCode()) {
			return VIEW_MOTHER;
		}else if (code == VIEW_ENVIRONMENT.getCode()) {
			return VIEW_ENVIRONMENT;
		}else if (code == VIEW_ASSET_TAG.getCode()) {
			return VIEW_ASSET_TAG;
		}else if (code == VIEW_BABY_PROTECTION_TAGS.getCode()) {
			return VIEW_BABY_PROTECTION_TAGS;
		}else if (code == VIEW_RECHARGEABLE_BABY_PROTECTION_TAGS.getCode()) {
			return VIEW_RECHARGEABLE_BABY_PROTECTION_TAGS;
		}
		return null;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}
	
}
