package com.yw.webplatform.api;

import java.util.Map;

import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;



/**
 *<pre>
 * 功       能: 导入
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
public interface IImportService{

	/**
	 * <pre>
	 * 说       明: 导入型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月22日下午2:13:16
	 * Q    Q: 308053847
	 * </pre>
	 * @param batNo TODO
	 */
	public ResultUtil importModel(String content, String batNo) throws Exception;

	/**
	 * <pre>
	 * 说       明: 导入应用
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午4:10:50
	 * Q    Q: 308053847
	 * </pre>
	 * @param batNo TODO
	 */
	public ResultUtil importApp(String content, String batNo) throws Exception;

	/**
	 * <pre>
	 * 说       明: 导入许可证
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月25日上午9:59:57
	 * Q    Q: 308053847
	 * </pre>
	 * @param batNo TODO
	 * @param modelMap TODO
	 */
	public ResultUtil importLicense(String content, String batNo, Map<String, ModelEntity> modelMap) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月27日下午3:34:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil importProfile(String content) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月5日下午6:06:24
	 * Q    Q: 308053847
	 * </pre>
	 * @param batNo TODO
	 */
	public ResultUtil importProfileFile(String filePath, String batNo) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月8日上午11:19:42
	 * Q    Q: 308053847
	 * </pre>
	 * @param batNo TODO
	 * @param zipPassword TODO
	 */
	public ResultUtil importFile(String path, String batNo, String zipPassword) throws Exception;


}
