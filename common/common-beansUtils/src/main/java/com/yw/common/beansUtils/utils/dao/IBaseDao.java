package com.yw.common.beansUtils.utils.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.utils.InterfacePage;

/**
 *<pre>
 * 功   能: 基础DAO类，通过了一些常用的方法
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22上午11:49:14
 * Q  Q: 308053847
 *</pre>
 */
public abstract interface IBaseDao<T, PK extends Serializable> {
	enum Stack {
		MAX, MIN, AVG, SUM;
	}

	/**
	 *<pre>
	 * 说   明:  保存对象
	 * @param entity
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:49:41
	 *</pre>
	 * @return 
	 */
	public abstract int insert(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 指定添加SQL语句
	 * @param entity
	 * @param nameSpace
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午6:04:10
	 *</pre>
	 */
	public abstract int insertCustomer(T entity, String nameSpace) throws Exception;

	/**
	 *<pre>
	 * 说   明: 删除对象
	 * @param id
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:49:49
	 *</pre>
	 * @return 
	 */
	public abstract int delete(PK id) throws Exception;

	/**
	 *<pre>
	 * 说   明: 修改对象
	 * @param entity
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:01
	 *</pre>
	 */
	public abstract Integer updateById(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param space
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-11下午4:43:16
	 *</pre>
	 */
	public Integer updateById(T entity, String space) throws Exception;

	/**
	 *<pre>
	 * 说   明: 根据ID查询对象
	 * @param entity
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:08
	 *</pre>
	 */
	public abstract T findById(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说   明: 根据条件查询集合
	 * @param entity 
	 * @param page
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:15
	 *</pre>
	 */
	public abstract List<T> findPage(T entity, InterfacePage page) throws Exception;

	/**
	 *<pre>
	 * 说   明: 根据条件统计集合
	 * @param page
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:22
	 *</pre>
	 */
	public abstract Integer countParam(InterfacePage page) throws Exception;

	/**
	 *<pre>
	 * 说   明: 查询所有
	 * @param entity 
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:31
	 *</pre>
	 * @throws Exception 
	 */
	public abstract List<T> findAll(T entity) throws Exception;

	/**
	 *<pre>
	 * 说   明: 统计所有
	 * @param entity 
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:38
	 *</pre>
	 */
	public abstract Integer getCount(T entity) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param t
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-30下午6:45:06
	 *</pre>
	 */
	public Integer getCountCustomer(T t, String sqlId);
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-11-28上午11:12:37
	 *</pre>
	 */
	public abstract Integer getCountCustom(T entity, String sqlId);
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param map
	 * @param sqlId
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-3下午6:46:01
	 *</pre>
	 */
	public abstract Integer getCountCustom(Map map, String sqlId);

	/**
	 *<pre>
	 * 说   明: 批量删除
	 * @param list
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:47
	 *</pre>
	 * @return 
	 */
	public abstract int batchDelete(final List<PK> list) throws Exception;

	/**
	 *<pre>
	 * 说   明: 批量插入
	 * @param list
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:54
	 *</pre>
	 * @return 
	 */
	public abstract int batchInsert(final List<T> list) throws Exception;

	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param entity
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:01:01
	 *</pre>
	 */
	public abstract List<T> findCustom(T entity, String sqlId);
	
	public abstract T findCustomForOne(T entity, String sqlId);

	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param str
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:04:05
	 *</pre>
	 */
	public abstract List<T> findCustom(String str, String sqlId);

	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param integer
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:04:39
	 *</pre>
	 */
	public abstract List<T> findCustom(Integer integer, String sqlId);

	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param map
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-10-12下午9:10:44
	 *</pre>
	 */
	public abstract List<T> findCustom(Map map, String sqlId);
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param str
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-5上午11:09:21
	 *</pre>
	 */
	public abstract T findCustomForOne(String str, String sqlId);

	/**
	 *<pre>
	 * 说       明: 
	 * @param map
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-5上午11:09:30
	 *</pre>
	 */
	public abstract T findCustomForOne(Map map, String sqlId);

	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-22下午3:27:47
	 *</pre>
	 */
	public abstract int delete(T entity);
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param nameSpace
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-20下午3:38:18
	 *</pre>
	 */
	public abstract int deleteCustomer(T entity, String nameSpace) throws Exception;
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param tid
	 * @param nameSpace
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-30下午6:44:07
	 *</pre>
	 */
	public abstract int deleteCustomer(String tid, String nameSpace) throws Exception;
	
	/**
	 *<pre>
	 * 说   明: 根据外键删除数据
	 * @param list
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:50:54
	 *</pre>
	 * @return 
	 */
	public abstract int deleteByFK(T entity) throws Exception;

	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param page
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-25下午5:33:08
	 *</pre>
	 */
	public abstract List<T> findPage(T entity, InterfacePage page, String sqlId);
	
	public abstract List<T> findPage(Map map, InterfacePage page, String sqlId);
}