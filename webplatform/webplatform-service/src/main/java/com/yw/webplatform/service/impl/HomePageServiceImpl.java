package com.yw.webplatform.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.add.LegendEnum;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.Y;
import com.yw.common.beansUtils.dto.HomePageDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.entity.ReportDataEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.ReportUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppIOTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppStatusEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import com.yw.common.beansUtils.utils.enums.PlatformDataTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IAppService;
import com.yw.webplatform.api.IHomePageService;



/**
 *<pre>
 * 功       能: 流程
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2016-05-01 15:38:33
 * Q    Q: 308053847
 *</pre>
 */
@Service("homePageService")
public class HomePageServiceImpl extends BaseMapperImpl implements IHomePageService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	@Autowired
	public IAppService appService;//应用
	
	/**
	 * <pre>
	 * 说       明: 首页应用报表
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日上午10:40:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil appReport(HomePageEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();
		
		Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		
		AppStatusEnum[] enumArray = new AppStatusEnum[]{
				AppStatusEnum.APP_NORMAL,
				AppStatusEnum.APP_ERROR};
		
		for (AppStatusEnum legendEnum : enumArray) {
			
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
		
		List<AppLicenseEntity> pvfeList = this.appLicenseEntityMapper.findAll(null);		
		
		for (Object str : legendArray) {
			seriesMap.put((String) str, new Object[pvfeList.size()]);
		}
		LegendEntity lgendEntity = null;
		String typeName = "";
		for (AppLicenseEntity data : pvfeList) {
			
			typeName = AppStatusEnum.getTypeName(data.getAppInstanceStatus());
			if (pieSeriesMap.containsKey(typeName)) {
				
				lgendEntity = pieSeriesMap.get(typeName);
				lgendEntity.setValue(lgendEntity.getValue() + Double.valueOf(1));
				pieSeriesMap.put(typeName,lgendEntity);
			}else{
				
				lgendEntity = pieSeriesMap.get(data.getAppInstanceStatus());
				lgendEntity.setValue(Double.valueOf(1));
				pieSeriesMap.put(typeName,lgendEntity);
			}
			
		}
		
		//基础定义
		option = ReportUtil.getTitle(option, "应用信息", null, 20, 20, null, null);
		
		//提示框
		option = ReportUtil.getTooltip(option, null, Trigger.item, null);
		
		//图例
		StringBuffer sb = new StringBuffer();
		sb.append("function (name) {");
			sb.append("var tmp = '',val = '';");
			sb.append("for(var i = 0;i<option.series[0].data.length;i++){");
				sb.append("tmp = option.series[0].data[i];");
			sb.append("if(tmp.name == name){");
				sb.append("val = tmp.value; ");
			sb.append("}");
					sb.append("}");
			sb.append(" return name+'  '+val;");
		sb.append("}");
		option = ReportUtil.getLegend(option, true,Orient.vertical,null,null, "80", "140", null, sb.toString(), legendArray);
		
		//饼图
		ReportUtil.getPie(option, pieSeriesMap, "35%", "60%", "50%", Position.inside, null, true, 15, true, "应用信息");
		
		//布局
		option = ReportUtil.getGrid(option, null, null, null, null);
		HomePageEntity re = new HomePageEntity();
		re.setBindingCount(this.appDeviceEntityMapper.findAll(null).size());//绑定数量
		re.setWarningCount(this.warningEntityMapper.findAll(new WarningEntity(null, 0)).size());//报警数量
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 首页设备
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午12:55:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil deviceReport(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		re.setPreceptionCount(this.deviceEntityMapper.findAll(new DeviceEntity(null, null, DeviceEqTypeEnum.VIEW.getCode())).size());
		re.setGetwayCount(this.deviceEntityMapper.findAll(new DeviceEntity(null, null, DeviceEqTypeEnum.GATEWAY.getCode())).size());
		
		{//正常/异常
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
			legendMap.put(LegendEnum.EQ_NORMAL.getName(), new LegendEntity(
					LegendEnum.EQ_NORMAL.getName(), 
					Position.inside,
					"", null, LegendEnum.EQ_NORMAL.getRgbColor(), null));
			legendMap.put(LegendEnum.EQ_ERROR.getName(), new LegendEntity(
					LegendEnum.EQ_ERROR.getName(), 
					Position.inside,
					"", null, LegendEnum.EQ_ERROR.getRgbColor(), null));
			
			
			pieSeriesMap.put(LegendEnum.EQ_NORMAL.getName(), new LegendEntity(
					LegendEnum.EQ_NORMAL.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.EQ_NORMAL.getRgbColor(), 0D));
			pieSeriesMap.put(LegendEnum.EQ_ERROR.getName(), new LegendEntity(
					LegendEnum.EQ_ERROR.getName(), 
					Position.outside,
					"{b}", null, LegendEnum.EQ_ERROR.getRgbColor(), 0D));
			
			
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			
			List<ReportDataEntity> pvfeList = this.reportDataEntityMapper.findDeviceStatus(null);		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			LegendEntity lgendEntity = null;
			for (ReportDataEntity data : pvfeList) {
				
				
				lgendEntity = pieSeriesMap.get(LegendEnum.EQ_NORMAL.getName());
				lgendEntity.setValue(Double.valueOf(data.getFirstData()));//正常
				pieSeriesMap.put(LegendEnum.EQ_NORMAL.getName(),lgendEntity);
				
				lgendEntity = pieSeriesMap.get(LegendEnum.EQ_ERROR.getName());
				lgendEntity.setValue(Double.valueOf(data.getSecondData()));
				pieSeriesMap.put(LegendEnum.EQ_ERROR.getName(),lgendEntity);
				
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "设备信息", null, 20, 20, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, "{a} <br/>{b} : {c} ({d}%)", Trigger.item, null);
			
			//图例
			StringBuffer sb = new StringBuffer();
			sb.append("function (name) {");
				sb.append("var tmp = '',val = '';");
				sb.append("for(var i = 0;i<option.series[0].data.length;i++){");
					sb.append("tmp = option.series[0].data[i];");
				sb.append("if(tmp.name == name){");
					sb.append("val = tmp.value; ");
				sb.append("}");
						sb.append("}");
				sb.append(" return name+'  '+val;");
			sb.append("}");
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,null, "325", "160", null, sb.toString(), legendArray);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "20%", "60%", "38%", Position.inside, null, true, 15, true, "设备信息");
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		{//在线/离线
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
			option = ReportUtil.getTitle(option, "", "", null, null, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			StringBuffer sb = new StringBuffer();
			sb.append("function (name) {");
				sb.append("var tmp = '',val = '';");
				sb.append("for(var i = 0;i<option.series[0].data.length;i++){");
					sb.append("tmp = option.series[0].data[i];");
				sb.append("if(tmp.name == name){");
					sb.append("val = tmp.value; ");
				sb.append("}");
						sb.append("}");
				sb.append(" return name+'  '+val;");
			sb.append("}");
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,null, "40", "140", null, sb.toString(), legendArray);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "20%", "60%", "38%", Position.inside, null, true, 15, true, "设备信息");
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 首页平台负载报表
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-7下午9:26:50
	 *</pre>
	 */
	@Override
	public ResultUtil platformReport(PlatformDataEntity entity) throws Exception{

		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();
		
		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		PlatformDataTypeEnum[] enumArray = new PlatformDataTypeEnum[]{
				PlatformDataTypeEnum.DATA};
		
		List<PlatformDataEntity> dataList = this.platformDataEntityMapper.findPatformReport(entity);
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
			log.info(xvalue[i]);
			
			Object[] inputObj = seriesMap.get(PlatformDataTypeEnum.DATA.getName());
			
			inputObj[i] = data.getPdata();
			
			seriesMap.put(PlatformDataTypeEnum.DATA.getName(), inputObj);
			i++;
			
			if (!(max > data.getPdata())) {//取Y轴最大值
				
				max = data.getPdata();
			}
		}
		
		//基础定义
		option = ReportUtil.getTitle(option, "系统状态", "平台负载", 10, 20, null, null);
		
		option = ReportUtil.getLegend(option, true,null,null,Y.top, "80", "50", null, null, legendArray);
		
		//提示框
		option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
		
		//X轴
		option = ReportUtil.getXAxisCategory(option, "时间", false, false, "#b1b9ce", xvalue);
		
		//Y轴
		option = ReportUtil.getYAxis(AxisType.value, option, "兆/小时", null, max, false, true, "#b1b9ce", null);
		//系列数据
		String name = null;
		int index = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
	        sb.append("offset: 0,");
	        sb.append("color: 'rgb(74,178,215)'");
	    sb.append("}, {");
	        sb.append("offset: 1,");
	        sb.append("color: 'rgb(255, 255, 255)'");
	    sb.append("}])");
		for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
			
			name = ite.next();
			option = ReportUtil.getSeriesLine(option, name, 
					null, 
					legendMap.get(name).getPositon(), 
					legendMap.get(name).getFormatter(), 
					legendMap.get(name).getTooltipFormatter(),
					legendMap.get(name).getRgbColor(), "''", null, sb.toString(), false, seriesMap.get(name));
		}
		
		//布局
		option = ReportUtil.getGrid(option, "3%", "4%", "3%", "110");
		HomePageEntity re = new HomePageEntity();
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	
	}
	
	/**
	 * <pre>
	 * 说       明: 首页平台IO/设备IO
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午6:32:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil ioReport(HomePageEntity entity) throws Exception{

		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();
		
		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		AppIOTypeEnum[] enumArray = new AppIOTypeEnum[]{
				AppIOTypeEnum.INPUT,
				AppIOTypeEnum.OUTPUT};
		List<AppEntity> dataList = this.appEntityMapper.findPlatformReport(null);
	    
		for (AppIOTypeEnum legendEnum : enumArray) {
			
			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
		        sb.append("offset: 0,");
		        sb.append("color: '"+legendEnum.getRgbColor()+"'");
		    sb.append("}, {");
		        sb.append("offset: 1,");
		        sb.append("color: 'rgb(255, 255, 255)'");
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
			
			Object[] inputObj = seriesMap.get(AppIOTypeEnum.INPUT.getName());
			Object[] outputObj = seriesMap.get(AppIOTypeEnum.OUTPUT.getName());
			
			inputObj[i] = data.getInputData();
			outputObj[i] = data.getOutputData();
			
			seriesMap.put(AppIOTypeEnum.INPUT.getName(), inputObj);
			seriesMap.put(AppIOTypeEnum.OUTPUT.getName(), outputObj);
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

		option = ReportUtil.getTitle(option, "平台对应用/设备数据输入输出流量", null, 20, 20,null, null);
		
		option = ReportUtil.getLegend(option, true,null,null,null, "80", "10", null, null, legendArray);
		
		//提示框
		option = ReportUtil.getTooltip(option, null, Trigger.axis, null);
		
		//X轴
		option = ReportUtil.getXAxisCategory(option, "时间", false, false, "#b1b9ce", xvalue);
		
		//Y轴
		option = ReportUtil.getYAxis(AxisType.value, option, "兆/小时", null, null, false, true, "#b1b9ce", null);
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
		option = ReportUtil.getGrid(option, "3%", "4%", "3%", "110");
		HomePageEntity re = new HomePageEntity();
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
}
