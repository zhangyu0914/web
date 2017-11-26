package com.yw.common.beansUtils.utils.excel;

import java.sql.Timestamp;

/**
 *<pre>
 * 功   能: 患者导入信息   
 * 创建者: 苏鹏鹏
 * 日   期: 2016-1-7下午5:09:03
 * Q  Q: 1216289696
 *</pre>
 */
public class PatientImportEntity {
	
    private Integer testFlag; //是否测试患者
    private String displayName; //姓名
    private String phone; //手机
    private String cardNo;// 身份证号
    private String projectName; //特殊项目
	private String projectTid; //项目Id
	private String projectCode; //项目Id
    private Integer identificationFlag; //认证状态
    private String productTid;// 产品id
    private String message; //定制短信
    private String result; //定制短信
    private Integer importStatus; //状态:0:未处理;1:处理失败;2:处理成功
    private String tid;
    private Integer startIndex;
    private Integer endIndex;
    private Integer smsFlag;//是否发短信;0:不发短信;1:发短信  
	private Timestamp createTime; // 创建时间
	private Long updateTime; // 更新时间
	public Integer getTestFlag() {
		return testFlag;
	}
	public void setTestFlag(Integer testFlag) {
		this.testFlag = testFlag;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getProjectTid() {
		return projectTid;
	}
	public void setProjectTid(String projectTid) {
		this.projectTid = projectTid;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Integer getIdentificationFlag() {
		return identificationFlag;
	}
	public void setIdentificationFlag(Integer identificationFlag) {
		this.identificationFlag = identificationFlag;
	}
	public String getProductTid() {
		return productTid;
	}
	public void setProductTid(String productTid) {
		this.productTid = productTid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getImportStatus() {
		return importStatus;
	}
	public void setImportStatus(Integer importStatus) {
		this.importStatus = importStatus;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(Integer smsFlag) {
		this.smsFlag = smsFlag;
	}
    

}
