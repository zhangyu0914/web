package com.yw.common.beansUtils.utils.service;

import java.io.Serializable;

import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.result.ResultUtil;



/**
 *<pre>
 * 功       能: 
 * 涉及版本: V3.0.0 
 * 创  建  者: Vickey
 * 日       期: 2015-09-06 11:04:13
 * Q    Q: 308053847
 *</pre>
 */
public interface IBaseService <T, PK extends Serializable>{

	/**
	 *<pre>
	 * 说       明: 添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-7上午10:48:26
	 *</pre>
	 */
	public Integer insert(T entity) throws Exception;

	/**
	 *<pre>
	 * 说       明: 修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-7上午10:48:32
	 *</pre>
	 */
	public Integer update(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-7上午10:48:36
	 *</pre>
	 */
	public ResultUtil findPage(T entity, InterfacePage<T> page) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-15上午10:31:09
	 *</pre>
	 */
	public T findOne(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-15上午10:31:09
	 *</pre>
	 */
	public T findById(PK id) throws Exception;
}
