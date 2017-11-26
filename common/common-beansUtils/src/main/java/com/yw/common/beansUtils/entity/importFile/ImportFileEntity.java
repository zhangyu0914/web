package com.yw.common.beansUtils.entity.importFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入应用定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportFileEntity {

	private static final long serialVersionUID = -2986347222798L;
	private List<ImportModelEntity> model_info;//
	private List<ImportAppEntity> app_info;//
	private List<ImportLicenseEntity> license_info;//

	public ImportFileEntity() {
		super();
	}

	public List<ImportModelEntity> getModel_info() {
		return model_info;
	}

	public void setModel_info(List<ImportModelEntity> model_info) {
		this.model_info = model_info;
	}

	public List<ImportAppEntity> getApp_info() {
		return app_info;
	}

	public void setApp_info(List<ImportAppEntity> app_info) {
		this.app_info = app_info;
	}

	public List<ImportLicenseEntity> getLicense_info() {
		return license_info;
	}

	public void setLicense_info(List<ImportLicenseEntity> license_info) {
		this.license_info = license_info;
	}

}
