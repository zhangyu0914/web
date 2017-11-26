package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.ModelEpDto;
import com.yw.common.beansUtils.entity.ModelEpEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IModelEpService;


/**
 *<pre>
 * 功       能: 型号流程
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-06-07 16:36:19
 * Q    Q: 308053847
 *</pre>
 */
@Service("modelEpService")
public class ModelEpServiceImpl extends BaseMapperImpl  implements IModelEpService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-07 16:36:19
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(ModelEpEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.modelEpEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-07 16:36:19
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(ModelEpEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.modelEpEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-06-07 16:36:19
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(ModelEpEntity entity, InterfacePage<ModelEpEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.modelEpEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.modelEpEntityMapper.findAll(entity));
		List<ModelEpDto> listDto = new ArrayList<ModelEpDto>();
		ModelEpDto dto = null;
		for (ModelEpEntity ce : page.getList()) {
			
			dto = new ModelEpDto(ce);
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
	 * 日       期: 2017-06-07 16:36:19
	 *</pre>
	 */
	@Override
	public ModelEpEntity findOne(ModelEpEntity entity)
			throws Exception {
		
		List<ModelEpEntity> list = this.modelEpEntityMapper.findAll(entity);
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
	 * 日       期: 2017-06-07 16:36:19
	 *</pre>
	 */
	@Override
	public ModelEpEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ModelEpEntity> list = this.modelEpEntityMapper.findAll(
				new ModelEpEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
