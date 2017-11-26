package com.yw.webplatform.mapper;

import java.util.List;

import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface AppEntityMapper extends IBaseMapper<AppEntity, String>{
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月16日上午10:11:33
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppEntity> findAppFunction(String fkAppLicenseTid) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月20日下午2:35:55
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer getCountAppFunction(String fkAppLicenseTid) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月21日下午1:12:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppEntity> findPlatformReport(AppEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 下午2:20:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<AppEntity> findApp(AppEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询app数量
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 下午2:20:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	public Integer getCountApp(AppEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询所有应用名称
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月6日 上午10:04:23
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<AppEntity> findAllAppName(AppEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询所有版本号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月7日下午3:10:11
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AppEntity> findVersionByAppId(AppEntity entity) throws Exception;
}
