package com.yw.common.beansUtils.utils.excel;

/**
 * Created by June on 2015/4/13.
 */
public class DoctorImportEntity {
    private String displayName; //姓名
    private String cellphone; //手机
    private String hospital; //单位名称
    private String hospitalTid; //单位id
    private String area; //地区
    private String dept; //一级科室
    private String deptTid; //一级科室id
    private String subDept; //二级科室
    private String subName; //二级科室
    private String subDeptTid; //三级科室id
    private String title; //职称
    private String titleTid; //职称id
    private String introduction; //个人介绍
    private Integer verifyStatus; //认证状态
    private Integer dailyPrice; //按日价格
    private Integer monthlyPrice; //包月价格
    private String projectCode; //特殊项目编码
    private String projectPrice; //特殊价格
    private String projectPeriod; //特殊周期
    private String message; //定制短信
    private Integer isTest; //是否测试医生
    private String isTestName; //是否测试医生
    private String fkCommonProjectTid;//项目ID
    private String projectName;//项目名称

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalTid() {
        return hospitalTid;
    }

    public void setHospitalTid(String hospitalTid) {
        this.hospitalTid = hospitalTid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDeptTid() {
        return deptTid;
    }

    public void setDeptTid(String deptTid) {
        this.deptTid = deptTid;
    }

    public String getSubDept() {
        return subDept;
    }

    public void setSubDept(String subDept) {
        this.subDept = subDept;
    }

    public String getSubDeptTid() {
        return subDeptTid;
    }

    public void setSubDeptTid(String subDeptTid) {
        this.subDeptTid = subDeptTid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleTid() {
        return titleTid;
    }

    public void setTitleTid(String titleTid) {
        this.titleTid = titleTid;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Integer dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Integer getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Integer monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(String projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getProjectPeriod() {
        return projectPeriod;
    }

    public void setProjectPeriod(String projectPeriod) {
        this.projectPeriod = projectPeriod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getFkCommonProjectTid() {
		return fkCommonProjectTid;
	}

	public void setFkCommonProjectTid(String fkCommonProjectTid) {
		this.fkCommonProjectTid = fkCommonProjectTid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIsTestName() {
		return isTestName;
	}

	public void setIsTestName(String isTestName) {
		this.isTestName = isTestName;
	}
    
}
