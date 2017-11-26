package com.yw.common.beansUtils.entity;

import com.yw.common.beansUtils.dto.ModelAttDto;
import org.hibernate.validator.constraints.Length;

/**
 * <pre>
 * 功       能: 设备型号与属性表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-23 14:55:49
 * Q    Q: 308053847
 * </pre>
 */
public class ModelAttEntity extends BaseEntity {

	private static final long serialVersionUID = -2991045099356L;

	@Length(min = 0, max = 50, message = "WEBPLATFORM.MODELATT.FKMODELTID")
	private String fkModelTid;// 型号外键ID
	@Length(min = 0, max = 50, message = "WEBPLATFORM.MODELATT.PROFILEID")
	private String profileId;// PROFILE_ID
	private String propName;//PROFILE名称


	/**** 以下是表中不存在的属性定义 *******************************************************************************/
	/**** V1.0.0版本 *******************************************************************************/
	private String attKey;//
	private String attValue;//

	// 无参构造方法
	public ModelAttEntity() {
		super();
	}

	// TID参数构造方法
	public ModelAttEntity(String tid) {
		super();
		this.setTid(tid);
	}

	public ModelAttEntity(String tid, String fkModelTid) {
		super();
		this.setTid(tid);
		this.setFkModelTid(fkModelTid);
	}

	public ModelAttEntity(String tid, String fkModelTid, String profileId) {
		super();
		this.setTid(tid);
		this.setFkModelTid(fkModelTid);
		this.setProfileId(profileId);
	}

	public ModelAttEntity(String tid, String fkModelTid, String profileId, String propName) {
		super();
		this.setTid(tid);
		this.fkModelTid = fkModelTid;
		this.profileId = profileId;
		this.propName = propName;
	}

	public ModelAttEntity(ModelAttDto data) {
		super();

		if (data != null) {

		}
	}

	public String getFkModelTid() {
		return fkModelTid;
	}

	public void setFkModelTid(String fkModelTid) {
		this.fkModelTid = fkModelTid;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getAttKey() {
		return attKey;
	}

	public void setAttKey(String attKey) {
		this.attKey = attKey;
	}

	public String getAttValue() {
		return attValue;
	}

	public void setAttValue(String attValue) {
		this.attValue = attValue;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

}
