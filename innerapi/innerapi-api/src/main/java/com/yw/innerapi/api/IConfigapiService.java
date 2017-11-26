package com.yw.innerapi.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AppInstanceDevEntity;
import com.yw.common.beansUtils.entity.ConfigapiEntity;
import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppSecretEntity;
import com.yw.common.beansUtils.entity.importFile.GetLicenseEntty;
import com.yw.common.beansUtils.entity.importFile.GetModelEntity;
import com.yw.common.beansUtils.entity.importFile.GetProfileEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13:01:21
 * Q    Q: 308053847
 *</pre>
 */
public interface IConfigapiService extends IBaseService<ConfigapiEntity, String> {

	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:27
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil modelGet(GetModelEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 获取应用配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月24日 下午3:04:59
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil appInstanceGet(GetAppEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 删除许可证
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:47:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil removeLicense(String id) throws Exception;

	/**
	 * <pre>
	 * 说       明: 删除属性
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:50:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil removeProfile(String profileId) throws Exception;


	/**
	 * <pre>
	 * 说       明: 删除型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午11:57:51
	 * Q    Q: 308053847
	 * </pre>
	 * @param version TODO
	 */
	public AppResultUtil removeModel(String id, Integer version) throws Exception;

	/**
	 * <pre>
	 * 说       明: 获取PROFILE
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日下午1:47:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil profileGet(GetProfileEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日下午2:29:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil licenseGet(GetLicenseEntty id) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日下午5:23:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appInstanceDevGet(AppInstanceDevEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 初始化数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月26日上午10:54:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil initData(String type) throws Exception;

	/**
	 * <pre>
	 * 说       明: 删除应用
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月26日上午10:53:57
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AppResultUtil removeAppInstance(String id) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月13日下午3:33:20
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil appSecretGet(GetAppSecretEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月15日下午3:06:15
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deviceStatusAdd(PushMQTTEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:20:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ImportLicenseEntity> findExLicense() throws Exception;

	/**
	 * <pre>
	 * 说       明: NODEJS推送I/O、平台数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月3日下午4:25:31
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil ioAdd() throws Exception;

	/**
	* <pre>
	* 说       明: 根据sn更新设备状态（t_device）
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月4日下午2:02:12
	* Q    Q: 17789861157
	* </pre>
	*/
	public ResultUtil devicePut() throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 说       明: 新增警告接口
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月5日下午2:40:22
	 * Q    Q: 17789861157
	 * </pre>
	 */
	public ResultUtil warningSet() throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年8月22日上午9:24:47
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil testJava(WarningEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年8月24日下午3:25:42
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deviceStatusPlan() throws Exception;
}
