package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.ParallelAxis;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.HomePageDto;
import com.yw.common.beansUtils.dto.LicenseDto;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.ReportDataEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.ReportUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.AppIOTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppLicenseCountEnum;
import com.yw.common.beansUtils.utils.enums.DeviceLicenseCountEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.innerapi.api.IConfigapiService;
import com.yw.webplatform.api.ILicenseService;


/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
@Service("licenseService")
public class LicenseServiceImpl extends BaseMapperImpl  implements ILicenseService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IConfigapiService configapiService;//
	/**
	 *<pre>
	 * 说       明: 应用许可证报表
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-7下午9:26:50
	 *</pre>
	 */
	@Override
	public ResultUtil appLicenseReport(HomePageEntity entity) throws Exception{

		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();
		
		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		AppLicenseCountEnum[] enumArray = new AppLicenseCountEnum[]{
				AppLicenseCountEnum.USE_DAY_COUNT,
				AppLicenseCountEnum.RESIDUE_DAY_COUNT,
				AppLicenseCountEnum.OVER};
		List<ReportDataEntity> dataList = this.reportDataEntityMapper.findAppLicenseReport(null);
		for (AppLicenseCountEnum legendEnum : enumArray) {
			
			legendMap.put(legendEnum.getName(), new LegendEntity(
					legendEnum.getName(), 
					Position.insideRight,
					"", null, legendEnum.getRgbColor(), null));
			seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
			
		}
		Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
		Object[] legendArray = legendHeadArray;
			
		final String[] xvalue = new String[dataList.size()];
		int i = 0, max=0;
		for (ReportDataEntity data : dataList) {
			
			xvalue[i] = data.getDataName();
			
			Object[] inputObj = seriesMap.get(AppLicenseCountEnum.USE_DAY_COUNT.getName());
			Object[] outputObj = seriesMap.get(AppLicenseCountEnum.RESIDUE_DAY_COUNT.getName());
			Object[] overObj = seriesMap.get(AppLicenseCountEnum.OVER.getName());
			
			inputObj[i] = data.getSecondData() < 0 ? 0 : data.getSecondData();//已使用
			outputObj[i] = data.getFirstData() - data.getSecondData();//剩下
			
			if((Integer)outputObj[i] <= 0){
				overObj[i] = Math.abs((Integer)outputObj[i]);//已过期天数
				outputObj[i] = inputObj[i] = null;
			}
			
			seriesMap.put(AppLicenseCountEnum.USE_DAY_COUNT.getName(), inputObj);
			seriesMap.put(AppLicenseCountEnum.RESIDUE_DAY_COUNT.getName(), outputObj);
			seriesMap.put(AppLicenseCountEnum.OVER.getName(), overObj);
			i++;
			
			if (data.getFirstData() > max ) {//取Y轴最大值
				max = data.getFirstData();
			}
		}
		
		//基础定义
		option = ReportUtil.getTitle(option, "应用许可证", null, 10, 10, "#616161", null);
		
		option = ReportUtil.getLegend(option, true,null,null,null, "110", "10", null, null, legendArray);
		
		//提示框
		option = ReportUtil.getTooltip(option, null, Trigger.axis, PointerType.shadow);
		
		//X轴
		option = ReportUtil.getXAxisValue(option, "天数", 0, max, false, "#b1b9ce");
		
		//Y轴
		option = ReportUtil.getYAxisCategory(option, "实例名称", false, "#b1b9ce", xvalue);
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.interval(0);
		option.yAxis().get(0).axisLabel(axisLabel);
		
		//系列数据
		String name = null;
		int index = 0;
		for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
			
			name = ite.next();
			option = ReportUtil.getSeriesBar(option, name, 
					"总量", 
					legendMap.get(name).getPositon(), 
					legendMap.get(name).getFormatter(), 
					legendMap.get(name).getTooltipFormatter(),
					legendMap.get(name).getRgbColor(), "''", null, null, seriesMap.get(name));
		}
		
		//布局
		option = ReportUtil.getGrid(option, null, "10%", "5%", "20%");
		HomePageEntity re = new HomePageEntity();
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 型号下的设备使用数量/未使用数量
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-5-7下午9:26:50
	 *</pre>
	 */
	@Override
	public ResultUtil deviceLicenseReport(HomePageEntity entity) throws Exception{

		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();
		
		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		DeviceLicenseCountEnum[] enumArray = new DeviceLicenseCountEnum[]{
				DeviceLicenseCountEnum.USE_COUNT,
				DeviceLicenseCountEnum.UNUSE_COUNT};
		List<ReportDataEntity> dataList = this.reportDataEntityMapper.findDeviceLicenseReport(null);
		for (DeviceLicenseCountEnum legendEnum : enumArray) {
			
			legendMap.put(legendEnum.getName(), new LegendEntity(
					legendEnum.getName(), 
					Position.insideRight,
					"", null, legendEnum.getRgbColor(), null));
			seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);
			
		}
		Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
		Object[] legendArray = legendHeadArray;
			
		final String[] xvalue = new String[dataList.size()];
		int i = 0, max=0;
		for (ReportDataEntity data : dataList) {
			
			xvalue[i] = data.getDataName();
			
			Object[] inputObj = seriesMap.get(DeviceLicenseCountEnum.USE_COUNT.getName());
			Object[] outputObj = seriesMap.get(DeviceLicenseCountEnum.UNUSE_COUNT.getName());
			
			inputObj[i] = data.getSecondData();
			outputObj[i] = data.getFirstData() - data.getSecondData();//已使用
			
			seriesMap.put(AppIOTypeEnum.INPUT.getName(), inputObj);
			seriesMap.put(AppIOTypeEnum.OUTPUT.getName(), outputObj);
			i++;
			
			if (data.getFirstData() > max ) {//取Y轴最大值
				max = data.getFirstData();
			}
		}
		
		//基础定义
		option = ReportUtil.getTitle(option, "设备许可证", null, 10, 10, "#616161", null);
		
		option = ReportUtil.getLegend(option, true,null,null,null, "130", "10", null, null, legendArray);
		
		//提示框
		option = ReportUtil.getTooltip(option, null, Trigger.axis, PointerType.shadow);
		
		//X轴
		option = ReportUtil.getXAxisValue(option, "数量(个)", 0, max, false, "#b1b9ce");
		option.xAxis().get(0).interval(1);//单位刻度
		
		//Y轴
		option = ReportUtil.getYAxisCategory(option, "型号", false, "#b1b9ce", xvalue);
		//系列数据
		String name = null;
		int index = 0;
		for(Iterator<String> ite = legendMap.keySet().iterator(); ite.hasNext();){
			
			name = ite.next();
			option = ReportUtil.getSeriesBar(option, name, 
					"总量", 
					legendMap.get(name).getPositon(), 
					legendMap.get(name).getFormatter(), 
					legendMap.get(name).getTooltipFormatter(),
					legendMap.get(name).getRgbColor(), "''", null, 60, seriesMap.get(name));
		}
		
		//布局
		option = ReportUtil.getGrid(option, null, "10%", "4%", "20%");
		HomePageEntity re = new HomePageEntity();
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 应用许可证查询接口
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:59:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAppLicense(LicenseEntity entity, InterfacePage<LicenseEntity> page) throws Exception {
		ResultUtil<List<LicenseDto>> resultUtil = new ResultUtil<List<LicenseDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.licenseEntityMapper.getCountAppLicense(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.licenseEntityMapper.findAppLicense(entity));
		List<LicenseDto> listDto = new ArrayList<LicenseDto>();
		LicenseDto dto = null;
		for (LicenseEntity ce : page.getList()) {
			
			dto = new LicenseDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备许可证查询接口
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:59:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findDeviceLicense(LicenseEntity entity, InterfacePage<LicenseEntity> page) throws Exception {
		ResultUtil<List<LicenseDto>> resultUtil = new ResultUtil<List<LicenseDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.licenseEntityMapper.getCountDeviceLicense(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.licenseEntityMapper.findDeviceLicense(entity));
		List<LicenseDto> listDto = new ArrayList<LicenseDto>();
		LicenseDto dto = null;
		for (LicenseEntity ce : page.getList()) {
			
			dto = new LicenseDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(LicenseEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.licenseEntityMapper.insert(entity);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer delete(String tid) throws Exception {

		return this.licenseEntityMapper.delete(tid);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(LicenseEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.licenseEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	public ResultUtil<List<LicenseDto>> findPage(LicenseEntity entity, InterfacePage<LicenseEntity> page) throws Exception {
		ResultUtil<List<LicenseDto>> resultUtil = new ResultUtil<List<LicenseDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.licenseEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.licenseEntityMapper.findAll(entity));
		List<LicenseDto> listDto = new ArrayList<LicenseDto>();
		LicenseDto dto = null;
		for (LicenseEntity ce : page.getList()) {
			
			dto = new LicenseDto(ce);
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	public LicenseEntity findOne(LicenseEntity entity)
			throws Exception {
		
		List<LicenseEntity> list = this.licenseEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	public LicenseEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<LicenseEntity> list = this.licenseEntityMapper.findAll(
				new LicenseEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询已过期的许可证
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	public ResultUtil findExLicense() throws Exception {
		log.info("[LICENSE过期]" + "开始");
		ResultUtil resultUtil = new ResultUtil();
		List<ImportLicenseEntity> list = this.configapiService.findExLicense();
		if (StringUtils.isBlank(list)
				|| list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		for(ImportLicenseEntity license : list){
			
			List data = new ArrayList();
			Map<String, Object> map =  new HashMap<String, Object>();
			data.add(license.getId());
			map.put("id", data);
			PushDataEntity pushModel = new PushDataEntity("license_remove", map);

			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
			log.info("[LICENSE过期]删除:" + license.getId() + ",当前系统时间：" + DateUtils.getSysStringTime()+",JSON:" + JavaBeanUtil.javaBeanToString(pushModel));
		}
		log.info("[LICENSE过期]" + "结束");
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Override
	public List<LicenseEntity> findAll(LicenseEntity entity)
			throws Exception {
		
		return this.licenseEntityMapper.findAll(entity);
	}
}
