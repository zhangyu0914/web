package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppFunctionDto;
import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppFunctionService;


/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:57:31
 * Q    Q: 308053847
 *</pre>
 */
@Service("appFunctionService")
public class AppFunctionServiceImpl extends BaseMapperImpl  implements IAppFunctionService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(AppFunctionEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.appFunctionEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(AppFunctionEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.appFunctionEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Override
	public ResultUtil<List<AppFunctionDto>> findPage(AppFunctionEntity entity, InterfacePage<AppFunctionEntity> page) throws Exception {
		ResultUtil<List<AppFunctionDto>> resultUtil = new ResultUtil<List<AppFunctionDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.appFunctionEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appFunctionEntityMapper.findAll(entity));
		List<AppFunctionDto> listDto = new ArrayList<AppFunctionDto>();
		AppFunctionDto dto = null;
		for (AppFunctionEntity ce : page.getList()) {
			
			dto = new AppFunctionDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月11日上午10:55:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<AppFunctionEntity> findAppFunctionBySn(AppFunctionEntity entity)
			throws Exception {

		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getAppId(), entity.getSn())) {
			return null;
		}
		return this.appFunctionEntityMapper.findAppFunctionBySn(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Override
	public AppFunctionEntity findOne(AppFunctionEntity entity)
			throws Exception {
		
		List<AppFunctionEntity> list = this.appFunctionEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Override
	public AppFunctionEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AppFunctionEntity> list = this.appFunctionEntityMapper.findAll(
				new AppFunctionEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
