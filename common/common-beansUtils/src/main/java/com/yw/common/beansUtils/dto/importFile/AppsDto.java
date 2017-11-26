package com.yw.common.beansUtils.dto.importFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.entity.importFile.AppsEntity;
import com.yw.common.beansUtils.entity.importFile.DevicesEntity;

/**
 * <pre>
 * 功       能: 导入许可证应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class AppsDto {

	private static final long serialVersionUID = -2986347222798L;

	private List<String> lic_ids;//App实例许可列表,每个app实例需要一个实例许可ID
	private String app_id;//应用ID

	public AppsDto() {
		super();
	}

	public AppsDto(String app_id, List<String> lic_ids) {
		super();
		this.app_id = app_id;
		this.lic_ids = lic_ids;
	}

	
	public List<String> getLic_ids() {
		return lic_ids;
	}

	public void setLic_ids(List<String> lic_ids) {
		this.lic_ids = lic_ids;
	}

	public void setLic_idsObj(String... str) {
		this.lic_ids = Arrays.asList(str);
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

}
