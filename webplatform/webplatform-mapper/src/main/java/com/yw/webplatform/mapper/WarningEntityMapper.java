package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
public interface WarningEntityMapper extends IBaseMapper<WarningEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月5日上午9:55:33
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getWarningCount(WarningEntity entity) throws Exception;
}
