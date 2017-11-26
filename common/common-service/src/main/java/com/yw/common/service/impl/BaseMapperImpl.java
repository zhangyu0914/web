package com.yw.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.yw.common.mapper.ConfigurationEntityMapper;
import com.yw.common.mapper.MwconfigEntityMapper;



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
	public ConfigurationEntityMapper configurationEntityMapper;//字典数据表
	@Autowired
	public MwconfigEntityMapper mwconfigEntityMapper;//配置表
}