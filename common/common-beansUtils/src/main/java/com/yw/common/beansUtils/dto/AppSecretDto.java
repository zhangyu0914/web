package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.AppAccountDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.BaseEntity;

/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-21 15:07:34
 * Q    Q: 308053847
 * </pre>
 */
public class AppSecretDto{

	private static final long serialVersionUID = -2985516909312L;

	private String app_instance_id;// 应用ID
	private String secret;// 应用密钥

	// 无参构造方法
	public AppSecretDto() {
		super();
	}

	public AppSecretDto(AppAccountEntity data) {
		if (data != null) {
			this.setApp_instance_id(data.getAppInstance());
			this.setSecret(data.getAppKey());
		}
	}
	
	public AppSecretDto(String app_instance_id, String secret) {
		super();
		this.app_instance_id = app_instance_id;
		this.secret = secret;
	}

	public String getApp_instance_id() {
		return app_instance_id;
	}

	public void setApp_instance_id(String app_instance_id) {
		this.app_instance_id = app_instance_id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
