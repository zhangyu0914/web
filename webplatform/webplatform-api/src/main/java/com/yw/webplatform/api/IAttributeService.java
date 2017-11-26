package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
public interface IAttributeService extends IBaseService<AttributeEntity, String> {

	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil deleteAttribute(AttributeEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 添加
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:11:05
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil insertAttribute(AttributeEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 更新
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:11:22
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil updateAttribute(AttributeEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月15日 上午9:40:23
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil findAll(AttributeEntity entity,String modelId) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月5日下午7:37:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AttributeEntity> findAll(AttributeEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:22:53
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer delete(String tid) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:23:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<AttributeEntity> findByProfileId(AttributeEntity entity) throws Exception;
}
