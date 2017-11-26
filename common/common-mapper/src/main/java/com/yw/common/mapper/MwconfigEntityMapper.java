package com.yw.common.mapper;


import java.util.List;

import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.entity.importFile.MwCfgEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:37
 * Q    Q: 308053847
 *</pre>
 */
public interface MwconfigEntityMapper extends IBaseMapper<MwconfigEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月27日下午6:05:03
	 * Q    Q: 308053847
	 * </pre>
	 * @param ids TODO
	 */
	public List<MwCfgEntity> findMwConfig(String[] ids) throws Exception;
}
