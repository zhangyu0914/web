package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 调用接口记录
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IInterfaceLogService extends IBaseService<InterfaceLogEntity, String> {
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午1:17:58
	 * Q     Q: 982234234
	 * </pre>
	 */
	public AppResultUtil interfaceLog(InterfaceLogEntity entity,String token,String action) throws Exception;
}
