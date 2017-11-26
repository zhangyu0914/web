package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.PointerType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppDto;
import com.yw.common.beansUtils.dto.DeviceDto;
import com.yw.common.beansUtils.dto.HomePageDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ReportUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppIOTypeEnum;
import com.yw.common.beansUtils.utils.enums.IoDataDataCodeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppAccountService;
import com.yw.webplatform.api.IAppLicenseService;
import com.yw.webplatform.api.IAppService;

/**
 * <pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 * </pre>
 */
@Service("appService")
public class AppServiceImpl extends BaseMapperImpl implements IAppService {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	@Autowired
	private IAppLicenseService appLicenseService;//
	@Autowired
	private IAppAccountService appAccountService;//

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:11:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil<List<AppDto>> appReport(AppLicenseEntity entity,
			InterfacePage<AppLicenseEntity> page) throws Exception {
		ResultUtil<List<AppDto>> resultUtil = new ResultUtil<List<AppDto>>();
		page.setList(this.appLicenseEntityMapper.findAppReport(entity));
		List<AppDto> listDto = new ArrayList<AppDto>();
		AppDto dto = null;
		List<AppDeviceEntity> deviceList = null;
		for (AppLicenseEntity ce : page.getList()) {

			deviceList = this.appDeviceEntityMapper
					.finDevice(new AppDeviceEntity(null, ce
							.getFkAppAccountTid()));
			if (!StringUtils.isBlank(deviceList) && !deviceList.isEmpty()) {
				ce.setBindingCount(deviceList.size());
			} else {
				ce.setBindingCount(0);
			}

			dto = new AppDto(ce);
			listDto.add(dto); // 封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page)
				.setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: APP功能I0统计报表接口多个应用统计
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:22:30
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil appIOReport(AppEntity entity) throws Exception {

		ResultUtil resultUtil = new ResultUtil();
		Option option = new Option();

		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		AppIOTypeEnum[] enumArray = new AppIOTypeEnum[] { AppIOTypeEnum.INPUT,
				AppIOTypeEnum.OUTPUT };
		List<AppLicenseEntity> dataList = this.appLicenseEntityMapper
				.findAll(null);
		for (AppIOTypeEnum legendEnum : enumArray) {

			legendMap.put(legendEnum.getName(),
					new LegendEntity(legendEnum.getName(), Position.inside, "",
							null, legendEnum.getRgbColor(), null));
			seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);

		}
		Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
		Object[] legendArray = legendHeadArray;

		final String[] xvalue = new String[dataList.size()];
		int i = 0, max = 0;
		Map<String, String> map = new HashMap<String, String>();
		for (AppLicenseEntity data : dataList) {

			xvalue[i] = data.getAppReportName();
			map.put(data.getAppReportName(), data.getTid());

			Object[] inputObj = seriesMap.get(AppIOTypeEnum.INPUT.getName());
			Object[] outputObj = seriesMap.get(AppIOTypeEnum.OUTPUT.getName());
			data.setDataCode(IoDataDataCodeEnum.APP.toString());
			data = this.appLicenseEntityMapper.findAppIO(data);
			if (StringUtils.isBlank(data)) {

				inputObj[i] = 0;
				outputObj[i] = 0;
			} else {

				inputObj[i] = data.getInputData();
				outputObj[i] = data.getOutputData();
				if (!(max > data.getInputData() && max > data.getOutputData())) {// 取Y轴最大值

					if (data.getInputData() > data.getOutputData()) {
						max = data.getInputData();
						if (data.getInputData() > max) {
							max = data.getInputData();
						}
					} else {
						max = data.getOutputData();
						if (data.getOutputData() > max) {
							max = data.getOutputData();
						}
					}
				}
			}
			seriesMap.put(AppIOTypeEnum.INPUT.getName(), inputObj);
			seriesMap.put(AppIOTypeEnum.OUTPUT.getName(), outputObj);
			i++;
		}

		// 基础定义
		option = ReportUtil.getTitle(option, "I/O统计", null, 10, 10, null, null);

		option = ReportUtil.getLegend(option, true, null, null, null, "80",
				"10", null, null, legendArray);

		// 提示框
		option = ReportUtil.getTooltip(option, "", Trigger.axis,
				PointerType.shadow);

		// X轴
		option = ReportUtil.getXAxisCategory(option, "应用名称", false, false,
				"#b1b9ce", xvalue);

		{// X轴字体斜着
			AxisLabel axisLabel = new AxisLabel();
			axisLabel.interval(0);
			axisLabel.rotate(45);
			axisLabel.margin(2);
			axisLabel.textStyle().fontSize(2);
			axisLabel.textStyle().color("#b1b9ce");
			option.xAxis().get(0).axisLabel(axisLabel);
		}

		// Y轴
		option = ReportUtil.getYAxis(AxisType.value, option, "兆", null, max,
				false, false, "#b1b9ce", null);
		// 系列数据
		String name = null;
		int index = 0;
		for (Iterator<String> ite = legendMap.keySet().iterator(); ite
				.hasNext();) {

			name = ite.next();
			option = ReportUtil
					.getSeriesBar(option, name, null, legendMap.get(name)
							.getPositon(), legendMap.get(name).getFormatter(),
							legendMap.get(name).getTooltipFormatter(),
							legendMap.get(name).getRgbColor(), "''", null, 20,
							seriesMap.get(name));
		}

		// 布局
		option = ReportUtil.getGrid(option, null, "18%", "12%", "15%");
		// option.grid().x2(100);
		// option.grid().y2(150);
		HomePageEntity re = new HomePageEntity();
		re.setBindingCount(this.appDeviceEntityMapper.findAll(null).size());
		re.setWarningCount(this.warningEntityMapper.findAll(
				new WarningEntity(null, 1)).size());
		re.setOption(option);
		re.setMap(map);
		return resultUtil.setData(new HomePageDto(re)).setCode(
				ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: I0统计报表接口单个应用分时间统计
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:44:16
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil appDetailReport(AppLicenseEntity entity) throws Exception {

		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		Option option = new Option();

		Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();
		Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
		AppIOTypeEnum[] enumArray = new AppIOTypeEnum[] { AppIOTypeEnum.INPUT,
				AppIOTypeEnum.OUTPUT };
		AppLicenseEntity appLicense = this.appLicenseService.findById(entity
				.getTid());
		if (StringUtils.isBlank(appLicense)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		AppAccountEntity appAccount = this.appAccountService
				.findOne(new AppAccountEntity(appLicense.getTid(), null));
		if (StringUtils.isBlank(appAccount)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		appLicense.setDataCode(IoDataDataCodeEnum.APP.toString());
		appLicense.setAppInstance(appAccount.getAppInstance());
		List<AppLicenseEntity> dataList = this.appLicenseEntityMapper
				.findAppDetailReport(appLicense);
		for (AppIOTypeEnum legendEnum : enumArray) {

			StringBuffer sb = new StringBuffer();
			sb.append("new echarts.graphic.LinearGradient(0, 0, 0, 1, [{");
			sb.append("offset: 0,");
			sb.append("color: '" + legendEnum.getRgbColor() + "'");
			sb.append("}, {");
			sb.append("offset: 1,");
			sb.append("color: 'rgb(255, 255, 255)'");
			sb.append("}])");
			legendMap
					.put(legendEnum.getName(),
							new LegendEntity(legendEnum.getName(),
									Position.inside, "", null, legendEnum
											.getRgbColor(), null, sb.toString()));
			seriesMap.put(legendEnum.getName(), new Object[dataList.size()]);

		}
		Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
		Object[] legendArray = legendHeadArray;

		final String[] xvalue = new String[dataList.size()];
		int i = 0, max = 0;
		for (AppLicenseEntity data : dataList) {

			xvalue[i] = data.getDateHour();

			Object[] inputObj = seriesMap.get(AppIOTypeEnum.INPUT.getName());
			Object[] outputObj = seriesMap.get(AppIOTypeEnum.OUTPUT.getName());

			inputObj[i] = data.getInputData();
			outputObj[i] = data.getOutputData();

			seriesMap.put(AppIOTypeEnum.INPUT.getName(), inputObj);
			seriesMap.put(AppIOTypeEnum.OUTPUT.getName(), outputObj);
			i++;

			if (!(max > data.getInputData() && max > data.getOutputData())) {// 取Y轴最大值

				if (data.getInputData() > data.getOutputData()) {
					max = data.getInputData();
					if (data.getInputData() > max) {
						max = data.getInputData();
					}
				} else {
					max = data.getOutputData();
					if (data.getOutputData() > max) {
						max = data.getOutputData();
					}
				}
			}
		}

		// 基础定义
		option = ReportUtil.getTitle(option, "I/O统计", null, 5, 5, null, null);

		option = ReportUtil.getLegend(option, true, Orient.vertical, null,
				null, "180", "40", null, null, legendArray);

		// 提示框
		option = ReportUtil.getTooltip(option, null, Trigger.axis, null);

		// X轴
		option = ReportUtil.getXAxisCategory(option, "时间", false, false,
				"#b1b9ce", xvalue);

		// Y轴
		option = ReportUtil.getYAxis(AxisType.value, option, "兆/秒", null, max,
				false, false, "#b1b9ce", null);
		// 系列数据
		String name = null;
		int index = 0;
		for (Iterator<String> ite = legendMap.keySet().iterator(); ite
				.hasNext();) {

			name = ite.next();
			option = ReportUtil.getSeriesLine(option, name, null, legendMap
					.get(name).getPositon(),
					legendMap.get(name).getFormatter(), legendMap.get(name)
							.getTooltipFormatter(), legendMap.get(name)
							.getRgbColor(), "''", null, legendMap.get(name)
							.getAreaStleColor(), false, seriesMap.get(name));
		}

		// 布局
		option = ReportUtil.getGrid(option, null, null, null, "18%");
		HomePageEntity re = new HomePageEntity();
		re.setOption(option);
		return resultUtil.setData(new HomePageDto(re)).setCode(
				ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: APP拥有设备的权限
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:51:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil<List> findAppFunction(String fkAppListenceTid,
			InterfacePage<AppEntity> page) throws Exception {
		ResultUtil<List<AppDto>> resultUtil = new ResultUtil<List<AppDto>>();
		if (StringUtils.isBlank(fkAppListenceTid)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.appEntityMapper
				.getCountAppFunction(fkAppListenceTid));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appEntityMapper.findAppFunction(fkAppListenceTid));
		List<AppDto> listDto = new ArrayList<AppDto>();
		AppDto dto = null;
		for (AppEntity ce : page.getList()) {

			dto = new AppDto(ce);
			listDto.add(dto); // 封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page)
				.setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: C++查询设备绑定情况
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午5:29:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAppBindingDevice(AppEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<AppDto> data = new ArrayList<AppDto>();
		List<AppEntity> list = this.appEntityMapper.findAll(entity);
		for (AppEntity app : list) {
			data.add(new AppDto(app));
		}
		return resultUtil.setData(data).setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月30日下午1:18:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAppDevice(String fkAppInstanceTid,InterfacePage<DeviceEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(fkAppInstanceTid)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("fkAppTid");
		}
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.deviceEntityMapper.getCountAppDevice(fkAppInstanceTid));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.deviceEntityMapper.findAppDevice(fkAppInstanceTid));
		List<DeviceDto> data = new ArrayList<DeviceDto>();
		for (DeviceEntity app : page.getList()) {
			data.add(new DeviceDto(app));
		}
		return resultUtil.setData(data).setCode(ErrorTypeEnum.SUCCESS).setPage(page);
	}

	/**
	 * <pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(AppEntity entity) throws Exception {

		entity.setTid(UUID.randomUUID().toString());
		return this.appEntityMapper.insert(entity);
	}

	/**
	 * <pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer update(AppEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid())
				|| StringUtils.isBlankOr(entity.getTid())) {

			return null;
		}
		return this.appEntityMapper.updateById(entity);
	}

	/**
	 * <pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Override
	public ResultUtil<List<AppDto>> findPage(AppEntity entity,
			InterfacePage<AppEntity> page) throws Exception {
		ResultUtil<List<AppDto>> resultUtil = new ResultUtil<List<AppDto>>();
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.appEntityMapper.getCount(entity));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appEntityMapper.findAll(entity));
		List<AppDto> listDto = new ArrayList<AppDto>();
		AppDto dto = null;
		for (AppEntity ce : page.getList()) {

			dto = new AppDto(ce);
			listDto.add(dto); // 封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page)
				.setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Override
	public AppEntity findOne(AppEntity entity) throws Exception {

		List<AppEntity> list = this.appEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(AppEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List list = new ArrayList();
		for (AppEntity app : this.appEntityMapper.findAll(entity)) {
			list.add(new AppDto(app));
		}
		return resultUtil.setData(list).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 查询所有应用名称
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月7日 下午3:23:20
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil findAllAppName(AppEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List list = new ArrayList();
		for (AppEntity app : this.appEntityMapper.findAllAppName(entity)) {
			list.add(new AppDto(app));
		}
		return resultUtil.setData(list).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 根据应用ID查询版本号
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月7日下午3:10:58
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findVersionByAppId(AppEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List list = new ArrayList();
		for (AppEntity app : this.appEntityMapper.findVersionByAppId(entity)) {
			list.add(new AppDto(app));
		}
		return resultUtil.setData(list).setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
	 * </pre>
	 */
	@Override
	public AppEntity findById(String tid) throws Exception {

		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AppEntity> list = this.appEntityMapper.findAll(new AppEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public ResultUtil findApp(AppEntity entity, InterfacePage<AppEntity> page)
			throws Exception {
		ResultUtil<List<AppDto>> resultUtil = new ResultUtil<List<AppDto>>();
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.appEntityMapper.getCountApp(entity));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appEntityMapper.findApp(entity));
		List<AppDto> listDto = new ArrayList<AppDto>();
		AppDto dto = null;
		for (AppEntity ce : page.getList()) {

			dto = new AppDto(ce);
			listDto.add(dto); // 封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page)
				.setCode(ErrorTypeEnum.SUCCESS);

	}
}
