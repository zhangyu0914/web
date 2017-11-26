package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
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
public interface UsersFunctionsEntityMapper extends IBaseMapper<UsersFunctionsEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午2:58:00
	 * Q    Q: 308053847
	 * </pre>
	 * @param entity TODO
	 */
	public Integer getMaxCode(UsersFunctionsEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午2:42:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateParentCodeById(UsersFunctionsEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月10日下午1:15:58
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<UsersFunctionsEntity> findByUserId(UserInfoEntity entity) throws Exception;
}
