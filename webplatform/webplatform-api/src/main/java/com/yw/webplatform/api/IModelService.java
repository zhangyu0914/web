package com.yw.webplatform.api;

import java.util.List;

import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
public interface IModelService extends IBaseService<ModelEntity, String> {

	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil deleteModel(ModelEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 更新
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:11:22
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil updateModel(ModelEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月27日下午2:13:18
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil findAll(ModelEntity entity,String attId) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月5日下午8:43:48
	 * Q    Q: 308053847
	 * </pre>
	 */
	public List<ModelEntity> findAll(ModelEntity entity) throws Exception;
	
	/**
	 * <pre>
	 * 说       明: 查询型号
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 上午10:05:37
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil findAllModel(ModelEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:15:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	public Integer delete(String tid) throws Exception;

}
