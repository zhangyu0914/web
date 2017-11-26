package com.github.abel533.echarts.add;

public enum LegendPlanTypeEnum {

	
	PLAN("计划任务", "PLAN","#2F4554", 0), //
	ADD("新增任务", "ADD","red", 1), //
	
	;

	private String name;//名称
	private String code;//编码
	private String rgbColor;//rgb颜色
	private Integer index;//

	LegendPlanTypeEnum(String name, String code, String rgbColor, Integer index) {
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

	public static LegendPlanTypeEnum getEnum(Integer index){
		if (index.intValue() == PLAN.getIndex().intValue()) {
			return PLAN;
		}else if (index.intValue() == ADD.getIndex().intValue()) {
			return ADD;
		}
		return null;
	}
}