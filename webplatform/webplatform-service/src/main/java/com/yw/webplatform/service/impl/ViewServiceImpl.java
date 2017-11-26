package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.add.LegendEnum;
import com.github.abel533.echarts.axis.Axis;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.AxisTick;
import com.github.abel533.echarts.axis.ParallelAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.Y;
import com.github.abel533.echarts.series.PictorialBar;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.github.pagehelper.PageHelper;
import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.dto.HomePageDto;
import com.yw.common.beansUtils.dto.PushMsgDto;
import com.yw.common.beansUtils.dto.ViewDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.entity.ReportDataEntity;
import com.yw.common.beansUtils.entity.ViewEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.ReportUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.DeviceEqStatusEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import com.yw.common.beansUtils.utils.enums.MqttDataTypeEnum;
import com.yw.common.beansUtils.utils.enums.PlatformDataTypeEnum;
import com.yw.common.beansUtils.utils.enums.SystemConfigEnum;
import com.yw.common.beansUtils.utils.enums.ViewAppIOTypeEnum;
import com.yw.common.beansUtils.utils.enums.ViewDeviceOnLineEnum;
import com.yw.common.beansUtils.utils.enums.ViewPlatformDataTypeEnum;
import com.yw.common.beansUtils.utils.enums.ViewSecondTypeGatewayEnum;
import com.yw.common.beansUtils.utils.enums.WarningWaStatusEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IViewService;


