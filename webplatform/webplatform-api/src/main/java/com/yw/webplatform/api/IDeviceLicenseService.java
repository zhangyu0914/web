package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
public interface IDeviceLicenseService extends IBaseService<DeviceLicenseEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午1:42:21
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<DeviceLicenseEntity> findAll(DeviceLicenseEntity entity) throws Exception;

}
