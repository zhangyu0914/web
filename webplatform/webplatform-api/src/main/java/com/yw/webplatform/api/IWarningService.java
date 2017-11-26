package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
public interface IWarningService extends IBaseService<WarningEntity, String> {

	/**
	 * <pre>
	 * 说       明: 修改警告数据
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午9:46:19
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil warningChange(WarningEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月5日上午9:57:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil getWarningCount(WarningEntity entity) throws Exception;

}
