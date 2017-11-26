package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.entity.SnListEntity;
import com.yw.common.beansUtils.dto.SnListDto;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.ISnListService;


/**
 *<pre>
 * 功       能: SN列表
 * 涉及版本: V2.0.0  
 * 创  建  者: zhangyu
 * 日       期: 2017-07-13 10:54:35
 * Q    Q: 308053847
 *</pre>
 */
@Service("snListService")
public class SnListServiceImpl extends BaseMapperImpl  implements ISnListService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: zhangyu
     * 日       期: 2017-07-13 10:54:35
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(SnListEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.snListEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: zhangyu
     * 日       期: 2017-07-13 10:54:35
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(SnListEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.snListEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017-07-13 10:54:35
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(SnListEntity entity, InterfacePage<SnListEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.snListEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.snListEntityMapper.findAll(entity));
		List<SnListDto> listDto = new ArrayList<SnListDto>();
		SnListDto dto = null;
		for (SnListEntity ce : page.getList()) {
			
			dto = new SnListDto(ce);
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
	 * 创  建  者: zhangyu
	 * 日       期: 2017-07-13 10:54:35
	 *</pre>
	 */
	@Override
	public SnListEntity findOne(SnListEntity entity)
			throws Exception {
		
		List<SnListEntity> list = this.snListEntityMapper.findAll(entity);
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
	 * 创  建  者: zhangyu
	 * 日       期: 2017-07-13 10:54:35
	 *</pre>
	 */
	@Override
	public SnListEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<SnListEntity> list = this.snListEntityMapper.findAll(
				new SnListEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
