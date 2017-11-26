package com.yw.common.beansUtils.entity.importFile;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入许可证设备定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class DevicesEntity {

	private static final long serialVersionUID = -2986347222798L;

	private List<String> sn_list;//设备序列号
	private String model_id;//型号ID
	private String snStr;//型号ID

	public DevicesEntity() {
		super();
	}

	public List<String> getSn_list() {
		return sn_list;
	}

	public void setSn_list(List<String> sn_list) {
		this.sn_list = sn_list;
	}
	
	public void setSn_list(String... obj) {
		this.sn_list = Arrays.asList(obj);
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public String getSnStr() {
		return snStr;
	}

	public void setSnStr(String snStr) {
		this.snStr = snStr;
	}
	
}
