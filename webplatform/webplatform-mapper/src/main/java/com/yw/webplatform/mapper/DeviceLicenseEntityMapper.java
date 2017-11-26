package com.yw.webplatform.mapper;

import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
public interface DeviceLicenseEntityMapper extends IBaseMapper<DeviceLicenseEntity, String>{
	/**
	 * <pre>
	 * 说       明: 根据外键删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午4:04:05
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer deleteByFK(DeviceLicenseEntity entity) throws Exception;
}
