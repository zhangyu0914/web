package com.yw.common.beansUtils.entity.importFile;


/**
 * <pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ImportModelConfigEntity {

	private static final long serialVersionUID = -2986347222798L;

	private String[] flows;// 选项

	public ImportModelConfigEntity() {
		super();
	}

	public String[] getFlows() {
		return flows;
	}

	public void setFlows(String[] flows) {
		this.flows = flows;
	}
}