/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
@Service("viewService")
public class ViewServiceImpl extends BaseMapperImpl  implements IViewService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IConfigurationService configurationService;//
	/**
	 * <pre>
	 * 说       明: 应用异常状态、设备异常状态
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月8日下午5:03:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findWarningReport(HomePageEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appCount", this.appEntityMapper.findAll(null).size());//应用数量
		map.put("appWarningCount", this.viewEntityMapper.getAppWarningCount(null));//应用报警数量，不包含已处理
		map.put("deviceCount", this.deviceEntityMapper.findAll(null).size());//设备数量
		map.put("deviceWarningCount", this.viewEntityMapper.getDeviceWarningCount(null));//设备报警数量，不包含已处理
		
		List<WarningEntity> warningList = this.warningEntityMapper.findAll(
				new WarningEntity(null, WarningWaStatusEnum.UNREAD.getCode()));
		if (!StringUtils.isBlank(warningList)
				&& !warningList.isEmpty()) {
			
			map.put("warningMsg", warningList.get(0).getContent());//警告滚动信息:显示最新的一条未处理的异常
		}else{
			map.put("warningMsg", "");
		}
		return resultUtil.setData(map).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 应用信息、设备信息、设备信息、设备上线总数
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月15日上午10:09:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAppDeviceReport(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		
		{//应用正常/异常
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			
			ViewSecondTypeGatewayEnum[] enumArray = new ViewSecondTypeGatewayEnum[]{
					ViewSecondTypeGatewayEnum.GATEWAY_READ,
					ViewSecondTypeGatewayEnum.GATEWAY_AP};
			
			for (ViewSecondTypeGatewayEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"{a} : {c}(T)", null, legendEnum.getRgbColor(), null));
				pieSeriesMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.outside,
						"{b}", null, legendEnum.getRgbColor(), 0D));
			}
			
			
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			
			List<AppAccountEntity> pvfeList = this.appAccountEntityMapper.findAll(null);		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			LegendEntity lgendEntity = null;
			String typeName = "";
			for (AppAccountEntity data : pvfeList) {
				
				typeName = ViewSecondTypeGatewayEnum.getTypeName(data.getAccountstatus());
				if (pieSeriesMap.containsKey(typeName)) {
					
					lgendEntity = pieSeriesMap.get(typeName);
					lgendEntity.setValue(lgendEntity.getValue() + Double.valueOf(1));
					pieSeriesMap.put(typeName,lgendEntity);
				}else{
					
					lgendEntity = pieSeriesMap.get(data.getAccountstatus());
					lgendEntity.setValue(Double.valueOf(1));
					pieSeriesMap.put(typeName,lgendEntity);
				}
				
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "应用信息", "", 0, 0, "#50e3c2", 1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "5%", "40%", null, null, legendArray);
			option.legend().textStyle().color("#fff");
			option.legend().textStyle().fontSize(3);
			option.legend().textStyle().fontWeight("bolder");
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "25%", "50%", "50%", Position.inside, null, true, 1, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, "30%", "12%", "70%", "100");
			re.setOption(option);
		}
		{//设备在线/离线
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			legendMap.put(LegendEnum.EQ_ONLINE.getName(), new LegendEntity(
					LegendEnum.EQ_ONLINE.getName(), 
					Position.inside,
					"", null, LegendEnum.EQ_ONLINE.getRgbColor(), null));
			legendMap.put(LegendEnum.EQ_OFLINE.getName(), new LegendEntity(
					LegendEnum.EQ_OFLINE.getName(), 
					Position.inside,
					"", null, LegendEnum.EQ_OFLINE.getRgbColor(), null));
			
			
			pieSeriesMap.put(LegendEnum.EQ_ONLINE.getName(), new LegendEntity(
					LegendEnum.EQ_ONLINE.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.EQ_ONLINE.getRgbColor(), 0D));
			pieSeriesMap.put(LegendEnum.EQ_OFLINE.getName(), new LegendEntity(
					LegendEnum.EQ_OFLINE.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.EQ_OFLINE.getRgbColor(), 0D));
			
			
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			
			List<ReportDataEntity> pvfeList = this.reportDataEntityMapper.findDeviceOnlineStatus(null);		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			List<String> legendList = new ArrayList<String>();
			LegendEntity lgendEntity = null;
			int index =0;
			for (ReportDataEntity data : pvfeList) {
				
				lgendEntity = pieSeriesMap.get(LegendEnum.EQ_ONLINE.getName());
				lgendEntity.setValue(Double.valueOf(data.getFirstData()));//在线
				pieSeriesMap.put(LegendEnum.EQ_ONLINE.getName(),lgendEntity);
				
				lgendEntity = pieSeriesMap.get(LegendEnum.EQ_OFLINE.getName());
				lgendEntity.setValue(Double.valueOf(data.getSecondData()));//离线
				pieSeriesMap.put(LegendEnum.EQ_OFLINE.getName(),lgendEntity);
				
				index++;
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "设备信息", null, 0, null, "#00ffc6", 1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "5%", "75", null, null, legendArray);
			option.legend().textStyle().color("#fff");
			option.legend().textStyle().fontSize(1);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "25%", "50%", "50%", Position.inside, null, true, 6, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		Integer normalErrorCount = 0;
		{//正常/异常
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			legendMap.put(LegendEnum.VIEW_EQ_NORMAL.getName(), new LegendEntity(
					LegendEnum.VIEW_EQ_NORMAL.getName(), 
					Position.inside,
					"", null, LegendEnum.VIEW_EQ_NORMAL.getRgbColor(), null));
			legendMap.put(LegendEnum.EQ_ERROR.getName(), new LegendEntity(
					LegendEnum.VIEW_EQ_ERROR.getName(), 
					Position.inside,
					"", null, LegendEnum.VIEW_EQ_ERROR.getRgbColor(), null));
			
			
			pieSeriesMap.put(LegendEnum.VIEW_EQ_NORMAL.getName(), new LegendEntity(
					LegendEnum.VIEW_EQ_NORMAL.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.VIEW_EQ_NORMAL.getRgbColor(), 0D));
			pieSeriesMap.put(LegendEnum.VIEW_EQ_ERROR.getName(), new LegendEntity(
					LegendEnum.VIEW_EQ_ERROR.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.VIEW_EQ_ERROR.getRgbColor(), 0D));
			
			
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			
			List<ReportDataEntity> pvfeList = this.reportDataEntityMapper.findDeviceStatus(null);		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			LegendEntity lgendEntity = null;
			for (ReportDataEntity data : pvfeList) {
				
				normalErrorCount = data.getFirstData() + data.getSecondData();
				lgendEntity = pieSeriesMap.get(LegendEnum.VIEW_EQ_NORMAL.getName());
				lgendEntity.setValue(Double.valueOf(data.getFirstData()));//正常
				pieSeriesMap.put(LegendEnum.VIEW_EQ_NORMAL.getName(),lgendEntity);
				
				lgendEntity = pieSeriesMap.get(LegendEnum.VIEW_EQ_ERROR.getName());
				lgendEntity.setValue(Double.valueOf(data.getSecondData()));
				pieSeriesMap.put(LegendEnum.VIEW_EQ_ERROR.getName(),lgendEntity);
				
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "设备信息", null, null, null, null, null);
			option.title().textStyle().color("#00ffc6");
			option.title().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "5%", "75", null, null, legendArray);
			option.legend().textStyle().color("#fff");
			option.legend().textStyle().fontSize(1);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "25%", "50%", "50%", Position.inside, null, true, 15, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		{//设备上线总数
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			ViewDeviceOnLineEnum[] enumArray = new ViewDeviceOnLineEnum[]{
					ViewDeviceOnLineEnum.DATA};
			
			List<PlatformDataEntity> dataList = this.platformDataEntityMapper.findDeviceOnLineCount(null);
			for (ViewDeviceOnLineEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
				
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (PlatformDataEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(ViewDeviceOnLineEnum.DATA.getName());
				
				inputObj[i] = data.getPdata();
				
				seriesMap.put(ViewDeviceOnLineEnum.DATA.getName(), inputObj);
				i++;
				
				if (!(max > data.getPdata())) {//取Y轴最大值
					
					max = data.getPdata();
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "设备上线总数", "", 0, 0, null, null);
			option.title().textStyle().color("#00ffc6");
			option.title().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
			
			//X轴
			option = ReportUtil.getXAxisCategory(option, "", false, false, "#b1b9ce", xvalue);
			
			//Y轴
			option = ReportUtil.getYParallelAxis(AxisType.value, option, "", null, max, false, true, "#b1b9ce", "#3ccdff", null);
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameGap(10);

			//系列数据
			String name = null;
			int index = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
		        sb.append("offset: 0,");
		        sb.append("color: 'rgb(77, 158, 244)'");
		    sb.append("}, {");
		        sb.append("offset: 1,");
		        sb.append("color: 'rgb(23, 37, 100)'");
		    sb.append("}])");
			for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
				
				name = ite.next();
				option = ReportUtil.getSeriesLine(option, name, 
						null, 
						legendMap.get(name).getPositon(), 
						legendMap.get(name).getFormatter(), 
						legendMap.get(name).getTooltipFormatter(),
						legendMap.get(name).getRgbColor(), "''", null, sb.toString(), true, seriesMap.get(name));
			}
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "10%", "40");
			re.setOption(option);
		}
		re.setTotalCountArray(new Integer[]{
				this.appAccountEntityMapper.findAll(null).size(),
				this.deviceEntityMapper.findAll(null).size(),
				normalErrorCount});//应用总数、设备总数、设备总数
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月16日上午10:10:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findPlatformReport(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		
		{//平台负载
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			ViewPlatformDataTypeEnum[] enumArray = new ViewPlatformDataTypeEnum[]{
					ViewPlatformDataTypeEnum.DATA};
			
			List<PlatformDataEntity> dataList = this.platformDataEntityMapper.findPatformReport(null);
			for (ViewPlatformDataTypeEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
				
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (PlatformDataEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(ViewPlatformDataTypeEnum.DATA.getName());
				
				inputObj[i] = data.getPdata();
				
				seriesMap.put(ViewPlatformDataTypeEnum.DATA.getName(), inputObj);
				i++;
				
				if (!(max > data.getPdata())) {//取Y轴最大值
					
					max = data.getPdata();
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "平台负载", "", 0, 0, null, null);
			option.title().textStyle().color("#00ffc6");
			option.title().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
			
			//X轴
			option = ReportUtil.getXAxisCategory(option, "", false, false, "#b1b9ce", xvalue);
			
			//Y轴
			option = ReportUtil.getYParallelAxis(AxisType.value, option, "", null, max, false, true, "#b1b9ce", "#3ccdff", null);
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameGap(10);

			//系列数据
			String name = null;
			int index = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
		        sb.append("offset: 0,");
		        sb.append("color: 'rgb(84,163, 253)'");
		    sb.append("}, {");
		        sb.append("offset: 1,");
		        sb.append("color: 'rgb(197, 178, 255)'");
		    sb.append("}])");
			for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
				
				name = ite.next();
				option = ReportUtil.getSeriesLine(option, name, 
						null, 
						legendMap.get(name).getPositon(), 
						legendMap.get(name).getFormatter(), 
						legendMap.get(name).getTooltipFormatter(),
						legendMap.get(name).getRgbColor(), "''", null, sb.toString(), true, seriesMap.get(name));
			}
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "10%", "40");
			re.setOption(option);
		}
		{//内存/磁盘/CPU
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			PlatformDataTypeEnum[] enumArray = new PlatformDataTypeEnum[]{
					PlatformDataTypeEnum.DATA};
			
			List<PlatformDataEntity> dataList = this.platformDataEntityMapper.findPatformReport(null);
			for (PlatformDataTypeEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (PlatformDataEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(PlatformDataTypeEnum.DATA.getName());
				
				inputObj[i] = data.getPdata();
				
				seriesMap.put(PlatformDataTypeEnum.DATA.getName(), inputObj);
				i++;
				
				if (!(max > data.getPdata())) {//取Y轴最大值
					
					max = data.getPdata();
				}
			}
			
			//TITLE
			option = ReportUtil.getTitle(option, "内存/硬盘/CPU", null, 0, 0,"#00ffc6", 1);
			option.title().textStyle().fontWeight(100);
			
			//X轴
			Axis xAxis = new Axis<T>() {};
			xAxis.max(3000);
			xAxis.show(false);//隐藏X轴
			xAxis.splitLine(new SplitLine().show(false));
			xAxis.offset(1);
			xAxis.axisLabel(new AxisLabel().margin(10));
			
			option.xAxis().add(xAxis);
			
			//Y轴
			option.yAxis().add(new Axis(){
				@Override
				public List getData() {
					List data = new ArrayList();
					data.add("内存"); 
					data.add("磁盘"); 
					data.add("CPU"); 
					return data;
				}
				@Override
				public AxisLabel getAxisLabel() {
					AxisLabel axisLabel = new AxisLabel();
					
					axisLabel.margin(20);
					axisLabel.textStyle().color("#d3d9e9");
					axisLabel.textStyle().fontSize(1);
					return axisLabel;
				}
				
			});
			Axis yAxis = option.yAxis().get(0);
			yAxis.inverse(true);
			yAxis.axisTick(new AxisTick().show(false));
			yAxis.axisLine(new AxisLine().show(false));
			
			//系列数据
			final String spirit = "image://data:image/png;base64,";
			final ReportDataEntity reportData = this.reportDataEntityMapper.getPlatformAppDAta(null);
			option.series().add(new PictorialBar() {
				
				@Override
				public List getData() {
					List list = new ArrayList();
					if (!StringUtils.isBlank(reportData)
				    		&&!StringUtils.isBlank(reportData.getFirstData())) {
						list.add(reportData.getFirstData() * 20);
				        list.add(reportData.getSecondData() * 20);
				        list.add(reportData.getThirdData() * 20);
					}
					
					return list;
				}
			});
			PictorialBar poctorialBar = (PictorialBar)option.series().get(0);
		    poctorialBar.symbol("image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAA8CAYAAABmdppWAAAAAXNSR0IArs4c6QAAAXNJREFUWAntmD1OxDAQhWcctuMAkJaKnoMAl0B7gEUcASkHiLgE7EHoqWi9HIBulQxvHBIWK5KXiTs8Un78M1+c50mRxzQTIk8r+uiuSXocdEXMdZgm4onpldht6azaMt/t43SOO8S3N0RdA9BFPParzfROVG24Xr8c9k9AEXHk20eifnM4IX3vGqrXD8zc69yTKcEE02wswLd6c6+nsMLwmtI9a4c5uLrV1+ewAbv9W1Kz1JNU0/PVpRt2M7EBKZiO6yaiMlwojWMSjpmDMgMQdZYrwHJT0eaA4gPACuU0ByswwHLZYN+gAlyuaNGwaGhQoJSNQbQopWgYCWJoFg0NokUpRcNIEEOzaGgQLUopGkaCGJr/UkPmT4NU8ylg6Y+Pnx819IKFXzN4CLkCLABhSOQKsJy6G1glDImFoQyw3GCVVH80LuYeDocEtkso7MEqgbthDteMdsvPlwKrhMgCHWyWcS2TbzN2ZDOCRqBel1hVXz++dzalp980AAAAAElFTkSuQmCC");
		    poctorialBar.SymbolRepeat("fixed");
		    poctorialBar.SymbolMargin("5%");
		    poctorialBar.SymbolClip(true);
		    poctorialBar.SymbolSize(Integer.valueOf(30));
		    poctorialBar.SymbolBoundingData(Integer.valueOf(2000));
		    poctorialBar.Z(Integer.valueOf(10));

		    PictorialBar poctorialBarTwo = new PictorialBar();
		    poctorialBarTwo.itemStyle(new ItemStyle().setNormal((Normal)new Normal().opacity(Double.valueOf(0.9D))));
		    poctorialBarTwo.label().normal().show(Boolean.valueOf(true));
		    poctorialBarTwo.label().normal().formatter(" function (params) { return (params.value / 2000 * 100).toFixed(1) + ' %'; }");
		    poctorialBarTwo.label().normal().position(Position.outside);
		    poctorialBarTwo.label().normal().Offset(new Integer[] { Integer.valueOf(20), Integer.valueOf(0) });
		    poctorialBarTwo.label().normal().textStyle().color("#fff");
		    poctorialBarTwo.label().normal().textStyle().fontSize(Integer.valueOf(1));

		    poctorialBarTwo.animationDuration(Integer.valueOf(0));
		    poctorialBarTwo.SymbolRepeat("fixed");
		    poctorialBarTwo.SymbolMargin("5%");
		    poctorialBarTwo.symbol("image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAA8CAYAAABmdppWAAAAAXNSR0IArs4c6QAAAXZJREFUWAntmD9OwzAYxf0s2HoAyMoQNTMHKUhwkyKOgNSbMEAOwkpLBtaUA3RDyodfgqNiJbLyNRv20Prf+8V+doY8mIEiIufb6mtljKwgci0GGafBSC3Am6uVRX5RAvgO5Qg7tlV9I2I2RuQqHPvTBj4Bsy7y7PW4vwe6VdldVT852Pp4QqzuoJtlnj261Tace+YFGhi1XIDTsvrAn3aF7TYbeWGHtsDiltu3PIDWMy3pV0cGWXj/2N8ZaZ5P5HVy2HvLqzELrIXIyvKezQUky/pLOweULG55MQesY8jCAectCXi6n8nD5KHCgXRtFKYFkuRhYIiimTxUmBZIkoeBIYpm8lBhWiBJHgaGKJr/00McFFaNSHCw/OwfGZ3cTZbtMoTJ2kEBWe6UUQ6OqjpRug9IJiD7XTS0iD3AhRpFfrm0jEqYbsTmx8bJIKu92MwKmG7ERGPj1Pq4pX9TuqhkOpQwav3D+tzGd8wWBHkg/7uD0kVVP5KYjppLqxWfAAAAAElFTkSuQmCC");
		    poctorialBarTwo.SymbolSize(Integer.valueOf(30));
		    poctorialBarTwo.SymbolBoundingData(Integer.valueOf(2000));
		    if (!StringUtils.isBlank(reportData)
		    		&&!StringUtils.isBlank(reportData.getFirstData())) {
		    	poctorialBarTwo.data(new Integer[] { reportData.getFirstData() * 20, reportData.getSecondData() * 20, reportData.getThirdData() * 20 });
			}
			poctorialBarTwo.z(5);
			
			option.series().add(poctorialBarTwo);
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "10%", "40");
			
			re.setOption(option);
		}
		{//推送信息量报表
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			MqttDataTypeEnum[] enumArray = new MqttDataTypeEnum[]{
					MqttDataTypeEnum.DATA};
			
			List<PlatformDataEntity> dataList = this.findMqtt();
			for (MqttDataTypeEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
				
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (PlatformDataEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(MqttDataTypeEnum.DATA.getName());
				
				inputObj[i] = data.getPdata();
				
				seriesMap.put(MqttDataTypeEnum.DATA.getName(), inputObj);
				i++;
				
				if (!(max > data.getPdata())) {//取Y轴最大值
					
					max = data.getPdata();
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "推送信息量报表", "", 0, 0, null, null);
			option.title().textStyle().color("#00ffc6");
			option.title().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
			
			//X轴
			option = ReportUtil.getXAxisCategory(option, "", false, false, "#b1b9ce", xvalue);
			
			//Y轴
			option = ReportUtil.getYParallelAxis(AxisType.value, option, "", null, max, false, true, "#b1b9ce", "#3ccdff", null);
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameGap(10);

			//系列数据
			String name = null;
			int index = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
		        sb.append("offset: 0,");
		        sb.append("color: 'rgb(36, 57,116)'");
		    sb.append("}, {");
		        sb.append("offset: 1,");
		        sb.append("color: 'rgb(186, 224, 235)'");
		    sb.append("}])");
			for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
				
				name = ite.next();
				option = ReportUtil.getSeriesLine(option, name, 
						null, 
						legendMap.get(name).getPositon(), 
						legendMap.get(name).getFormatter(), 
						legendMap.get(name).getTooltipFormatter(),
						legendMap.get(name).getRgbColor(), "''", null, sb.toString(), true, seriesMap.get(name));
			}
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "10%", "40");
			re.setOption(option);
		}
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 平台对应用/设备输入输出流量、接口访问量报表
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日上午10:10:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findIOAndInterface(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		{
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			ViewAppIOTypeEnum[] enumArray = new ViewAppIOTypeEnum[]{
					ViewAppIOTypeEnum.INPUT,
					ViewAppIOTypeEnum.OUTPUT};
			List<AppEntity> dataList = this.appEntityMapper.findPlatformReport(null);
		    
			for (ViewAppIOTypeEnum legendEnum : enumArray) {
				
				StringBuffer sb = new StringBuffer();
				sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
			        sb.append("offset: 0,");
			        sb.append("color: 'rgb(87, 237,207)'");
			    sb.append("}, {");
			        sb.append("offset: 1,");
			        sb.append("color: '"+legendEnum.getRgbColor()+"'");
			    sb.append("}])");
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null, sb.toString()));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (AppEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(ViewAppIOTypeEnum.INPUT.getName());
				Object[] outputObj = seriesMap.get(ViewAppIOTypeEnum.OUTPUT.getName());
				
				inputObj[i] = data.getInputData();
				outputObj[i] = data.getOutputData();
				
				seriesMap.put(ViewAppIOTypeEnum.INPUT.getName(), inputObj);
				seriesMap.put(ViewAppIOTypeEnum.OUTPUT.getName(), outputObj);
				i++;
				
				if (!(max > data.getInputData() && max > data.getOutputData())) {//取Y轴最大值
					
					if (data.getInputData() > data.getOutputData()) {
						max = data.getInputData();
						if (data.getInputData() > max) {
							max = data.getInputData();
						}
					}else{
						max = data.getOutputData();
						if (data.getOutputData() > max) {
							max = data.getOutputData();
						}
					}
				}
			}
			
			//基础定义

			option = ReportUtil.getTitle(option, "平台对应用/设备数据输入输出流量", null, null, 0,"#00ffc6", 1);
			
			option = ReportUtil.getLegend(option, true,null,null,null, "0", "0", null, null, legendArray);
			option.legend().textStyle().color("#b1b9ce");
			option.legend().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
			
			//X轴
			option = ReportUtil.getXAxisCategory(option, "", false, false, "#b1b9ce", xvalue);
			
			//Y轴
			option = ReportUtil.getYParallelAxis(AxisType.value, option, "", null, null, false, true, "#b1b9ce", "#3ccdff",null);
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameGap(15);
			
			//系列数据
			String name = null;
			int index = 0;
			for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
				
				name = ite.next();
				option = ReportUtil.getSeriesLine(option, name, 
						null, 
						legendMap.get(name).getPositon(), 
						legendMap.get(name).getFormatter(), 
						legendMap.get(name).getTooltipFormatter(),
						legendMap.get(name).getRgbColor(), "''", null, legendMap.get(name).getAreaStleColor(), false, seriesMap.get(name));
			}
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "3%", "50");
			re.setOption(option);
		}
		{//接口访问量报表
			Option option = new Option();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			ViewPlatformDataTypeEnum[] enumArray = new ViewPlatformDataTypeEnum[]{
					ViewPlatformDataTypeEnum.DATA};
			
			List<PlatformDataEntity> dataList = this.platformDataEntityMapper.findInerfaceLogCount(null);
			for (ViewPlatformDataTypeEnum legendEnum : enumArray) {
				
				legendMap.put(legendEnum.getName(), new LegendEntity(
						legendEnum.getName(), 
						Position.inside,
						"", null, legendEnum.getRgbColor(), null));
				seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
				
			}
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
				
			final String[] xvalue = new String[dataList.size()];
			int i = 0, max=0;
			for (PlatformDataEntity data : dataList) {
				
				xvalue[i] = data.getDateHour();
				
				Object[] inputObj = seriesMap.get(ViewPlatformDataTypeEnum.DATA.getName());
				
				inputObj[i] = data.getPdata();
				
				seriesMap.put(ViewPlatformDataTypeEnum.DATA.getName(), inputObj);
				i++;
				
				if (!(max > data.getPdata())) {//取Y轴最大值
					
					max = data.getPdata();
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "接口访问量报表", "", 0, 0, null, null);
			option.title().textStyle().color("#00ffc6");
			option.title().textStyle().fontSize(1);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
			
			//X轴
			option = ReportUtil.getXAxisCategory(option, "", false, false, "#b1b9ce", xvalue);
			
			//Y轴
			option = ReportUtil.getYParallelAxis(AxisType.value, option, "", null, max, false, true, "#b1b9ce", "#3ccdff", null);
			ParallelAxis axis = (ParallelAxis) option.yAxis().get(0);
			axis.nameGap(10);

			//系列数据
			String name = null;
			int index = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
		        sb.append("offset: 0,");
		        sb.append("color: 'rgb(77, 158, 244)'");
		    sb.append("}, {");
		        sb.append("offset: 1,");
		        sb.append("color: 'rgb(23, 37, 100)'");
		    sb.append("}])");
			for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
				
				name = ite.next();
				option = ReportUtil.getSeriesLine(option, name, 
						null, 
						legendMap.get(name).getPositon(), 
						legendMap.get(name).getFormatter(), 
						legendMap.get(name).getTooltipFormatter(),
						legendMap.get(name).getRgbColor(), "''", null, sb.toString(), true, seriesMap.get(name));
			}
			
			//布局
			option = ReportUtil.getGrid(option, "3%", "4%", "10%", "50");
			re.setOption(option);
		}
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 查询MQTT消息
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日上午10:17:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<PlatformDataEntity> findMqtt() throws Exception {
		
		
		String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_MQTT_PORT_8060_TCP_ADDR.toString());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYY_MM_DD + DateUtils.PARAM_TIME_STAR));//只显示当天
		param.put("pageSize", 1000000000);//查询所有
		String jsonText = UrlUtil.postNoSecret(host, param, false);
		if (StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
				|| jsonText.indexOf("MongoError") != -1) {
			return null;
		}
		JSONObject jsonObject = JSONObject.parseObject(jsonText);
		List<Object> list = JavaBeanUtil.jsonToJavaBean(jsonObject.getString("data").toString(), List.class);
		List<PushMsgDto> listDto = new ArrayList<PushMsgDto>();
		JSONObject jsonObj = null;
		PushMsgDto dto = null;
		for (Object obj : list) {
			
			dto = new PushMsgDto();
			jsonObj = JSONObject.parseObject(obj.toString());
			dto.setCreateTime(DateUtils.formatUTCStr(jsonObj.getString("time")+"", "HH:00"));
			listDto.add(dto);	//封装成DTO数据
		}
		Map<String, PlatformDataEntity> map = new HashMap<String, PlatformDataEntity>();
		for (PushMsgDto pushMsg : listDto) {
			
			if (map.containsKey(pushMsg.getCreateTime())) {
				Integer count = map.get(pushMsg.getCreateTime()).getPdata() + 1;
				map.put(pushMsg.getCreateTime(), new PlatformDataEntity(count, pushMsg.getCreateTime()));
			}else{
				map.put(pushMsg.getCreateTime(), new PlatformDataEntity(1, pushMsg.getCreateTime()));
			}
		}
		return new ArrayList<PlatformDataEntity>(map.values());
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(ViewEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.viewEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(ViewEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.viewEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(ViewEntity entity, InterfacePage<ViewEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.viewEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.viewEntityMapper.findAll(entity));
		List<ViewDto> listDto = new ArrayList<ViewDto>();
		ViewDto dto = null;
		for (ViewEntity ce : page.getList()) {
			
			dto = new ViewDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Override
	public ViewEntity findOne(ViewEntity entity)
			throws Exception {
		
		List<ViewEntity> list = this.viewEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Override
	public ViewEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ViewEntity> list = this.viewEntityMapper.findAll(
				new ViewEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
