package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
public interface ITradeService extends IBaseService<TradeEntity, String> {
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 上午9:40:57
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil findAll(TradeEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月15日上午11:26:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertTrade(TradeEntity entity) throws Exception;
}
