package com.yw.common.beansUtils.utils.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.dao.IBaseDao;

/**
 *<pre>
 * 功   能: 基础DAO类，通过了一些常用的方法
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22上午11:53:40
 * Q  Q: 308053847
 *</pre>
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, PK extends Serializable> extends
		SqlSessionDaoSupport implements IBaseDao<T, PK> {

	/**查询所有*/
	public static final String MYBATIS_ID_FINDALL = "findAll";
	
	/**
	 * 根据ID查询对象
	 */
	public static final String MYBATIS_ID_FINDBYID = MYBATIS_ID_FINDALL;
	
	/**
	 * 保存对象
	 */
	public static final String MYBATIS_ID_INSERT = "insert";
	/**
	 * 修改对象
	 */
	public static final String MYBATIS_ID_UPDATEBYID = "updateById";
	/**
	 * 删除对象
	 */
	public static final String MYBATIS_ID_DELETE = "delete";
	/**
	 * 根据外键ID删除对象
	 */
	public static final String MYBATIS_ID_DELETE_FK = "deleteByFK";
	/**
	 * 根据条件统计集合
	 */
	public static final String MYBATIS_ID_COUNTPARAM = "countParam";
	
	/**
	 * 统计全部集合
	 */
	public static final String MYBATIS_ID_GETCOUNT= "getCount";
	/**
	 * 根据条件查询
	 */
	public static final String MYBATIS_ID_FINDBYALL = "findByAll";
	/**
	 * 批量删除
	 */
	public static final String MYBATIS_ID_BATCHDELETE= "batchDelete";
	/**
	 * 批量插入
	 */
	public static final String MYBATIS_ID_BATCHINSERT= "batchInsert";
	/**
	 * 命名空间
	 */
	private String sqlmapNamespace = "";

	public String getSqlmapNamespace() {
		return sqlmapNamespace;
	}

	public void setSqlmapNamespace(String sqlmapNamespace) {
		this.sqlmapNamespace = sqlmapNamespace;
	}

	protected  BaseDaoImpl(String sqlmapNamespace){
		this.sqlmapNamespace = sqlmapNamespace;
	}
	/**
	 *<pre>
	 * 说   明: 根据ID查询对象
	 * @param entity
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:13
	 *</pre>
	 */
	@Override
	public T findById(T entity) {
		return (T) this.getSqlSession().selectOne(sqlmapNamespace + MYBATIS_ID_FINDBYID,	entity);
	}

	/**
	 *<pre>
	 * 说   明:  保存实体
	 * @param entity
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:22
	 *</pre>
	 */
	@Override
	public int insert(T entity) {
		return this.getSqlSession().insert(sqlmapNamespace + MYBATIS_ID_INSERT, entity);
	}
	
	@Override
	public int insertCustomer(T entity, String nameSpace) throws Exception {
		return this.getSqlSession().insert(sqlmapNamespace + nameSpace, entity);
	}

	/**
	 *<pre>
	 * 说   明: 删除实体
	 * @param id
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:29
	 *</pre>
	 */
	@Override
	public int delete(PK id) {
		return this.getSqlSession().delete(sqlmapNamespace + MYBATIS_ID_DELETE, id);
	}
	
	@Override
	public int delete(T entity) {
		return this.getSqlSession().delete(sqlmapNamespace + MYBATIS_ID_DELETE, entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 自定义删除方法
	 * @param entity
	 * @param nameSpace
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-20下午3:08:58
	 *</pre>
	 */
	@Override
	public int deleteCustomer(T entity, String nameSpace) throws Exception {
		return this.getSqlSession().delete(sqlmapNamespace + nameSpace, entity);
	}
	
	@Override
	public int deleteCustomer(String tid, String nameSpace) throws Exception {
		return this.getSqlSession().delete(sqlmapNamespace + nameSpace, tid);
	}
	
	/**
	 *<pre>
	 * 说   明: 删除实体
	 * @param id
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:29
	 *</pre>
	 */
	@Override
	public int deleteByFK(T entity) {
		return this.getSqlSession().delete(sqlmapNamespace + MYBATIS_ID_DELETE_FK, entity);
	}

	/**
	 *<pre>
	 * 说   明:  更新实体
	 * @param entity
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:38
	 *</pre>
	 */
	@Override
	public Integer updateById(T entity) {
        return this.getSqlSession().update(sqlmapNamespace+MYBATIS_ID_UPDATEBYID, entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param space
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-11下午1:08:27
	 *</pre>
	 */
	@Override
	public Integer updateById(T entity, String space) throws Exception {
		return this.getSqlSession().update(sqlmapNamespace+space, entity);
	}

	/**
	 *<pre>
	 * 说   明: 根据条件查询集合
	 * @param page
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:46
	 *</pre>
	 */
	@Override
	public List<T> findPage(T entity, InterfacePage page) {
		RowBounds rowBounds = new RowBounds(page.getStart() + page.getOffset(), page.getPageSize());
		return this.getSqlSession().selectList(sqlmapNamespace+MYBATIS_ID_FINDALL, entity, rowBounds);
	}
	
	@Override
	public List<T> findPage(T entity, InterfacePage page, String sqlId) {
		RowBounds rowBounds = new RowBounds(page.getStart() + page.getOffset(), page.getPageSize());
		return this.getSqlSession().selectList(sqlmapNamespace+sqlId, entity, rowBounds);
	}
	
	@Override
	public List<T> findPage(Map map, InterfacePage page, String sqlId) {
		RowBounds rowBounds = new RowBounds(page.getStart() + page.getOffset(), page.getPageSize());
		return this.getSqlSession().selectList(sqlmapNamespace+sqlId, map, rowBounds);
	}
	

	/**
	 *<pre>
	 * 说   明: 根据条件统计集合
	 * @param page
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:54:54
	 *</pre>
	 */
	@Override
	public Integer countParam(InterfacePage page) {
		return (Integer) this.getSqlSession().selectOne(sqlmapNamespace+MYBATIS_ID_COUNTPARAM, page);
	}

	/**
	 *<pre>
	 * 说   明: 查询所有
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:55:01
	 *</pre>
	 */
	@Override
	public  List<T> findAll(T entity) throws Exception{
		return this.getSqlSession().selectList(sqlmapNamespace+MYBATIS_ID_FINDALL, entity);
	}
	
	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param entity
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:01:16
	 *</pre>
	 */
	@Override
	public List<T> findCustom(T entity, String sqlId) {
		return this.getSqlSession().selectList(sqlmapNamespace + sqlId, entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-11-4下午5:04:20
	 *</pre>
	 */
	@Override
	public T findCustomForOne(T entity, String sqlId) {
		return this.getSqlSession().selectOne(sqlmapNamespace + sqlId, entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param str
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-5上午11:09:08
	 *</pre>
	 */
	@Override
	public T findCustomForOne(String str, String sqlId) {
		return this.getSqlSession().selectOne(sqlmapNamespace + sqlId, str);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param map
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-5上午11:09:13
	 *</pre>
	 */
	@Override
	public T findCustomForOne(Map map, String sqlId) {
		return this.getSqlSession().selectOne(sqlmapNamespace + sqlId, map);
	}
	
	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param entity
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:01:16
	 *</pre>
	 */
	@Override
	public List<T> findCustom(Map map, String sqlId) {
		return this.getSqlSession().selectList(sqlmapNamespace + sqlId, map);
	}
	
	/**
	 *<pre>
	 * 说   明:  查询指定的SQL
	 * @param str
	 * @param sqlId
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-20上午11:04:14
	 *</pre>
	 */
	@Override
	public List<T> findCustom(String str, String sqlId) {
		return this.getSqlSession().selectList(sqlmapNamespace + sqlId, str);
	}
	
	@Override
	public List<T> findCustom(Integer integer, String sqlId) {
		return this.getSqlSession().selectList(sqlmapNamespace + sqlId, integer);
	}
	
	/**
	 *<pre>
	 * 说   明: 查询所有统计
	 * @return
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:55:10
	 *</pre>
	 */
	@Override
	public Integer getCount(T entity) {
		Integer countObj = (Integer) this.getSqlSession().selectOne(sqlmapNamespace+MYBATIS_ID_GETCOUNT, entity);
		if(null == countObj)
			return 0;
		return countObj;
	}
	
	@Override
	public Integer getCountCustomer(T t, String sqlId) {
		return (Integer) this.getSqlSession().selectOne(sqlmapNamespace+sqlId, t);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param entity
	 * @param sqlId
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-11-28上午11:12:44
	 *</pre>
	 */
	@Override
	public Integer getCountCustom(T entity, String sqlId) {
		return this.getSqlSession().selectOne(sqlmapNamespace + sqlId, entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param map
	 * @param sqlId
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-3下午6:46:13
	 *</pre>
	 */
	@Override
	public Integer getCountCustom(Map map, String sqlId) {
		return this.getSqlSession().selectOne(sqlmapNamespace + sqlId, map);
	}


	/**
	 *<pre>
	 * 说   明: 批量删除
	 * @param list
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:55:17
	 *</pre>
	 */
	@Override
	public int batchDelete(List<PK> list) {
	 	return this.getSqlSession().delete(sqlmapNamespace+MYBATIS_ID_BATCHDELETE, list);
	}

	/**
	 *<pre>
	 * 说   明: 批量插入
	 * @param list
	 * 创建者: 陈林林(Vickey)
	 * 日   期: 2014-6-22上午11:55:25
	 *</pre>
	 */
	@Override
	public int batchInsert(List<T> list) {
		return this.getSqlSession().insert(sqlmapNamespace+MYBATIS_ID_BATCHINSERT, list);
	}
}
