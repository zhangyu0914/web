package com.yw.common.beansUtils.entity.importFile;

import java.util.Map;

/**
 * <pre>
 * 功       能: 推送型号数据实体
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class PushDataEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String name;// 配置名称
	private Map data;// 配置类型

	public PushDataEntity() {
		super();
	}

	public PushDataEntity(String name, Map data) {
		super();
		this.name = name;
		this.data = data;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

}
