package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
public interface AttributeEntityMapper extends IBaseMapper<AttributeEntity, String>{
	
	public List<AttributeEntity> findByProfileId(AttributeEntity entity) throws Exception;
}
