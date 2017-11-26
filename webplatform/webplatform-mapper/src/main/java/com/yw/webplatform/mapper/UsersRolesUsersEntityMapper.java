package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.UsersRolesUsersEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 调用接口记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface UsersRolesUsersEntityMapper extends IBaseMapper<UsersRolesUsersEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午3:20:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer deleteByFK(UsersRolesUsersEntity entity) throws Exception;
}
