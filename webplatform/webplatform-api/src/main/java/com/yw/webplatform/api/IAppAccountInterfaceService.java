package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IAppAccountInterfaceService extends IBaseService<AppAccountInterfaceEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月24日下午3:32:10
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertAppAccountInterface(AppAccountInterfaceEntity entity) throws Exception;

}
