package com.yw.webplatform.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface ILicenseService extends IBaseService<LicenseEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:14:38
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appLicenseReport(HomePageEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:16:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deviceLicenseReport(HomePageEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午11:32:29
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAppLicense(LicenseEntity entity, InterfacePage<LicenseEntity> page) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日下午12:00:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findDeviceLicense(LicenseEntity entity, InterfacePage<LicenseEntity> page) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月1日下午3:05:22
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findExLicense() throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月5日下午8:47:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<LicenseEntity> findAll(LicenseEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:17:10
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer delete(String tid) throws Exception;
}
