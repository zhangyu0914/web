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
public class UsersFunctionsDto  extends BaseDto {

	
	private String tid;//主键
	private String functionCode;// 权限编码
	private String functionName;// 权限名称
	private String uri;// ACTION
	private Integer functionLevel;// 菜单级别
	private String parentCode;// 权限编码
	private Integer displayOrder;// 菜单显示
	private Integer functionType;// 菜单类型
	private Integer functionSeq;// 显示顺序
	private String functionDesc;// 菜单说明
	private boolean checked; // 默认选中，用于后台表与表关联时，修改功能默认选中数据
	

	public UsersFunctionsDto() {
		super();
	}
	
	public UsersFunctionsDto(UsersFunctionsEntity data) {
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
			this.setChecked(data.getChecked());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
