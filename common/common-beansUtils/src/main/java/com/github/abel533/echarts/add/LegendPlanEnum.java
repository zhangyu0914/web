package com.github.abel533.echarts.add;

public enum LegendPlanEnum {

	RUING("进行中", "RUING","red", 0), //
	END("已结束", "END","#2F4554", 1), //
	UNSTARTED("未开始", "UNSTARTED","#61A0A8",2),//
	CHECK_ACCEPT("已验收","CHECK_ACCEPT","#799BB9",3)//
	
	;

	private String name;//名称
	private String code;//编码
	private String rgbColor;//rgb颜色
	private Integer index;//

	LegendPlanEnum(String name, String code, String rgbColor, Integer index) {
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

	public static LegendPlanEnum getEnum(Integer index){
		if (index.intValue() == RUING.getIndex().intValue()) {
			return RUING;
		}else if (index.intValue() == END.getIndex().intValue()) {
			return END;
		}else if (index.intValue() == UNSTARTED.getIndex().intValue()) {
			return UNSTARTED;
		}
		return null;
	}
}