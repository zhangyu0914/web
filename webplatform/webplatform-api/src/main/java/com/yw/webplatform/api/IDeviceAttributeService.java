package com.yw.webplatform.api;

import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.entity.DeviceAttributeEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 设备属性
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-22 10:59:29
 * Q    Q: 308053847
 *</pre>
 */
public interface IDeviceAttributeService extends IBaseService<DeviceAttributeEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月19日下午3:32:08
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateData(DeviceAttributeEntity entity) throws Exception;

	public List<DeviceAttributeEntity> findAll(DeviceAttributeEntity entity) throws Exception;
}
