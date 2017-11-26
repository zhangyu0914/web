package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:57:31
 * Q    Q: 308053847
 *</pre>
 */
public interface IAppFunctionService extends IBaseService<AppFunctionEntity, String> {

	/**
	 * <pre>
	 * 说       明: 根据SN查询型号是否支持
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月11日上午10:53:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppFunctionEntity> findAppFunctionBySn(AppFunctionEntity entity) throws Exception;

}
