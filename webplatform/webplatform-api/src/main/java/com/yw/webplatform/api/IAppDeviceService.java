package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 应用与设备
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
public interface IAppDeviceService extends IBaseService<AppDeviceEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:26:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateBySN(AppDeviceEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:37:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer delete(String tid) throws Exception;

}
