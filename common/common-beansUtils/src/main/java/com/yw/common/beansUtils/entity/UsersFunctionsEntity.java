package com.yw.common.beansUtils.entity;

import javax.validation.constraints.Size;

import com.yw.common.beansUtils.dto.UsersFunctionsDto;

/**
 * <pre>
 * 功   能: 权限表
 * 创建者: Vickey
 * 日   期: 2015-06-09 10:37:37
 * Q  Q: 308053847
 * </pre>
 */
public class UsersFunctionsEntity extends BaseEntity {

	private static final long serialVersionUID = -2867634914578L;
	
	@Size(min=0, max=100, message = "权限编码数据长度过长")
	private String functionCode;// 权限编码
	@Size(min=0, max=100, message = "权限名称数据长度过长")
	private String functionName;// 权限名称
	@Size(min=0, max=1000, message = "ACTION数据长度过长")
	private String uri;// ACTION
	private Integer functionLevel;// 菜单级别
	@Size(min=0, max=100, message = "权限编码数据长度过长")
	private String parentCode;// 权限编码
	private Integer displayOrder;// 菜单显示
	private Integer functionType;// 菜单类型
	private Integer functionSeq;// 显示顺序
	@Size(min=0, max=100, message = "菜单说明数据长度过长")
	private String functionDesc;// 菜单说明
	
	/****表中不存在的属性定义在这里*******************************************************************************/
	private UsersFunctionsEntity parentEntity;	//
	private String parentFunctionCode;
	private String oldParentFunctionCode;
	private String functionCodeTemp;			//
	private Integer type;			//

	public UsersFunctionsEntity() {
		super();
	}
	
	public UsersFunctionsEntity(UsersFunctionsDto data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setFunctionCode(data.getFunctionCode());
			this.setFunctionName(data.getFunctionName());
			this.setUri(data.getUri());
			this.setFunctionLevel(data.getFunctionLevel());
			this.setParentCode(data.getParentCode());
			this.setDisplayOrder(data.getDisplayOrder());
			this.setFunctionType(data.getFunctionType());
			this.setFunctionSeq(data.getFunctionSeq());
			this.setFunctionDesc(data.getFunctionDesc());

		}
	}
	
	
	public UsersFunctionsEntity(Integer functionType, String functionCodeTemp) {
		this.setFunctionType(functionType);
		this.setFunctionCodeTemp(functionCodeTemp);
	}
	
	
	public UsersFunctionsEntity(String functionCode, String functionName,
			String uri) {
		super();
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.uri = uri;
	}

	public UsersFunctionsEntity(String tid) {
		this.setTid(tid);
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

	public Integer getFunctionSeq() {
		return functionSeq;
	}

	public void setFunctionSeq(Integer functionSeq) {
		this.functionSeq = functionSeq;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getParentFunctionCode() {
		return parentFunctionCode;
	}

	public void setParentFunctionCode(String parentFunctionCode) {
		this.parentFunctionCode = parentFunctionCode;
	}

	public String getOldParentFunctionCode() {
		return oldParentFunctionCode;
	}

	public void setOldParentFunctionCode(String oldParentFunctionCode) {
		this.oldParentFunctionCode = oldParentFunctionCode;
	}

	public String getFunctionCodeTemp() {
		return functionCodeTemp;
	}

	public void setFunctionCodeTemp(String functionCodeTemp) {
		this.functionCodeTemp = functionCodeTemp;
	}

	public UsersFunctionsEntity getParentEntity() {
		return parentEntity;
	}

	public void setParentEntity(UsersFunctionsEntity parentEntity) {
		this.parentEntity = parentEntity;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
