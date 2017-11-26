package com.yw.appapi.mapper;

import org.springframework.stereotype.Repository;

import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.utils.mapper.IBaseMapper;

/**
 *<pre>
 * 功       能: ControlLogEntityMapper,对t_control_log表进行操作的接口（Dao）
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年6月29日下午2:16:18
 * Q    Q: 1784286916
 *</pre>
 */
@Repository
public interface ControlLogEntityMapper extends IBaseMapper<ControlLogEntity, String> {
	
	/*public abstract Integer insert(T entity) throws Exception;
	public abstract Integer delete(PK id) throws Exception;
	public abstract Integer updateById(T entity) throws Exception;
	public abstract List<T> findAll(T entity) throws Exception;
	public abstract Integer getCount(T entity) throws Exception;*/
	
	/**
	 * 
	*<pre>
	* 说      明:  获取t_auth_log表中的app_instance
	* @param token
	* @return String(appInstance)
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年6月30日下午4:33:40
	*</pre>
	 */
	public abstract String getAppInstance(String token) throws Exception;
	
	/**
	 * 
	*<pre>
	* 说      明:  判断数据表中appInstance是否已经存在
	* @param appInstance
	* @return Integer
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日上午9:37:30
	*</pre>
	 */
	public abstract Integer getCountOfAppInstance(String appInstance) throws Exception;
	
	/**
	 * 
	*<pre>
	* 说      明:  判断数据表中controlUuid是否已经存在
	* @param controlUuid
	* @return Integer
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日上午9:38:43
	*</pre>
	 */
	public abstract Integer getCountOfControlUuid(String controlUuid) throws Exception;
	/**
	 * 
	*<pre>
	* 说      明:  根据tid查找记录
	* @param tid
	* @return ControlLogEntity
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月3日上午9:39:29
	*</pre>
	 */
	public abstract ControlLogEntity findById(String tid) throws Exception;
}
