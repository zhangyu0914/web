package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface AppAccountEntityMapper extends IBaseMapper<AppAccountEntity, String>{
	/**
	 * <pre>
	 * 说       明: 查询一条数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月19日 下午2:15:30
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<AppAccountEntity> findOne(String token);
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月13日上午10:20:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppAccountEntity> findAppAccount(String[] ids);
}
