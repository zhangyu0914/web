package com.yw.common.beansUtils.utils.enums;

import com.yw.common.beansUtils.entity.ConfigurationEntity;


/**
 * <pre>
 * 功       能: 配置表初始化数据功能
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-6-26下午5:57:17
 * Q    Q: 308053847
 * </pre>
 */
public enum ConfigurationEnum implements ConfigurationEnumValue {
	
	UPLOAD_FILE_IMAGES_SUFFIX,//
	UPLOAD_FILE_VIDEOS_SUFFIX,//
	UPLOAD_FILE_FILES_SUFFIX,
	UPLOAD_FILE_MAX_SIZE,
	UPLOAD_FILE_OUTER_NET,
	UPLOAD_FILE_REAL_DIR,
	UPLOAD_FILE_STAMP,
	
	REDIS_TIMEOUT,//REDIS超时时间
	
	PAGE_SIZE,
	LICENSE_DEVICE_TIMEOUT_DAY,//设备许可证到期
	DOCKER_DB_USERNAME,//DOCKER数据库用户名
	DOCKER_DB_PASSWORD,//DOCKER数据库密码
	
	APP_KEY_MAX_COUNT,//应用密钥长度
	AUTH_TOKEN_HOUR,//应用认证延时小时数
	DEBUG_MODEL,//环境模式，0：生产，1：调试
	SERVER_URL,//C++服务
	
	FILE_PATH,//文件目录
	LOG_URL,//日志URL
	PUSHMSG_URL,//URL
	PROJECT_MODULE_URL,//获取项目和项目模块的接口url
	DATA_LOG_URL,//数据日志查询的接口url
	RAW_DATA_LOG_URL,//原始数据日志查询的接口url
	LICENSE_AES_KEY,//许可证AES密钥
	PID,//平台ID
	;

	private String key;// KEY
	private String value;// 值
	private ConfigurationEntity configuration;// 对象

	private ConfigurationEnum() {

	}

	private ConfigurationEnum(ConfigurationEntity sysConfiguration, String value) {
		this.configuration = sysConfiguration;
		this.value = value;
	}

	@Override
	public ConfigurationEntity getConfiguration() {// 从REDIS获取最新值
		return this.configuration;
	}

	@Override
	public void setConfiguration(ConfigurationEntity configuration) {
		this.configuration = configuration;
	}

	@Override
	public String getValue() {// 从REDIS获取最新值
		return this.value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}
}