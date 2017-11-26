package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:15
 * Q    Q: 308053847
 *</pre>
 */
public interface OrganizationEntityMapper extends IBaseMapper<OrganizationEntity, String>{
	/**
	 * <pre>
	 * 说       明: 查询机构编码
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月11日 下午3:55:26
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer getMaxCode(OrganizationEntity entity) throws Exception;
}
