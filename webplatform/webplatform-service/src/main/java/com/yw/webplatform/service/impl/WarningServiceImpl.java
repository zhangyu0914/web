package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.WarningDto;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IWarningService;


/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
@Service("warningService")
public class WarningServiceImpl extends BaseMapperImpl  implements IWarningService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IDeviceService deviceService;//
	/**
	 * <pre>
	 * 说       明: 修改警告数据
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午9:48:08
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil warningChange(WarningEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity.getTid()) 
				|| StringUtils.isBlankOr(entity.getWaStatus())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid,waStatus");
		}
		if (this.warningEntityMapper.updateById(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月5日上午9:57:06
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil getWarningCount(WarningEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getEqType())) {
			return resultUtil.setData(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("eqType");
		}
		return resultUtil.setData(this.warningEntityMapper.getWarningCount(entity)).setCode(ErrorTypeEnum.SUCCESS);
	}
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(WarningEntity entity) throws Exception {
		return this.warningEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(WarningEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.warningEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Override
	public ResultUtil<List<WarningDto>> findPage(WarningEntity entity, InterfacePage<WarningEntity> page) throws Exception {
		
		ResultUtil<List<WarningDto>> resultUtil = new ResultUtil<List<WarningDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.warningEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.warningEntityMapper.findAll(entity));
		List<WarningDto> listDto = new ArrayList<WarningDto>();
		WarningDto dto = null;
		for (WarningEntity ce : page.getList()) {
			dto = new WarningDto(ce);
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Override
	public WarningEntity findOne(WarningEntity entity)
			throws Exception {
		
		List<WarningEntity> list = this.warningEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Override
	public WarningEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<WarningEntity> list = this.warningEntityMapper.findAll(
				new WarningEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
