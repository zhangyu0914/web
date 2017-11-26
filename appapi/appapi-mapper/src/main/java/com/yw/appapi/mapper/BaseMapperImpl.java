package com.yw.appapi.mapper;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * <pre>
 * 功       能: 基础MAPPER
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月23日下午3:07:45
 * Q    Q: 308053847
 * </pre>
 */
public class BaseMapperImpl {

	@Autowired
	public PlatformAppEntityMapper platformAppEntityMapper;//平台提供APP访问接口
	
	@Autowired
	public ControlLogEntityMapper controlLogEntityMapper;//记录参数和返回值的数据表访问接口
	
}