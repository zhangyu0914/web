package com.yw.appapi.api;

import java.util.List;

import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
/**
 * 
 *<pre>
 * 功       能: 定义服务接口
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年6月30日下午4:27:53
 * Q    Q: 1784286916
 *</pre>
 */
public interface IControlLogService {
	
	/**
	 * 
	*<pre>
	* 说      明:  将参数和返回值保存到t_control_log
	* @param args,result,token
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年6月30日下午4:35:22
	*</pre>
	 */
	public AppResultUtil insert(Object[] args, Object result, String token) throws Exception;
	
	/**
	 * 
	*<pre>
	* 说      明:  根据条件查找t_control_log表信息
	* @param entity
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午8:09:36
	*</pre>
	 */
	public List<ControlLogEntity> findAll(ControlLogEntity entity) throws Exception;
	
	/**
	 * 
	*<pre>
	* 说      明:  根据条件获取表中记录数
	* @param entity
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午11:17:41
	*</pre>
	 */
	public AppResultUtil getCount(ControlLogEntity entity) throws Exception;
	
	/**
	 * 
	*<pre>
	* 说      明:  根据主键删除记录
	* @param 
	* @return 
	* @throws 
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午11:20:34
	*</pre>
	 */
	public AppResultUtil delete(String tid) throws Exception;
	/**
	 * 
	*<pre>
	* 说      明:  根据主键更新数据
	* @param tid
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月2日下午10:11:40
	*</pre>
	 */
	public AppResultUtil updateById(ControlLogEntity entity) throws Exception;
}
