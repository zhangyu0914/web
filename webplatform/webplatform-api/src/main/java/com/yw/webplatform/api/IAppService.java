package com.yw.webplatform.api;

import java.util.List;

import com.github.abel533.echarts.Option;
import com.yw.common.beansUtils.dto.AppDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface IAppService extends IBaseService<AppEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午5:29:03
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAppBindingDevice(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:11:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil<List<AppDto>> appReport(AppLicenseEntity entity, InterfacePage<AppLicenseEntity> page) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:25:15
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appIOReport(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: I0统计报表接口单个应用分时间统计
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:45:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appDetailReport(AppLicenseEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午9:52:49
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil<List> findAppFunction(String fkAppListenceTid, InterfacePage<AppEntity> page) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月30日下午1:18:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAppDevice(String fkAppInstanceTid,InterfacePage<DeviceEntity> page) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 下午2:20:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil findApp(AppEntity entity, InterfacePage<AppEntity> page) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 下午2:20:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil findAllAppName(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月19日下午5:10:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAll(AppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 根据应用ID查询版本号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月7日下午3:10:35
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findVersionByAppId(AppEntity entity) throws Exception;
}
