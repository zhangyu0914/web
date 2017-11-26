package com.yw.common.beansUtils.dto.importFile;

import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;

/**
 * <pre>
 * 功       能: 导入应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class AppInfoDto {

	private static final long serialVersionUID = -2986347222798L;

	private String id;// 平台应用实例ID
	private ImportAppDto app_info;// 应用详情

	public AppInfoDto() {
		super();
	}

	public AppInfoDto(ImportAppEntity data) {
		this.setId(data.getInstance_id());
		this.setApp_info(new ImportAppDto(data));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImportAppDto getApp_info() {
		return app_info;
	}

	public void setApp_info(ImportAppDto app_info) {
		this.app_info = app_info;
	}

}
