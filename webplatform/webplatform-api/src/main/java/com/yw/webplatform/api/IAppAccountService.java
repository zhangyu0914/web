package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppSecretEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IAppAccountService extends IBaseService<AppAccountEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月21日下午3:32:33
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertAppAccount(AppAccountEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: MQTT认证接口
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月31日上午9:53:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil authMQTT(String app_id) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月13日上午11:27:16
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil removeAppAccount(AppAccountEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:14:14
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppAccountEntity> findAll(AppAccountEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:16:17
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer delete(String tid) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:24:47
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppAccountEntity> findAppAccount(String[] ids) throws Exception;

}
