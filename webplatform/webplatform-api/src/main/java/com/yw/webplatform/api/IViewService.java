package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.entity.ViewEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
public interface IViewService extends IBaseService<ViewEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月8日下午5:03:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findWarningReport(HomePageEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日上午9:59:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAppDeviceReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日上午10:05:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findPlatformReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日上午10:09:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findIOAndInterface(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日上午10:17:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<PlatformDataEntity> findMqtt() throws Exception;

}
