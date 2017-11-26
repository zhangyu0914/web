package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.ViewEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
public interface ViewEntityMapper extends IBaseMapper<ViewEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月15日下午1:32:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getAppWarningCount(ViewEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月15日下午1:33:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getDeviceWarningCount(ViewEntity entity) throws Exception;
}
