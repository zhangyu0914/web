package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.DeviceLicenseDto;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceLicenseService;


/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
@Service("deviceLicenseService")
public class DeviceLicenseServiceImpl extends BaseMapperImpl  implements IDeviceLicenseService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(DeviceLicenseEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.deviceLicenseEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(DeviceLicenseEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.deviceLicenseEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Override
	public ResultUtil<List<DeviceLicenseDto>> findPage(DeviceLicenseEntity entity, InterfacePage<DeviceLicenseEntity> page) throws Exception {
		ResultUtil<List<DeviceLicenseDto>> resultUtil = new ResultUtil<List<DeviceLicenseDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.deviceLicenseEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.deviceLicenseEntityMapper.findAll(entity));
		List<DeviceLicenseDto> listDto = new ArrayList<DeviceLicenseDto>();
		DeviceLicenseDto dto = null;
		for (DeviceLicenseEntity ce : page.getList()) {
			
			dto = new DeviceLicenseDto(ce);
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Override
	public DeviceLicenseEntity findOne(DeviceLicenseEntity entity)
			throws Exception {
		
		List<DeviceLicenseEntity> list = this.deviceLicenseEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Override
	public DeviceLicenseEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<DeviceLicenseEntity> list = this.deviceLicenseEntityMapper.findAll(
				new DeviceLicenseEntity(tid));
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
	 * 日       期: 2017年3月15日下午1:42:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<DeviceLicenseEntity> findAll(DeviceLicenseEntity entity) throws Exception {
		
		List<DeviceLicenseEntity> list = this.deviceLicenseEntityMapper.findAll(entity);
		return list;
	}
}
