package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 设备型号与属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:07:17
 * Q    Q: 308053847
 *</pre>
 */
public interface ModelAttEntityMapper extends IBaseMapper<ModelAttEntity, String>{
	/**
	 * <pre>
	 * 说       明: 根据外键删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午4:04:05
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer deleteByFK(ModelAttEntity entity) throws Exception;
}
