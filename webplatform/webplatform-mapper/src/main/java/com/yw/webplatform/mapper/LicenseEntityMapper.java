package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface LicenseEntityMapper extends IBaseMapper<LicenseEntity, String>{
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日下午1:16:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<LicenseEntity> findDeviceLicense(LicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日下午1:16:28
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getCountDeviceLicense(LicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日下午1:16:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<LicenseEntity> findAppLicense(LicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日下午1:16:28
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getCountAppLicense(LicenseEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月10日下午6:44:23
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getMaxCode() throws Exception;
}
