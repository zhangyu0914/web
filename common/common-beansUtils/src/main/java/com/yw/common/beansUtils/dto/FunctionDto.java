package com.yw.common.beansUtils.dto;

import java.util.Map;

import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功   能: 权限表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class FunctionDto {

	private String functionName;// 权限名称
	private String uri;// ACTION
	private Integer functionLevel;// 菜单级别
	private Integer displayOrder;// 菜单显示
	private Integer functionType;// 菜单类型

	public FunctionDto() {
		super();
	}

	public FunctionDto(UsersFunctionsEntity data) {
		super();

		if (data != null) {

			this.setFunctionName(data.getFunctionName());
			this.setUri(data.getUri());
			this.setFunctionLevel(data.getFunctionLevel());
			this.setDisplayOrder(data.getDisplayOrder());
			this.setFunctionType(data.getFunctionType());
		}
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(Integer functionLevel) {
		this.functionLevel = functionLevel;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

}
