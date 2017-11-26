package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.RouteEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
public interface IDeviceService extends IBaseService<DeviceEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午3:09:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<DeviceEntity> findAll(DeviceEntity entity) throws Exception;


	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:18:03
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deviceViewReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:19:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil gatewayReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:10:54
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findSetting(DeviceEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:15:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil changeSetting(List<DeviceEntity> list) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 设备路由
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午10:20:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findDeviceRoute(RouteEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 根据SN查询数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月11日下午5:12:33
	 * Q    Q: 308053847
	 * </pre>
	 */
	public DeviceEntity findOneBySn(String sn) throws Exception;


	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月7日下午4:09:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateStatusBySN(DeviceEntity entity) throws Exception;
}
