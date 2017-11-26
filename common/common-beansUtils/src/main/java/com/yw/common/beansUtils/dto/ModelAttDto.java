package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 设备型号与属性表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-23 14:55:49
 * Q    Q: 308053847
 *</pre>
 */
public class ModelAttDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String fkModelTid;// 型号外键ID
	private String profileId;// 属性外键ID
	public ModelAttDto() {
		super();
	}
	
	public ModelAttDto(ModelAttEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setFkModelTid(data.getFkModelTid());
			this.setProfileId(data.getProfileId());
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
	
}
