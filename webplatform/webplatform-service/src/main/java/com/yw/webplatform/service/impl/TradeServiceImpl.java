package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.TradeDto;
import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.ITradeService;


/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
@Service("tradeService")
public class TradeServiceImpl extends BaseMapperImpl  implements ITradeService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	
	
	/**
	 * <pre>
	 * 说       明: 添加厂家
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月15日上午11:28:03
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil insertTrade(TradeEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getTradeCode(),
				entity.getTradeName())){
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tradeCode, tradeName");
		}
		TradeEntity trade=new TradeEntity();
		trade.setTradeCode(entity.getTradeCode());
		if (!StringUtils.isBlank(this.findOne(trade))) {
			//机构名已存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_EXISTS);
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.tradeEntityMapper.insert(entity) > 0) {

			return resultUtil.setData(entity).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(TradeEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.tradeEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(TradeEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.tradeEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(TradeEntity entity, InterfacePage<TradeEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.tradeEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.tradeEntityMapper.findAll(entity));
		List<TradeDto> listDto = new ArrayList<TradeDto>();
		TradeDto dto = null;
		for (TradeEntity ce : page.getList()) {
			
			dto = new TradeDto(ce);
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Override
	public TradeEntity findOne(TradeEntity entity)
			throws Exception {
		
		List<TradeEntity> list = this.tradeEntityMapper.findAll(entity);
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Override
	public TradeEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<TradeEntity> list = this.tradeEntityMapper.findAll(
				new TradeEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 上午9:40:57
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(TradeEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<TradeEntity> list = this.tradeEntityMapper.findAll(entity);
		List<TradeDto> listDto = new ArrayList<TradeDto>();
		TradeDto dto = null;
		for (TradeEntity ce : list) {
			dto = new TradeDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
		return resultUtil.setData(listDto).setCode(ErrorTypeEnum.SUCCESS);
	}
}
