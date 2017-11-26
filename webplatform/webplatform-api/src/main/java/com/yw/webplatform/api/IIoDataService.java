package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: IO数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-14 16:11:14
 * Q    Q: 308053847
 *</pre>
 */
public interface IIoDataService extends IBaseService<IoDataEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月24日上午9:55:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertTestData(String date) throws Exception;

}
