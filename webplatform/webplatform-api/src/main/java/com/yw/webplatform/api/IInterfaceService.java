package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:14
 * Q    Q: 308053847
 *</pre>
 */
public interface IInterfaceService extends IBaseService<InterfaceEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月24日下午3:02:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertInterface(InterfaceEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 更新
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午4:29:36
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil updateInterface(InterfaceEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午4:29:55
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil deleteInterface(InterfaceEntity entity) throws Exception;

}
