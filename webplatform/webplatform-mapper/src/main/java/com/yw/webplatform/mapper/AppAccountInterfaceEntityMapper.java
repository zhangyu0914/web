package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface AppAccountInterfaceEntityMapper extends IBaseMapper<AppAccountInterfaceEntity, String>{
	/**
	 * <pre>
	 * 说       明: 查询一条数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月19日 下午2:15:30
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<AppAccountInterfaceEntity> findByFK(AppAccountInterfaceEntity entity);
}
