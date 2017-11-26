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
import com.yw.common.beansUtils.dto.DeviceAttributeDto;
import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceModelAttEntity;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeGatewayEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeViewEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqStatusEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceAttributeService;
import com.yw.webplatform.api.IDeviceLicenseService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IModelService;


/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-22 10:59:29
 * Q    Q: 308053847
 *</pre>
 */
@Service("deviceAttributeService")
public class DeviceAttributeServiceImpl extends BaseMapperImpl  implements IDeviceAttributeService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IDeviceService deviceService;//
	@Autowired
	private IModelService modelService;//
	@Autowired
	private IDeviceLicenseService deviceLicenseService;//
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(DeviceAttributeEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.deviceAttributeEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(DeviceAttributeEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.deviceAttributeEntityMapper.updateById(entity);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月19日下午3:31:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer updateData(DeviceAttributeEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getSn()) 
				|| StringUtils.isBlankOr(entity.getPropId(),
						entity.getModelNo())) {
			return null;
		}
		return this.deviceAttributeEntityMapper.updateData(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(DeviceAttributeEntity entity, InterfacePage<DeviceAttributeEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.deviceAttributeEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.deviceAttributeEntityMapper.findAll(entity));
		List<DeviceAttributeDto> listDto = new ArrayList<DeviceAttributeDto>();
		DeviceAttributeDto dto = null;
		for (DeviceAttributeEntity ce : page.getList()) {
			
			dto = new DeviceAttributeDto(ce);
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
	 * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Override
	public DeviceAttributeEntity findOne(DeviceAttributeEntity entity)
			throws Exception {
		
		List<DeviceAttributeEntity> list = this.deviceAttributeEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Override
	public List<DeviceAttributeEntity> findAll(DeviceAttributeEntity entity) throws Exception {
		
		return this.deviceAttributeEntityMapper.findAll(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-22 10:59:29
	 *</pre>
	 */
	@Override
	public DeviceAttributeEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<DeviceAttributeEntity> list = this.deviceAttributeEntityMapper.findAll(
				new DeviceAttributeEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
