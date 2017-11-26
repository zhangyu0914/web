package com.yw.common.beansUtils.utils.mapper;

import java.io.Serializable;
import java.util.List;

import com.yw.common.beansUtils.utils.InterfacePage;

/**
 * <pre>
 * 功       能: 基础MAPPER类，通过了一些常用的方法
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-7上午10:01:50
 * Q    Q: 308053847
 * </pre>
 */
public abstract interface IBaseMapper<T, PK extends Serializable> {

	public abstract Integer insert(T entity) throws Exception;

	public abstract Integer delete(PK id) throws Exception;

	public abstract Integer updateById(T entity) throws Exception;

	public abstract List<T> findAll(T entity) throws Exception;
	
	public abstract Integer getCount(T entity) throws Exception;
}