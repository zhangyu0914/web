package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 平台数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-14 16:11:11
 * Q    Q: 308053847
 *</pre>
 */
public interface PlatformDataEntityMapper extends IBaseMapper<PlatformDataEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日上午11:07:10
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<PlatformDataEntity> findPatformReport(PlatformDataEntity entity) throws Exception;
	
	
	/**
	 * <pre>
	 * 说       明: 获取MQTT推送数量
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月31日下午2:53:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<PlatformDataEntity> findMqtt(PlatformDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明:获取接口访问数量
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月31日下午2:53:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<PlatformDataEntity> findInerfaceLogCount(PlatformDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询设备上线总数
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月10日上午10:26:39
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<PlatformDataEntity> findDeviceOnLineCount(PlatformDataEntity entity) throws Exception;
}
