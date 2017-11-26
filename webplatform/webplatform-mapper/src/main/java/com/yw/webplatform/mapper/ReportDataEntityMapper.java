package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.ReportDataEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 * <pre>
 * 功       能: 
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年3月21日下午3:43:29
 * Q    Q: 308053847
 * </pre>
 */
public interface ReportDataEntityMapper extends IBaseMapper<ReportDataEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日下午3:47:28
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findAppLicenseReport(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日下午4:13:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findDeviceLicenseReport(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月22日下午5:12:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findDeviceStatus(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月29日下午5:40:46
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findDeviceOnlineStatus(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月29日下午5:40:43
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findGateway(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月29日下午5:40:37
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ReportDataEntity> findView(ReportDataEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月14日下午2:02:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ReportDataEntity getPlatformAppDAta(ReportDataEntity entity) throws Exception;
}
