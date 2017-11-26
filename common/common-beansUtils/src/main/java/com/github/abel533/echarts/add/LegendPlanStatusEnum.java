package com.github.abel533.echarts.add;

public enum LegendPlanStatusEnum {

	
	PLANING("正常", "PLANING","red", 0), //
	CHANGE("变化", "DELAY","#2F4554", 1), //
	DELAY("延期", "DELAY","#2F4554", 2), //
	ADVANCE("提前", "ADVANCE","#2F4554", 3), //
	
	;

	private String name;//名称
	private String code;//编码
	private String rgbColor;//rgb颜色
	private Integer index;//

	LegendPlanStatusEnum(String name, String code, String rgbColor, Integer index) {
		this.setName(name);
		this.setCode(code);
		this.setRgbColor(rgbColor);
		this.setIndex(index);
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public static LegendPlanStatusEnum getEnum(Integer index){
		if (index.intValue() == PLANING.getIndex().intValue()) {
			return PLANING;
		}else if (index.intValue() == CHANGE.getIndex().intValue()) {
			return CHANGE;
		}
		return null;
	}
}