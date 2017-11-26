package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.ConfigurationEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 字典数据表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 13:17:22
 * Q    Q: 308053847
 *</pre>
 */
public class ConfigurationDto extends BaseDto{

	
	private String tid;//主键
	private Integer versionInt;// 版本号
	private String dictionaryKey;// 字典键
	private String dictionaryValue;// 字典值
	private String remark;// 备注说明


	public ConfigurationDto() {
		super();
	}
	
	public ConfigurationDto(ConfigurationEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setVersionInt(data.getVersionInt());
			this.setDictionaryKey(data.getKey());
			this.setDictionaryValue(data.getValue());
			this.setRemark(data.getRemark());
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

	public Integer getVersionInt() {
		return versionInt;
	}

	public void setVersionInt(Integer versionInt) {
		this.versionInt = versionInt;
	}

	public String getDictionaryKey() {
		return dictionaryKey;
	}

	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}

	public String getDictionaryValue() {
		return dictionaryValue;
	}

	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
