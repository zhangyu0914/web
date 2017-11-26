package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 认证记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface AuthLogEntityMapper extends IBaseMapper<AuthLogEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月26日下午3:56:21
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateOnlineForTimeOUt(AuthLogEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月28日下午3:35:54
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AuthLogEntity> findVaildToken(AuthLogEntity entity) throws Exception;
}
