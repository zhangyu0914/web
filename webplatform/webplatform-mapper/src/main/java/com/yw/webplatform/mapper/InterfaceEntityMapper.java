package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:14
 * Q    Q: 308053847
 *</pre>
 */
public interface InterfaceEntityMapper extends IBaseMapper<InterfaceEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月24日下午3:06:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getMaxCode() throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午5:19:14
	 * Q     Q: 982234234
	 * </pre>
	 */
	public InterfaceEntity findByURL(InterfaceEntity entity) throws Exception;
}
