package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.AuthLogEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 认证记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IAuthLogService extends IBaseService<AuthLogEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月26日上午10:23:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil auth(PlatformAppEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月26日下午3:53:18
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateOnlineForTimeOUt(AuthLogEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月28日下午3:40:54
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AuthLogEntity findVaildToken(AuthLogEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月10日下午5:17:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AuthLogEntity findAuthLogByToken(String token) throws Exception;

}
