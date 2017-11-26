package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 角色表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
public interface IUsersRolesService extends IBaseService<UsersRolesEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午3:51:35
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertRoles(UsersRolesEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午4:48:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAll(UsersRolesEntity entity, String usersId) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午2:32:20
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deleteRoles(UsersRolesEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午5:36:30
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateRoles(UsersRolesEntity entity) throws Exception;

}
