package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.add.LegendEntity;
import com.github.abel533.echarts.add.LegendEnum;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.Y;
import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.DeviceDto;
import com.yw.common.beansUtils.dto.HomePageDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceModelAttEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.ReportDataEntity;
import com.yw.common.beansUtils.entity.RouteEntity;
import com.yw.common.beansUtils.entity.SnListEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ReportUtil;
import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeGatewayEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeViewEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqStatusEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceService;


/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
@Service("deviceService")
public class DeviceServiceImpl extends BaseMapperImpl  implements IDeviceService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 * <pre>
	 * 说       明: 设备配置提交
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:10:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil changeSetting(List<DeviceEntity> list) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(list)
				|| list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("list");
		}
		for (DeviceEntity device : list) {
			if (StringUtils.isBlank(device.getFkDeviceModelAttTid())
				|| StringUtils.isBlank(device.getAttValue())) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("fkDeviceModelAttTid,attValue");
			}
			this.deviceModelAttEntityMapper.updateById(new DeviceModelAttEntity(device.getFkDeviceModelAttTid(),device.getAttValue()));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:10:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findSetting(DeviceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getSn())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("sn");
		}
		List<DeviceEntity> list = this.deviceEntityMapper.findSetting(entity);
		List<DeviceDto> listDto = new ArrayList<DeviceDto>();
		DeviceDto dto = null;
		for (DeviceEntity ce : list) {
			
			dto = new DeviceDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备菜单感知终端报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:18:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil deviceViewReport(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		re.setWarningCount(this.warningEntityMapper.getWarningCount(new WarningEntity(DeviceEqTypeEnum.VIEW.getCode())));
		
		{//正常/异常
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			List<ReportDataEntity> pvfeList = this.reportDataEntityMapper.findView(null);		
			
			LegendEntity lgendEntity = null;
			List<String> legendArray = new ArrayList<String>();
			for (ReportDataEntity data : pvfeList) {
				
				
				lgendEntity = new LegendEntity(
						data.getDataName(), 
						Position.inside,
						"", null, null, null);
				
				lgendEntity.setValue(Double.valueOf(data.getFirstData()));
				legendArray.add(data.getDataName());
				
				pieSeriesMap.put(data.getDataName(),lgendEntity);
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "感知终端", null, 20, 20, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "80", "140", null, null, legendArray.toArray());
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "35%", "60%", "50%", Position.inside, null, false, 15, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, null,null,null, null);
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
			
			List<DeviceEntity> pvfeList = this.deviceEntityMapper.findAll(new DeviceEntity(null, null, DeviceEqTypeEnum.VIEW.getCode()));		
			
			LegendEntity lgendEntity = null;
			for (DeviceEntity data : pvfeList) {
				
				if (data.getEqStatus().intValue() == DeviceEqStatusEnum.NORMAL.getCode().intValue()) {
					
					lgendEntity = pieSeriesMap.get(LegendEnum.EQ_ONLINE.getName());
					lgendEntity.setValue(Double.valueOf(lgendEntity.getValue() + 1));
					pieSeriesMap.put(LegendEnum.EQ_ONLINE.getName(),lgendEntity);
				}else{
					
					lgendEntity = pieSeriesMap.get(LegendEnum.EQ_OFLINE.getName());
					lgendEntity.setValue(Double.valueOf(lgendEntity.getValue() + 1));
					pieSeriesMap.put(LegendEnum.EQ_OFLINE.getName(),lgendEntity);
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, null, null, null, null, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "80", "140", null, null, legendArray);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "35%", "60%", "50%", Position.inside, null, true, 15, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备网关设备报表
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:17:29
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil gatewayReport(AppEntity entity) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		HomePageEntity re = new HomePageEntity();
		re.setWarningCount(this.warningEntityMapper.getWarningCount(new WarningEntity(DeviceEqTypeEnum.GATEWAY.getCode())));
		
		{//正常/异常
			Option option = new Option();
			
			Map<String, LegendEntity> pieSeriesMap = new LinkedHashMap<String, LegendEntity>();
			Map<String, LegendEntity> legendMap = new LinkedHashMap<String, LegendEntity>();

			List<SnListEntity> snList = this.snListEntityMapper.findAll(new SnListEntity(DeviceEqTypeEnum.GATEWAY.getCode()));
			for (SnListEntity snListEntity : snList) {
				
				legendMap.put(snListEntity.getEqName(), new LegendEntity(
						snListEntity.getEqName(), 
						Position.inside,
						"", null, null, null));
				pieSeriesMap.put(snListEntity.getEqName(), new LegendEntity(
						snListEntity.getEqName(), 
						Position.outside,
						"{b}", null, null, 0D));
			}
			
			Object[] legendHeadArray = LegendEntity.getLegend(legendMap);
			Object[] legendArray = legendHeadArray;
			Map<String, Object[]> seriesMap = new LinkedHashMap<String, Object[]>();
			
			List<ReportDataEntity> pvfeList = this.reportDataEntityMapper.findGateway(null);		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			List<String> legendList = new ArrayList<String>();
			LegendEntity lgendEntity = null;
			int index =0;
			for (ReportDataEntity data : pvfeList) {
				
				lgendEntity = pieSeriesMap.get(data.getDataName());
				lgendEntity.setValue(Double.valueOf(data.getFirstData()));
				pieSeriesMap.put(data.getDataName(),lgendEntity);
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, "网关", "", 20, 20, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "80", "140", null, null, legendArray);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "35%", "60%", "50%", Position.inside, null, false, 15, true, null);
			
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
			
			List<DeviceEntity> pvfeList = this.deviceEntityMapper.findAll(new DeviceEntity(null, null, DeviceEqTypeEnum.GATEWAY.getCode()));		
			
			for (Object str : legendArray) {
				seriesMap.put((String) str, new Object[pvfeList.size()]);
			}
			LegendEntity lgendEntity = null;
			for (DeviceEntity data : pvfeList) {
				
				if (data.getEqStatus().intValue() == DeviceEqStatusEnum.NORMAL.getCode().intValue()) {
					
					lgendEntity = pieSeriesMap.get(LegendEnum.EQ_ONLINE.getName());
					lgendEntity.setValue(Double.valueOf(lgendEntity.getValue() + 1));
					pieSeriesMap.put(LegendEnum.EQ_ONLINE.getName(),lgendEntity);
				}else{
					
					lgendEntity = pieSeriesMap.get(LegendEnum.EQ_OFLINE.getName());
					lgendEntity.setValue(Double.valueOf(lgendEntity.getValue() + 1));
					pieSeriesMap.put(LegendEnum.EQ_OFLINE.getName(),lgendEntity);
				}
			}
			
			//基础定义
			option = ReportUtil.getTitle(option, null, null, null, null, null, null);
			
			//提示框
			option = ReportUtil.getTooltip(option, null, Trigger.item, null);
			
			//图例
			option = ReportUtil.getLegend(option, true,Orient.vertical,null,Y.top, "80", "140", null, null, legendArray);
			
			//饼图
			ReportUtil.getPie(option, pieSeriesMap, "35%", "60%", "50%", Position.inside, null, true, 15, true, null);
			
			//布局
			option = ReportUtil.getGrid(option, null, null, null, null);
			re.setOption(option);
		}
		return resultUtil.setData(new HomePageDto(re)).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 设备路由
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午10:20:29
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findDeviceRoute(RouteEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		RouteEntity root = new RouteEntity();
		
		root.setLevel(0);
		root.setEqName("接入设备");
		root.setEqType(DeviceEqTypeEnum.VIEW.getCode());
		root.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		root.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		root.setModelNo("1001");
		
		List<RouteEntity> childrenList = new ArrayList<RouteEntity>();
		RouteEntity route1 = new RouteEntity();
		RouteEntity route2 = new RouteEntity();
		RouteEntity route3 = new RouteEntity();
		
		RouteEntity route4 = new RouteEntity();
		RouteEntity route5 = new RouteEntity();
		
		route1.setLevel(1);
		route1.setEqName("电子标签");
		route1.setEqType(DeviceEqTypeEnum.VIEW.getCode());
		route1.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		route1.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		route1.setModelNo("1002");
		
		route2.setLevel(1);
		route2.setEqName("母婴标签");
		route2.setEqType(DeviceEqTypeEnum.VIEW.getCode());
		route2.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		route2.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		route2.setModelNo("1003");
		
		route3.setLevel(1);
		route3.setEqName("婴儿标签");
		route3.setEqType(DeviceEqTypeEnum.VIEW.getCode());
		route3.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		route3.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		route3.setModelNo("1004");
		
		route4.setLevel(1);
		route4.setEqName("网关1");
		route4.setEqType(DeviceEqTypeEnum.GATEWAY.getCode());
		route4.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		route4.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		route4.setModelNo("1005");
		
		route5.setLevel(1);
		route5.setEqName("网关2");
		route5.setEqType(DeviceEqTypeEnum.GATEWAY.getCode());
		route5.setEqStatus(DeviceEqStatusEnum.NORMAL.getCode());
		route5.setEqIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000"
				+ "&sec=1489423223935&di=72f4f3eb657ed0777dbfafbf54c1ade7&imgtype=0"
				+ "&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F019c28554b6159000001bf729078c0.jpg");
		route5.setModelNo("1006");
		
		childrenList.add(route1);
		childrenList.add(route2);
		childrenList.add(route3);
		childrenList.add(route4);
		childrenList.add(route5);
		
		root.setChildrenList(childrenList);
		
		return resultUtil.setData(root).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(DeviceEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.deviceEntityMapper.insert(entity);
	}
	

	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Override
	public ResultUtil<List<DeviceDto>> findPage(DeviceEntity entity, InterfacePage<DeviceEntity> page) throws Exception {
		ResultUtil<List<DeviceDto>> resultUtil = new ResultUtil<List<DeviceDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.deviceEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.deviceEntityMapper.findAll(entity));
		List<DeviceDto> listDto = new ArrayList<DeviceDto>();
		DeviceDto dto = null;
		for (DeviceEntity ce : page.getList()) {
			
			dto = new DeviceDto(ce);
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
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Override
	public DeviceEntity findOne(DeviceEntity entity)
			throws Exception {
		
		List<DeviceEntity> list = this.deviceEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月11日下午5:12:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public DeviceEntity findOneBySn(String sn) throws Exception {
		return this.deviceEntityMapper.findOneBySn(sn);
	}
	
	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Override
	public DeviceEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<DeviceEntity> list = this.deviceEntityMapper.findAll(
				new DeviceEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午3:09:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<DeviceEntity> findAll(DeviceEntity entity) throws Exception {
		
		List<DeviceEntity> list = this.deviceEntityMapper.findAll(entity);
		return list;
	}


	/**
	 * 
	 * <pre>
	 * 说       明: 根据sn更新设备状态（t_device）
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月4日下午2:17:34
	 * Q    Q: 17789861157
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer update(DeviceEntity entity) throws Exception {
		//根据sn取出要更新的记录(获取了更新所需要的tid)
		DeviceEntity entityForUpdate = this.deviceEntityMapper.findOneBySn(entity.getSn());
		//对数据库中查找出来的值进行判断,没有找到更新的值，返回0
		if(StringUtils.isBlank(entityForUpdate)){
			return 0;
		}
		//设置新的设备状态
		entityForUpdate.setEqStatus(entity.getEqStatus());
		//返回更新结果
		return this.deviceEntityMapper.updateById(entityForUpdate);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月7日下午4:15:09
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil updateStatusBySN(DeviceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getSn())) {
			return resultUtil;
		}
		Integer result = this.deviceEntityMapper.updateStatusBySN(entity);
		if (result > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil;
	}
}
