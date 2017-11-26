package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
public interface ModelEntityMapper extends IBaseMapper<ModelEntity, String>{
	/**
	 * <pre>
	 * 说       明: 查询所有厂商设备型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月5日 下午4:32:38
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<ModelEntity> findAllModel(ModelEntity entity) throws Exception;
}
