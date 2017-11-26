package com.yw.webplatform.api;

import com.github.abel533.echarts.Option;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;



/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface IHomePageService{

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日上午10:00:20
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil platformReport(PlatformDataEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日上午10:31:05
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deviceReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日上午10:40:15
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appReport(HomePageEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午6:26:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil ioReport(HomePageEntity entity) throws Exception;

}
