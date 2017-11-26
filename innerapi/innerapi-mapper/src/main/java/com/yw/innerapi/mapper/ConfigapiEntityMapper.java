package com.yw.innerapi.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppDeviceEntity;
import com.yw.common.beansUtils.entity.AppInstanceDevEntity;
import com.yw.common.beansUtils.entity.ConfigapiEntity;
import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.entity.importFile.ImportModelEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13:01:21
 * Q    Q: 308053847
 *</pre>
 */
public interface ConfigapiEntityMapper extends IBaseMapper<ConfigapiEntity, String>{
	
	
	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:16:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ImportModelEntity> findModel(ImportModelEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 获取应用配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月24日 下午3:09:41
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<ImportAppEntity> findApp(String[] id) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 获取许可证数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:16:00
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ImportLicenseEntity> findLicense(String[] id) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月27日上午10:27:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppInstanceDevEntity> findAppInstanceDevGet(AppInstanceDevEntity entity) throws Exception;
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月27日上午10:27:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ImportLicenseEntity> findExLicense() throws Exception;
	
	public int changeBatSQL(List<String> batSql) throws Exception;
}
