package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.DeviceModelAttEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 设备与型号属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
public interface DeviceModelAttEntityMapper extends IBaseMapper<DeviceModelAttEntity, String>{
	/**
	 * <pre>
	 * 说       明: 根据外键删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午3:57:16
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer deleteByFK(DeviceModelAttEntity entity) throws Exception;
}
