package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:23:42
 * Q    Q: 308053847
 * </pre>
 */
public class ModelDto extends BaseDto {

	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String modelName;// 型号名称
	private String modelNo;// 型号
	private String modelCode; //型号编码
	private String remark;// 备注
	private boolean checked; // 默认选中，用于后台表与表关联时，修改功能默认选中数据
	private String tradeName;// 厂商名称
	private String tradeCode;// 厂商代码
	private Integer modelVersion;// 型号版本号
	
	public ModelDto() {
		super();
	}

	public ModelDto(ModelEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setModelName(data.getModelName());
			this.setModelNo(data.getModelNo());
			this.setModelCode(data.getModelCode());
			this.setRemark(data.getRemark());
			this.setChecked(data.getChecked());
			this.setTradeCode(data.getTradeCode());
			this.setTradeName(data.getTradeName());
			this.setModelVersion(data.getModelVersion());
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

}
