package com.yw.appapi.api;

import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.resultUtil.ControlResultUtil;



/**
 *<pre>
 * 功       能: 平台提供APP访问接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 *</pre>
 */
public interface IPlatformAppService {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午12:52:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil deviceGet(String sn) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午1:23:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil historyDeviceDataGet(PlatformAppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午1:41:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil onofflineDeviceDataGet(PlatformAppEntity entity)
			throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午1:43:23
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil historyDescendingOrderGet(PlatformAppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午1:44:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Object deviceControl(ControlEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午2:32:16
	 * Q    Q: 308053847
	 * </pre>
	 * @param sn TODO
	 * @param token TODO
	 */
	public AppResultUtil devicebindSet(String app_instance, String sn, String token) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午2:33:27
	 * Q    Q: 308053847
	 * </pre>
	 * @param sn TODO
	 * @param token TODO
	 */
	public AppResultUtil devicebindDel(String app_instance, String sn, String token) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午2:33:27
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil appstatusSet(PlatformAppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 应用错误提交接口(A1010)
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午2:42:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil appstatusPost(PlatformAppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 设备实时状态查询接口(A1011)
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月9日下午2:45:44
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil deviceRealTimeGet(String sn) throws Exception;

}
