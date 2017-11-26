package com.yw.webplatform.mapper;

import java.util.Map;

import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-22 10:59:29
 * Q    Q: 308053847
 *</pre>
 */
public interface DeviceAttributeEntityMapper extends IBaseMapper<DeviceAttributeEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月22日下午12:07:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Map<String, String> findKeyValue(DeviceAttributeEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月22日下午12:36:21
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateValue(DeviceAttributeEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月19日下午3:29:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateData(DeviceAttributeEntity entity) throws Exception;
}
