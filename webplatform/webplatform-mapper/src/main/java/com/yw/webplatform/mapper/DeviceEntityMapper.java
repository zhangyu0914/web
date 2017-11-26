package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
public interface DeviceEntityMapper extends IBaseMapper<DeviceEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月14日下午3:41:39
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer updateStatusBySN(DeviceEntity entity) throws Exception;
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日下午4:06:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<DeviceEntity> findSetting(DeviceEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询APP下的绑定设备
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月5日 下午5:43:40
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<DeviceEntity> findAppDevice(String appInstanceLicId) throws Exception;

	/**
	 * <pre>
	 * 说       明: 查询APP下的绑定设备数量
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月5日 下午5:43:50
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer getCountAppDevice(String appInstanceLicId) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 说       明: 根据sn查找一条记录
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月4日下午4:24:24
	 * Q    Q: 17789861157
	 * </pre>
	 */
	public DeviceEntity findOneBySn(String sn) throws Exception;
}
