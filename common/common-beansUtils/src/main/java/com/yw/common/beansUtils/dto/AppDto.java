package com.yw.common.beansUtils.dto;

import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 *</pre>
 */
public class AppDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String appId;// 应用ID
	private Integer appVersion;
	private String appName;// 应用名称
	private Integer appStatus;// 应用状态
	private String eqName;//设备名称
	private String sn;//SN
	private String icon;// 图标URL
	private Integer bindingCount;//绑定设备数量
	
	private String attType;//属性类型
	private String attName;//属性名称
	private String function;//权限
	private String modelName;//型号名称
	private String modelNo;//型号编号
	private String modelCode;//厂商设备型号
	private String tradeName;//厂商名称
	private String tradeCode;//厂商名称
	private String orgName;//机构名称
	private Integer appCount;//应用数量
	private Integer modelVersion;//型号版本号
	
	private String flow;
	private String ep;
	public AppDto() {
		super();
	}
	
	public AppDto(AppEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setAppName(data.getAppName());
			this.setAppStatus(data.getAppStatus());
			this.setAppId(data.getAppId());
			this.setAppVersion(data.getAppVersion());
			this.setEqName(data.getEqName());
			this.setSn(data.getSn());
			this.setIcon(data.getIcon());
			this.setBindingCount(data.getBindingCount());
			this.setAttName(data.getAttName());
			this.setFunction(data.getFunction());
			this.setModelNo(data.getModelNo());
			this.setModelName(data.getModelName());
			this.setFlow(data.getFlow());
			this.setEp(data.getEp());
			this.setTradeCode(data.getTradeCode());
			this.setTradeName(data.getTradeName());
			this.setModelCode(data.getModelCode());
			this.setAppCount(data.getAppCount());
			this.setOrgName(data.getOrgName());
			this.setModelVersion(data.getModelVersion());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public AppDto(AppLicenseEntity data) {
		
		if (data != null) {
			
			this.setTid(data.getTid());
			this.setAppName(data.getAppReportName());
			this.setAppStatus(data.getAppInstanceStatus());
			this.setAppId(data.getAppId());
			this.setIcon(data.getIcon());
			this.setBindingCount(data.getBindingCount());
			this.setFunction(data.getFunction());
			this.setModelNo(data.getModelNo());
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getEqName() {
		return eqName;
	}

	public void setEqName(String eqName) {
		this.eqName = eqName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getBindingCount() {
		return bindingCount;
	}

	public void setBindingCount(Integer bindingCount) {
		this.bindingCount = bindingCount;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public Integer getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getAppCount() {
		return appCount;
	}

	public void setAppCount(Integer appCount) {
		this.appCount = appCount;
	}

	public Integer getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(Integer appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

}
