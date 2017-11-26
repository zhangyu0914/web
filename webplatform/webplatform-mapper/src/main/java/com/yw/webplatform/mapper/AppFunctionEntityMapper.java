package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:57:30
 * Q    Q: 308053847
 *</pre>
 */
public interface AppFunctionEntityMapper extends IBaseMapper<AppFunctionEntity, String>{
	/**
	 * <pre>
	 * 说       明: 根据外键删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午4:04:05
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer deleteByFK(AppFunctionEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月11日上午10:57:27
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppFunctionEntity> findAppFunctionBySn(AppFunctionEntity entity) throws Exception;
}
