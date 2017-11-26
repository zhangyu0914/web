package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
public interface AppDeviceEntityMapper extends IBaseMapper<AppDeviceEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月12日下午6:04:19
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppDeviceEntity> finDevice(AppDeviceEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月15日下午4:10:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateBySN(AppDeviceEntity entity) throws Exception;
}
