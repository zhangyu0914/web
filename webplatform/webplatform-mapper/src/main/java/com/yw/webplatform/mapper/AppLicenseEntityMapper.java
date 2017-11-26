package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 型号许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:46
 * Q    Q: 308053847
 *</pre>
 */
public interface AppLicenseEntityMapper extends IBaseMapper<AppLicenseEntity, String>{

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月20日下午2:35:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppLicenseEntity findAppIO(AppLicenseEntity entity) throws Exception;
	

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日上午10:36:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppLicenseEntity> findAppDetailReport(AppLicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月12日上午10:15:08
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppLicenseEntity> findAppReport(AppLicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月12日上午10:16:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getCountAppReport(AppLicenseEntity entity) throws Exception;
}
