package com.yw.common.api;

import java.util.List;

import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.entity.importFile.GetMwConfigEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:37
 * Q    Q: 308053847
 *</pre>
 */
public interface IMwconfigService extends IBaseService<MwconfigEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月27日下午5:39:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateMwConfig(MwconfigEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月27日下午5:44:56
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil mwConfigGet(GetMwConfigEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月19日 下午4:57:20
	 * Q     Q: 982234234
	 * </pre>
	 */
	public List<MwconfigEntity> mwConfigList(MwconfigEntity entity) throws Exception;

}
