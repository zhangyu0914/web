package com.yw.common.beansUtils.utils;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.abel533.echarts.DataZoom;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ParallelAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.TimeAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.DataZoomType;
import com.github.abel533.echarts.code.FontStyle;
import com.github.abel533.echarts.code.FontWeight;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Emphasis;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.yw.common.beansUtils.utils.date.DateUtils;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.string.StringUtils;



/**
 *<pre>
 * 功       能: 流程
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2016-05-01 15:38:33
 * Q    Q: 308053847
 *</pre>
 */
@Service
public class ReportUtil {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param noSelected TODO
	 * @param right TODO
	 * @param top TODO
	 * @param left TODO
	 * @param formatter TODO
	 * @param legendArray
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午12:37:25
	 *</pre>
	 */
	public static Option getLegend(Option option, boolean noSelected,Orient orient, X x, Y y, String right, String top, String left, String formatter, Object... legendArray){
		
		int index = 0;
		String tempStr = "";
		for (Object str : legendArray) {
			
			tempStr = (String) str;//++ index + "-" + 
			option.legend().data().add(tempStr);
			option.legend().selected(tempStr, noSelected);//默认选中
		}
		option.legend().orient(orient != null ? orient : Orient.vertical);//垂直显示
//		option.legend().textStyle().fontSize(15);//字体大小
//		option.legend().textStyle().fontWeight(FontWeight.bolder);//加粗
//		option.legend().align(Align.left);//图标显示位置
		option.legend().x(x);
		option.legend().setRight(right);
		option.legend().setTop(top);
		option.legend().setLeft(left);
		option.legend().formatter(formatter);
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param trigger TODO
	 * @param pointerType TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午12:40:47
	 *</pre>
	 */
	public static Option getTooltip(Option option, String formatter, Trigger trigger, PointerType pointerType){
		option.tooltip().trigger(trigger);
		option.tooltip().formatter(formatter);
//		option.tooltip().triggerOn(TriggerOn.mousemove);//鼠标移动时触发
//		option.tooltip().enterable(true);//鼠标可以到详情气泡中
//		option.tooltip().backgroundColor("red");//背景颜色
		option.tooltip().axisPointer().type(pointerType);
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午6:02:43
	 *</pre>
	 */
	public static Option getToolbox(Option option){
		option.toolbox().feature(Tool.saveAsImage);
		option.toolbox().x(X.center);
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param name
	 * @param minDate
	 * @param maxDate TODO
	 * @param enabledTextStyle TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午1:00:36
	 *</pre>
	 */
	public static Option getXAxisTime(Option option, final String name, 
			final String minDate, final String maxDate, final boolean enabledTextStyle){
		option.xAxis().add(new TimeAxis(){
			@Override
			public String getName() {
				return name;
			}
			@Override
			public Object getMin() {
				try {
					if (!StringUtils.isBlank(minDate)) {
						return DateUtils.format(minDate + DateUtils.PARAM_TIME_STAR, 
								DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS).getTime();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return super.getMin();
			}
			@Override
			public Object getMax() {
				try {
					if (!StringUtils.isBlank(maxDate)) {
						return DateUtils.format(maxDate + DateUtils.PARAM_TIME_STAR, 
								DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS).getTime();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return super.getMax();
			}
			@Override
			public AxisLabel getAxisLabel() {//设置字体
				if (enabledTextStyle) {
					AxisLabel axisLabel = new AxisLabel();
					axisLabel.textStyle().fontSize(15);
					axisLabel.textStyle().fontWeight(FontWeight.bolder);
					axisLabel.textStyle().fontStyle(FontStyle.italic);
					axisLabel.interval(0);
					axisLabel.show(true);
					axisLabel.rotate(0);//旋转
					axisLabel.margin(5);//坐标轴文本标签与坐标轴的间距
					return axisLabel;
				}
				return super.getAxisLabel();
			}
			@Override
			public Integer getSplitNumber() {
				return 1;
			}
		});
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param name
	 * @param min TODO
	 * @param max TODO
	 * @param enabledTextStyle TODO
	 * @param lineStyleColor TODO
	 * @param minDate
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午5:35:07
	 *</pre>
	 */
	public static Option getXAxisValue(Option option, final String name, 
			final Integer min, final Integer max, final boolean enabledTextStyle, final String lineStyleColor){
		option.xAxis().add(new ValueAxis(){
			@Override
			public String getName() {
				return name;
			}
			@Override
			public Boolean getShow() {
				return true;
			}
			@Override
			public Object getInterval() {
				return super.getInterval();
			}
			@Override
			public Object getMin() {
				if (StringUtils.isBlank(min)) {
					return super.getMin();
				}
				return min;
			}
			@Override
			public Object getMax() {
				return null;
			}
			
			@Override
			public AxisLine getAxisLine() {
				if (!StringUtils.isBlank(lineStyleColor)) {
					
					AxisLine axisLine = new AxisLine();
					LineStyle lineStyle = new LineStyle();
					lineStyle.setColor(lineStyleColor);
					axisLine.setLineStyle(lineStyle);
					return axisLine;
				}
				return super.getAxisLine();
			}
		});
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param name
	 * @param enabledStyle TODO
	 * @param splitLineShow TODO
	 * @param lineStyleColor TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-9下午10:30:28
	 *</pre>
	 */
	public static Option getXAxisCategory(Option option, final String name, final boolean enabledStyle,final boolean splitLineShow, final String lineStyleColor, final Object... objData){
		option.xAxis().add(new CategoryAxis(){
			@Override
			public String getName() {
				return name;
			}
			@Override
			public List getData() {
				return Arrays.asList(objData);
			}
			@Override
			public AxisLabel getAxisLabel() {
				if (enabledStyle) {
					
					AxisLabel axisLabel = new AxisLabel();
					axisLabel.textStyle().fontSize(15);
					axisLabel.textStyle().fontWeight(FontWeight.bolder);
					axisLabel.textStyle().fontStyle(FontStyle.italic);
					return axisLabel;
				}
				return super.getAxisLabel();
			}
			@Override
			public SplitLine getSplitLine() {
				SplitLine sl = new SplitLine();
				sl.show(splitLineShow);
				return sl;
			}
			@Override
			public AxisLine getAxisLine() {
				if (!StringUtils.isBlank(lineStyleColor)) {
					
					AxisLine axisLine = new AxisLine();
					LineStyle lineStyle = new LineStyle();
					lineStyle.setColor(lineStyleColor);
					axisLine.setLineStyle(lineStyle);
					return axisLine;
				}
				return super.getAxisLine();
			}
		});
		return option;
	}
	
	
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param option
	 * @param name
	 * @param min TODO
	 * @param max TODO
	 * @param enabledTextStyle TODO
	 * @param splitLineShow TODO
	 * @param lineStyleColor TODO
	 * @param data
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午12:54:47
	 *</pre>
	 */
	public static Option getYAxis(final AxisType axisType, Option option, 
			final String name, final Integer min, 
			final Integer max, final boolean enabledTextStyle, final boolean splitLineShow, final String lineStyleColor, final Object... ydata){
		option.yAxis().add(new Axis(){
			@Override
			public AxisType getType() {
				return axisType;
			}
			@Override
			public String getName() {
				return name;
			}
			@Override
			public List getData() {
				if (ydata != null) {
					List data = new ArrayList();
					for(int i=0; i<ydata.length; i++){
						data.add((i+1) + "-" + ydata[i]);//(i+1) + "-" + 
					}
					return data;
				}
				return super.getData();
			}
			@Override
			public AxisLabel getAxisLabel() {
				if (enabledTextStyle) {
					AxisLabel axisLabel = new AxisLabel();
					axisLabel.textStyle().fontSize(15);
					axisLabel.textStyle().fontWeight(FontWeight.bolder);
					axisLabel.textStyle().fontStyle(FontStyle.italic);
					axisLabel.clickable(true);
					axisLabel.show(true);
					return axisLabel;
				}
				return super.getAxisLabel();
			}
			@Override
			public Object getMin() {
				if (StringUtils.isBlank(min)) {
					return super.getMin();
				}
				return min;
			}
			@Override
			public Object getMax() {
				return null;
			}
			
			@Override
			public SplitLine getSplitLine() {
				SplitLine sl = new SplitLine();
				sl.show(splitLineShow);
				return sl;
			}
			
			@Override
			public AxisLine getAxisLine() {
				if (!StringUtils.isBlank(lineStyleColor)) {
					
					AxisLine axisLine = new AxisLine();
					LineStyle lineStyle = new LineStyle();
					lineStyle.setColor(lineStyleColor);
					axisLine.setLineStyle(lineStyle);
					axisLine.setShow(true);
					return axisLine;
				}
				return super.getAxisLine();
			}
		});
		return option;
	}
	
	
	public static Option getYParallelAxis(final AxisType axisType, Option option, 
			final String name, final Integer min, 
			final Integer max, final boolean enabledTextStyle, final boolean splitLineShow, final String lineStyleColor, String nameTextStyle, final Object... ydata){
		option.yAxis().add(new ParallelAxis(){
			@Override
			public AxisType getType() {
				return axisType;
			}
			@Override
			public String getName() {
				return name;
			}
			@Override
			public List getData() {
				if (ydata != null) {
					List data = new ArrayList();
					for(int i=0; i<ydata.length; i++){
						data.add((i+1) + "-" + ydata[i]);//(i+1) + "-" + 
					}
					return data;
				}
				return super.getData();
			}
			@Override
			public AxisLabel getAxisLabel() {
				if (enabledTextStyle) {
					AxisLabel axisLabel = new AxisLabel();
					axisLabel.textStyle().fontSize(15);
					axisLabel.textStyle().fontWeight(FontWeight.bolder);
					axisLabel.textStyle().fontStyle(FontStyle.italic);
					axisLabel.clickable(true);
					axisLabel.show(true);
					return axisLabel;
				}
				return super.getAxisLabel();
			}
			@Override
			public Object getMin() {
				if (StringUtils.isBlank(min)) {
					return super.getMin();
				}
				return min;
			}
			@Override
			public Object getMax() {
				return null;
			}
			
			@Override
			public SplitLine getSplitLine() {
				SplitLine sl = new SplitLine();
				sl.show(splitLineShow);
				return sl;
			}
			
			@Override
			public AxisLine getAxisLine() {
				if (!StringUtils.isBlank(lineStyleColor)) {
					
					AxisLine axisLine = new AxisLine();
					LineStyle lineStyle = new LineStyle();
					lineStyle.setColor(lineStyleColor);
					axisLine.setLineStyle(lineStyle);
					axisLine.setShow(false);
					return axisLine;
				}
				return super.getAxisLine();
			}
		});
		if (!StringUtils.isBlank(nameTextStyle)) {
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameTextStyle().color(nameTextStyle);
		}
		return option;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日下午3:53:50
	 * Q    Q: 308053847
	 * </pre>
	 * @param enabledTextStyle TODO
	 * @param lineStyleColor TODO
	 */
	public static Option getYAxisCategory(Option option, final String name, 
			final boolean enabledTextStyle, final String lineStyleColor, final Object... ydata){
		option.yAxis().add(new CategoryAxis(){
			@Override
			public String getName() {
				return name;
			}
			@Override
			public List getData() {
				if (ydata != null) {
					List data = new ArrayList();
					for(int i=0; i<ydata.length; i++){
						data.add(ydata[i]);//(i+1) + "-" + 
					}
					return data;
				}
				return super.getData();
			}
			@Override
			public AxisLabel getAxisLabel() {//设置字体
				if (enabledTextStyle) {
					AxisLabel axisLabel = new AxisLabel();
					axisLabel.textStyle().fontSize(15);
					axisLabel.textStyle().fontWeight(FontWeight.normal);
					axisLabel.textStyle().fontStyle(FontStyle.normal);
					axisLabel.clickable(true);
					axisLabel.show(true);
					axisLabel.interval(0);
					return axisLabel;
				}
				return super.getAxisLabel();
			}
			
			@Override
			public AxisLine getAxisLine() {
				if (!StringUtils.isBlank(lineStyleColor)) {
					
					AxisLine axisLine = new AxisLine();
					LineStyle lineStyle = new LineStyle();
					lineStyle.setColor(lineStyleColor);
					axisLine.setLineStyle(lineStyle);
					return axisLine;
				}
				return super.getAxisLine();
			}
		});
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: 柱状图系列
	 * @param option
	 * @param name
	 * @param stack
	 * @param position
	 * @param formatter
	 * @param tooltipFormatter
	 * @param rgbColor
	 * @param dataEmplyValue
	 * @param textColor TODO
	 * @param barWidth TODO
	 * @param barData
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-21上午10:23:58
	 *</pre>
	 */
	public static Option getSeriesBar(
			Option option, final String name, 
			final String stack, final Position position,
			final String formatter, final String tooltipFormatter, 
			final String rgbColor, final String dataEmplyValue, 
			final String textColor, final Integer barWidth, final Object... barData){
		
		option.series().add(new Bar() {
			@Override
			public String getName() {
				return name;//名称
			}
			@Override
			public String getStack() {
				return stack;//分类，相同在同一柱子上，否则分开柱子显示
			}
			@Override
			public ItemStyle getLabel() {
				Normal normal = new Normal();
				normal.show(true);
				normal.position(position);
				normal.textStyle().fontSize(15);//默认：10
				normal.textStyle().fontWeight(FontWeight.bolder);//加粗
				if (!StringUtils.isBlank(textColor)) {
					normal.textStyle().color(textColor);
				}
				if (formatter != null) {
					normal.formatter(formatter);
				}
				ItemStyle itemStyle = new ItemStyle();
				itemStyle.normal(normal);
				return itemStyle;
			}
			
			@Override
			public List getData() {
				for(int i=0; i<barData.length; i++){
					if(barData[i] == null){
						barData[i] = dataEmplyValue;
					}
				}
				return Arrays.asList(barData);
			}
			
			@Override
			public String getBarCategoryGap() {
				return "5%";//柱子间距
			}
			@Override
			public Tooltip getTooltip() {
				if (tooltipFormatter == null) {
					return super.getTooltip();
				}
				Tooltip t = new Tooltip();
				t.formatter(tooltipFormatter);//换行：<br/>
				t.textStyle().fontStyle(FontStyle.normal);
				t.textStyle().align(X.left);
				t.textStyle().fontWeight(FontWeight.bolder);
				t.backgroundColor("red");//背景颜色
				return t;
			}
			@Override
			public ItemStyle getItemStyle() {
				if (rgbColor == null) {
					return super.getItemStyle();
				}
				Normal normal = new Normal();
				normal.color(rgbColor);//自定义柱子颜色
				ItemStyle itemStyle = new ItemStyle();
				itemStyle.normal(normal);
				
				return itemStyle;
			}
			@Override
			public Integer getBarWidth() {
				if (!StringUtils.isBlank(barWidth)) {
					return barWidth;
				}
				return super.getBarWidth();
			}
		});
		return option;
	}
	
	/**
	 * <pre>
	 * 说       明: 折线
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日上午10:46:14
	 * Q    Q: 308053847
	 * </pre>
	 * @param areaStyleColor TODO
	 * @param smooth TODO
	 */
	public static Option getSeriesLine(
			Option option, final String name, 
			final String stack, final Position position,
			final String formatter, final String tooltipFormatter, 
			final String rgbColor, final String dataEmplyValue, 
			final String textColor,final String areaStyleColor, 
			final boolean smooth, final Object... barData){
		
		option.series().add(new Line(name){
			@Override
			public String stack() {
				return stack;
			}
			@Override
			public ItemStyle getLabel() {
				Normal normal = new Normal();
				normal.show(true);
				normal.position(position);
				normal.textStyle().fontSize(15);//默认：10
				normal.textStyle().fontWeight(FontWeight.bolder);//加粗
				if (!StringUtils.isBlank(textColor)) {
					normal.textStyle().color(textColor);
				}
				if (formatter != null) {
					normal.formatter(formatter);
				}
				ItemStyle itemStyle = new ItemStyle();
				itemStyle.normal(normal);
				return itemStyle;
			}
			@Override
			public ItemStyle getAreaStyle() {
				Normal normal = new Normal();
				
				normal.color(areaStyleColor);
				
				ItemStyle itemStyle = new ItemStyle();
				itemStyle.normal(normal);
				return itemStyle;
			}
			@Override
			public List getData() {
				for(int i=0; i<barData.length; i++){
					if(barData[i] == null){
						barData[i] = dataEmplyValue;
					}
				}
				return Arrays.asList(barData);
			}
			
			@Override
			public Tooltip getTooltip() {
				if (tooltipFormatter == null) {
					return super.getTooltip();
				}
				Tooltip t = new Tooltip();
				t.formatter(tooltipFormatter);//换行：<br/>
				t.textStyle().fontStyle(FontStyle.normal);
				t.textStyle().align(X.left);
				t.textStyle().fontWeight(FontWeight.bolder);
				t.backgroundColor("red");//背景颜色
				return t;
			}
			@Override
			public ItemStyle getItemStyle() {
				if (rgbColor == null) {
					return super.getItemStyle();
				}
				Normal normal = new Normal();
				normal.color(rgbColor);//自定义柱子颜色
				ItemStyle itemStyle = new ItemStyle();
				itemStyle.normal(normal);
				
				return itemStyle;
			}
			@Override
			public Boolean smooth() {
				return smooth;
			}
		});
		if (smooth) {//曲线图
			
			Line line = (Line) option.series().get(0);
			line.smooth(smooth);
		}
		return option;
	}
	
	/**
	 *<pre>
	 * 说       明: X轴缩放功能
	 * @param option
	 * @param beginDate TODO
	 * @param endDate TODO
	 * @param scale TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-14下午11:04:13
	 *</pre>
	 * @throws ParseException 
	 */
	public static Option getDataZoom(Option option, String beginDate, String endDate, Integer scale) throws Exception{
		DataZoom dataZoom = new DataZoom();
		
		if(!StringUtils.isBlankOr(beginDate,endDate)){
			Long allDayCount = DateUtils.getSubtractDays(beginDate
					+ DateUtils.PARAM_TIME_STAR, endDate + DateUtils.PARAM_TIME_STAR, 
					DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS);
			Long nowDayCount = DateUtils.getSubtractDays(beginDate
					+ DateUtils.PARAM_TIME_STAR, DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYY_MM_DD)
					+ DateUtils.PARAM_TIME_STAR, 
					DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS);
			int start = Double.valueOf(nowDayCount*1.0/allDayCount * 96).intValue();
			if(start > 60){//比例
				
				dataZoom.start(60);//从第1个开始
			}else{
				dataZoom.start(start);//从第1个开始
			}
		}else{
			dataZoom.start(0);
		}
		dataZoom.end(dataZoom.getStart() + 40);//默认显示
		dataZoom.type(DataZoomType.inside);//内容到数据里面
		
		DataZoom dataZoom2 = new DataZoom();
		dataZoom2.show(true);//显示滑动条
		dataZoom2.height(20);//高度
		dataZoom2.type(DataZoomType.slider);//外置
		dataZoom2.xAxisIndex(0);
		dataZoom2.top("96%");
		
		option.dataZoom().add(dataZoom);
		option.dataZoom().add(dataZoom2);
		return option;
	}
	/**
	 *<pre>
	 * 说       明: 布局
	 * @param option
	 * @param left TODO
	 * @param right TODO
	 * @param bottom TODO
	 * @param top TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-8下午12:30:48
	 *</pre>
	 */
	public static Option getGrid(Option option, String left, String right, String bottom, String top){
		option.grid().setLeft(left == null ? "3%" : left);
		option.grid().setRight(right == null ? "12%" : right);
		option.grid().bottom(bottom == null ? "7%" : bottom);
		option.grid().top(top == null ? "10" : top);
		option.grid().containLabel(true);
		return option;
	}
	
	/***
	 *<pre>
	 * 说       明: 获取MAP中所有数据数量
	 * @param seriesMap
	 * @param key TODO
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-6-5下午9:10:05
	 *</pre>
	 */
	public static Integer getMapDataSize(LinkedHashMap<String, List<LegendEntity>> seriesMap, String key)throws Exception{
		Integer sum = 0;
		String tempKey = "";
		for (Iterator<String> iterator = seriesMap.keySet().iterator(); iterator.hasNext();) {
			tempKey = iterator.next();
			if (seriesMap.get(tempKey).size() == 0) {//&& !tempKey.equals(key)
				
				sum += 1;//需要加1
			}else{
				sum += seriesMap.get(tempKey).size();
			}
			if (tempKey.equals(key)) {
				break;
			}
		}
		return sum - 1;
	}
	
	/**
	 *<pre>
	 * 说       明: 创建饼图
	 * @param option
	 * @param pieSeriesMap
	 * @param pieWidth TODO
	 * @param pieHeight TODO
	 * @param pieSize TODO
	 * @param position TODO
	 * @param formatter TODO
	 * @param settingColor TODO 饼图颜色
	 * @param textSize TODO 文字大小
	 * @param normalShow TODO
	 * @param pieName TODO
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-17下午10:53:11
	 *</pre>
	 */
	public static Option getPie(Option option, final Map<String, LegendEntity> pieSeriesMap, 
			final String pieWidth, final String pieHeight, 
			final String pieSize, Position position, String formatter, boolean settingColor, Object textSize, boolean normalShow, String pieName){
		Pie pie = new Pie();
		int sum = 0;
		String name = "";
		for(Iterator<String> ite = pieSeriesMap.keySet().iterator(); ite.hasNext();){
			
			name = ite.next();
			sum += pieSeriesMap.get(name).getValue();
		}
		List color = new ArrayList();
		boolean selected = false;
		for(Iterator<String> ite = pieSeriesMap.keySet().iterator(); ite.hasNext();){
			
			name = ite.next();
			if (pieSeriesMap.get(name).getValue().intValue() != 0) {
				pie.data(new PieData(name, pieSeriesMap.get(name).getValue(), selected));  
			}else{
				pie.data(new PieData(name, 0, selected));  
			}
			if (settingColor) {
				option.color().addAll(Arrays.asList(pieSeriesMap.get(name).getRgbColor()));//设置饼图颜色
			}
			selected = false;
		}
		
		Normal normal = new Normal();
		normal.show(normalShow);
		normal.position(position);
		if (!StringUtils.isBlank(formatter)) {
			normal.formatter(formatter);
		}else{
			normal.formatter("");
		}
		if (!StringUtils.isBlank(textSize)) {
			normal.textStyle().fontSize((Integer)textSize);
		}
		normal.textStyle().fontWeight(FontWeight.bolder);
		
		ItemStyle itemStyle = new ItemStyle();
		itemStyle.normal(normal);
		Emphasis emphasis = new Emphasis();
		itemStyle.setEmphasis(emphasis);;
		
		pie.setLabel(itemStyle);
		if (!StringUtils.isBlank(pieWidth)
				&& !StringUtils.isBlank(pieHeight)) {
			
			pie.center(pieWidth, pieHeight);//指定显示位置：宽,高
		}else{
			//第一个参数[越大越右],第2个参数[越大越下]
			pie.center("75%","72%"); //1000,350 "75%","72%"
		}
		if (!StringUtils.isBlank(pieSize)) {
			
			if (pieSize.indexOf(",") != -1) {
				
				pie.radius(pieSize.split(","));//指定饼图大小
			}else{
				pie.radius(pieSize);//指定饼图大小
			}
		}else{
			pie.radius("28%");
		}
		if (!StringUtils.isBlank(formatter)) {
			pie.tooltip().formatter("{b} : {c}%");//图例
		}
	    pie.roseType(null);//饼图显示类型：默认，面积，半径
	    pie.setName(pieName);
	    
		option.series(pie);
		return option;
	}
	
	/**
	 * <pre>
	 * 说       明: 标题
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月28日下午1:25:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static Option getTitle(Option option, String text, String subtext, Integer top, Integer left, String textStyleColor, Integer fontSize) throws Exception{
		option.title().setText(text);
		option.title().setSubtext(subtext);
		option.title().setTop(top);
		option.title().setLeft(left);
		if (!StringUtils.isBlank(textStyleColor)) {
			
			TextStyle textTyle = new TextStyle();
			textTyle.setColor(textStyleColor);
			textTyle.setFontSize(fontSize);
			option.title().setTextStyle(textTyle);
		}
		return option;
	}
}
