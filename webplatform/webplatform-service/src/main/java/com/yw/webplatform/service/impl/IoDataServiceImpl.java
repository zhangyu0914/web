package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.IoDataDto;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.entity.PushMsgEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.IoDataDataCodeEnum;
import com.yw.common.beansUtils.utils.enums.WarningWaStatusEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.random.RandomTool;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceAttributeService;
import com.yw.webplatform.api.IIoDataService;


/**
 *<pre>
 * 功       能: IO数据
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-14 16:11:14
 * Q    Q: 308053847
 *</pre>
 */
@Service("ioDataService")
public class IoDataServiceImpl extends BaseMapperImpl  implements IIoDataService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IDeviceAttributeService deviceAttributeService;//
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(IoDataEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.ioDataEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(IoDataEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.ioDataEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Override
	public ResultUtil<List<IoDataDto>> findPage(IoDataEntity entity, InterfacePage<IoDataEntity> page) throws Exception {
		ResultUtil<List<IoDataDto>> resultUtil = new ResultUtil<List<IoDataDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.ioDataEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.ioDataEntityMapper.findAll(entity));
		List<IoDataDto> listDto = new ArrayList<IoDataDto>();
		IoDataDto dto = null;
		for (IoDataEntity ce : page.getList()) {
			
			dto = new IoDataDto(ce);
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Override
	public IoDataEntity findOne(IoDataEntity entity)
			throws Exception {
		
		List<IoDataEntity> list = this.ioDataEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Override
	public IoDataEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<IoDataEntity> list = this.ioDataEntityMapper.findAll(
				new IoDataEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: IO测试数据，一个小时一次
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月24日上午9:55:30
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil insertTestData(String date) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		//获取SN号
		List<DeviceAttributeEntity> deviceAttributeList = this.deviceAttributeEntityMapper.findAll(null);
		if (StringUtils.isBlank(deviceAttributeList)
				|| deviceAttributeList.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		Map<String, String> snMap = new HashMap<String, String>();
		for (DeviceAttributeEntity device : deviceAttributeList) {
			snMap.put(device.getSn(), device.getSn());
		}
		//根据日期随机创建数据
		String tempKey = "";
		IoDataEntity platformIoData = null;
		IoDataEntity deviceIoData = null;
		IoDataEntity appIoData = null;
		PlatformDataEntity platformData = null;
		for (Iterator<String> iterator = snMap.keySet().iterator(); iterator.hasNext();) {
			
			tempKey = iterator.next();
			platformIoData = new IoDataEntity();
			deviceIoData = new IoDataEntity();
			appIoData = new IoDataEntity();
			platformData = new PlatformDataEntity();
			
			{
				platformIoData.setTid(UUIDUtil.getUUID());
				platformIoData.setDataCode(IoDataDataCodeEnum.PLATFORM.toString());
				platformIoData.setInputData(RandomTool.getRandom(100, 200));
				platformIoData.setOutputData(RandomTool.getRandom(0, 100));
				platformIoData.setDataTime(DateUtils.getSysStringTime(null));
				
//				this.ioDataEntityMapper.insert(platformIoData);
			}
			
			{
				deviceIoData.setTid(UUIDUtil.getUUID());
				deviceIoData.setDataCode(IoDataDataCodeEnum.DEVICE.toString());
				deviceIoData.setDataValue(tempKey);
				deviceIoData.setInputData(RandomTool.getRandom(100, 200));
				deviceIoData.setOutputData(RandomTool.getRandom(0, 100));
				deviceIoData.setDataTime(DateUtils.getSysStringTime(null));
				
//				this.ioDataEntityMapper.insert(deviceIoData);
			}
			
			{
				List<AppLicenseEntity> appList = this.appLicenseEntityMapper.findAll(null);
				for (AppLicenseEntity app : appList) {
					
					appIoData.setTid(UUIDUtil.getUUID());
					appIoData.setDataCode(IoDataDataCodeEnum.APP.toString());
					appIoData.setDataValue(app.getAppInstanceLicId());
					appIoData.setInputData(RandomTool.getRandom(100, 200));
					appIoData.setOutputData(RandomTool.getRandom(0, 100));
					appIoData.setDataTime(DateUtils.getSysStringTime(null));
					
//					this.ioDataEntityMapper.insert(appIoData);
				}
			}
			
			{
				platformData.setTid(UUIDUtil.getUUID());
				platformData.setPdata(RandomTool.getRandom(0, 100));
				platformData.setDataTime(DateUtils.getSysStringTime(null));
				
//				this.platformDataEntityMapper.insert(platformData);
			}
		}
		
		{//第三方接口调用次数统计
				
			List<AppAccountInterfaceEntity> appList = this.appAccountInterfaceEntityMapper.findAll(null);
			for (AppAccountInterfaceEntity appAccountInterface : appList) {
				
				InterfaceLogEntity interfaceLog = new InterfaceLogEntity();
				
				interfaceLog.setTid(UUIDUtil.getUUID());
				interfaceLog.setFkAppAccountTid(appAccountInterface.getFkAppAccountTid());
				interfaceLog.setFkAppAccountInterfaceTid(appAccountInterface.getTid());
//				this.interfaceLogEntityMapper.insert(interfaceLog);
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
}
