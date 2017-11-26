package com.yw.webplatform.api;

import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.dto.FunctionDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 权限表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
public interface IUsersFunctionsService extends IBaseService<UsersFunctionsEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午2:58:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertFunction(UsersFunctionsEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午3:11:03
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAll(UsersFunctionsEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午2:36:05
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deleteFunctions(UsersFunctionsEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午5:40:32
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateFunctions(UsersFunctionsEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月10日下午1:15:23
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Map<String, List<FunctionDto>> findByUserId(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月10日下午4:21:28
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findParent(UsersFunctionsEntity entity, String rolesId) throws Exception;

}
