package com.yw.common.beansUtils.utils.enums;

/**
 * <pre>
 * 功       能: 系统配置
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月23日上午10:41:19
 * Q    Q: 308053847
 * </pre>
 */
public enum SystemConfigEnum {
	
	IS_DOCKER("环境判断"),
	
	YW_DEVMGR_PORT_9002_TCP_ADDR("控制配置"),
	YW_LOG_PORT_8060_TCP_ADDR("日志配置"),
	YW_REDIS_PORT_6379_TCP_ADDR("REDIS配置"),
	YW_MYSQL_PORT_3306_TCP_ADDR("数据库配置"),
	YW_MQTT_PORT_8060_TCP_ADDR("MQTT配置"),
	YW_PROJECT_MODULE_PORT_8060_TCP_ADDR("日志模块配置"),
	YW_DATA_LOG_PORT_8060_TCP_ADDR("数据日志配置"),
	YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR("原始数据日志配置"),
	;

	private String name;
	private Integer code;

	SystemConfigEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getTypeName(Integer code) {
		if (code == null) {
			return null;
		}
		return null;
	}
}
