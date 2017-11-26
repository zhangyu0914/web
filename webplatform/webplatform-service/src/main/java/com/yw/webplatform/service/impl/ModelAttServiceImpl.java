package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.ModelAttDto;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IModelAttService;


/**
 *<pre>
 * 功       能: 设备型号与属性表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:07:23
 * Q    Q: 308053847
 *</pre>
 */
@Service("modelAttService")
public class ModelAttServiceImpl extends BaseMapperImpl  implements IModelAttService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:07:23
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(ModelAttEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.modelAttEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-13 18:07:23
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(ModelAttEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.modelAttEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:07:23
	 *</pre>
	 */
	@Override
	public ResultUtil<List<ModelAttDto>> findPage(ModelAttEntity entity, InterfacePage<ModelAttEntity> page) throws Exception {
		ResultUtil<List<ModelAttDto>> resultUtil = new ResultUtil<List<ModelAttDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.modelAttEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.modelAttEntityMapper.findAll(entity));
		List<ModelAttDto> listDto = new ArrayList<ModelAttDto>();
		ModelAttDto dto = null;
		for (ModelAttEntity ce : page.getList()) {
			
			dto = new ModelAttDto(ce);
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
	 * 日       期: 2017-03-13 18:07:23
	 *</pre>
	 */
	@Override
	public ModelAttEntity findOne(ModelAttEntity entity)
			throws Exception {
		
		List<ModelAttEntity> list = this.modelAttEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-13 18:07:23
	 *</pre>
	 */
	@Override
	public ModelAttEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ModelAttEntity> list = this.modelAttEntityMapper.findAll(
				new ModelAttEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
