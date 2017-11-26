package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.SnListEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: SN列表
 * 涉及版本: V2.0.0 
 * 创  建  者: zhangyu
 * 日       期: 2017-07-13 10:54:35
 * Q    Q: 982234234
 *</pre>
 */
public interface SnListEntityMapper extends IBaseMapper<SnListEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 根据SN更新
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月13日 上午11:16:56
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer updateBySn(SnListEntity entity) throws Exception;
}
