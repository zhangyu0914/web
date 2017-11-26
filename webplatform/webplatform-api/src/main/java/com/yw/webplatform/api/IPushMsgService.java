package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.PushMsgEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 推送消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IPushMsgService extends IBaseService<PushMsgEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月14日下午4:43:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil pushMQTT(PushMQTTEntity entity) throws Exception;

}
