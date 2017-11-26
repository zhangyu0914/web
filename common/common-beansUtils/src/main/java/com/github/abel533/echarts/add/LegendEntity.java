package com.github.abel533.echarts.add;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import com.github.abel533.echarts.code.Position;

/**
 * <pre>
 * 功       能: 
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2016-5-14上午11:47:30
 * Q    Q: 308053847
 * </pre>
 */
public class LegendEntity implements Serializable {
	private static final long serialVersionUID = -7523385978320419472L;

	private String name;// 名称
	private Position positon;// 显示位置
	private String formatter;// 配置
	private String tooltipFormatter;// 配置
	private String rgbColor;// RGB颜色
	private Object[] objArray;//
	private Double value = 0.00D;// pie数据
	private String textColor = "white";// 文章颜色：白色
	private String areaStleColor;

	public LegendEntity() {
		super();
	}

	public LegendEntity(String name, Position positon, String formatter,
			String tooltipFormatter, String rgbColor, Double value) {
		super();
		this.name = name;
		this.positon = positon;
		this.formatter = formatter;
		this.tooltipFormatter = tooltipFormatter;
		this.rgbColor = rgbColor;
		this.value = value;
	}
	
	public LegendEntity(String name, Position positon, String formatter,
			String tooltipFormatter, String rgbColor, Double value, String areaStleColor) {
		super();
		this.name = name;
		this.positon = positon;
		this.formatter = formatter;
		this.tooltipFormatter = tooltipFormatter;
		this.rgbColor = rgbColor;
		this.value = value;
		this.areaStleColor = areaStleColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPositon() {
		return positon;
	}

	public void setPositon(Position positon) {
		this.positon = positon;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	/**
	 * <pre>
	 * 说       明: 获取图例数据
	 * @param legendMap
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-14上午11:52:07
	 * </pre>
	 */
	public static String[] getLegend(Map<String, LegendEntity> legendMap) {
		if (legendMap == null) {
			return null;
		}
		String[] array = new String[legendMap.size()];
		int index = 0;
		for (Iterator<String> it = legendMap.keySet().iterator(); it.hasNext();) {
			array[index++] = legendMap.get(it.next()).getName();
		}
		return array;
	}

	public String getTooltipFormatter() {
		return tooltipFormatter;
	}

	public void setTooltipFormatter(String tooltipFormatter) {
		this.tooltipFormatter = tooltipFormatter;
	}

	public String getRgbColor() {
		return rgbColor;
	}

	public void setRgbColor(String rgbColor) {
		this.rgbColor = rgbColor;
	}

	public Object[] getObjArray() {
		return objArray;
	}

	public void setObjArray(Object[] objArray) {
		this.objArray = objArray;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getAreaStleColor() {
		return areaStleColor;
	}

	public void setAreaStleColor(String areaStleColor) {
		this.areaStleColor = areaStleColor;
	}

}
