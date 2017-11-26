package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppDeviceDto;
import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppDeviceService;


/**
 *<pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
@Service("appDeviceService")
public class AppDeviceServiceImpl extends BaseMapperImpl  implements IAppDeviceService{
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
	public Integer insert(AppDeviceEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.appDeviceEntityMapper.insert(entity);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer delete(String tid) throws Exception {
		
		return this.appDeviceEntityMapper.delete(tid);
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
	public Integer update(AppDeviceEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.appDeviceEntityMapper.updateById(entity);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:26:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer updateBySN(AppDeviceEntity entity) throws Exception {
		return this.appDeviceEntityMapper.updateBySN(entity);
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
	public ResultUtil<List<AppDeviceDto>> findPage(AppDeviceEntity entity, InterfacePage<AppDeviceEntity> page) throws Exception {
		ResultUtil<List<AppDeviceDto>> resultUtil = new ResultUtil<List<AppDeviceDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.appDeviceEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appDeviceEntityMapper.findAll(entity));
		List<AppDeviceDto> listDto = new ArrayList<AppDeviceDto>();
		AppDeviceDto dto = null;
		for (AppDeviceEntity ce : page.getList()) {
			
			dto = new AppDeviceDto(ce);
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
	public AppDeviceEntity findOne(AppDeviceEntity entity)
			throws Exception {
		
		List<AppDeviceEntity> list = this.appDeviceEntityMapper.findAll(entity);
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
	public AppDeviceEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AppDeviceEntity> list = this.appDeviceEntityMapper.findAll(
				new AppDeviceEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
