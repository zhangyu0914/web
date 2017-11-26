package com.yw.common.beansUtils.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.UserInfoDto;

/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:17
 * Q    Q: 308053847
 *</pre>
 */
public class UserInfoEntity extends BaseEntity {

	private static final long serialVersionUID = -2977925795818L;
	
	@Length(min=0, max=64, message = "WEBPLATFORM.USERINFO.USERPWD")
	private String userPwd;// 密码
	@Length(min=0, max=64, message = "WEBPLATFORM.USERINFO.USERSALT")
	private String userSalt;// 密码盐
	@Length(min=0, max=200, message = "WEBPLATFORM.USERINFO.USERSECRETKEY")
	private String userSecretKey;// 安全KEY
	@Length(min=0, max=100, message = "WEBPLATFORM.USERINFO.USERACCOUNT")
	private String userAccount;// 账号
	@Length(min=0, max=100, message = "WEBPLATFORM.USERINFO.DISPLAYNAME")
	private String displayName;// 姓名
	@Length(min=0, max=30, message = "WEBPLATFORM.USERINFO.PHONE")
	private String phone;// 电话号码
	@Length(min=0, max=100, message = "WEBPLATFORM.USERINFO.EMAIL")
	private String email;// 邮箱地址
	@Length(min=0, max=10, message = "WEBPLATFORM.USERINFO.BIRTHDAY")
	private String birthday;// 生日
	private Integer age;// 年龄（目前不用）
	private Integer gender;// 性别
	@Length(min=0, max=50, message = "WEBPLATFORM.USERINFO.CITY")
	private String city;// 城市
	@Length(min=0, max=200, message = "WEBPLATFORM.USERINFO.ADDRESS")
	private String address;// 地址
	@Length(min=0, max=100, message = "WEBPLATFORM.USERINFO.AVATAR")
	private String avatar;// 头像地址
	private Integer loginStatus;// 登录状态
	private Integer delFlag;// 删除
	private Integer userState;// 用户状态
	

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V1.0.0版本*******************************************************************************/
	private String token;// token
	private String rolesIds;// 
	private String userPwdNew;//新密码 
	private List<UsersRolesEntity> rolesList; // 此用户下所有所有角色
	
	//无参构造方法
	public UserInfoEntity() {
		super();
	}
	
	//TID参数构造方法
	public UserInfoEntity(String tid) {
		super();
		this.setTid(tid);
	}
	
	public UserInfoEntity(UserInfoDto data) {
		super();

		if (data != null) {
			
		}
	}
	
	public UserInfoEntity(String tid, String userAccount) {
		super();
		this.setTid(tid);
		this.setUserAccount(userAccount);
	}
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	public String getUserSecretKey() {
		return userSecretKey;
	}

	public void setUserSecretKey(String userSecretKey) {
		this.userSecretKey = userSecretKey;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public String getRolesIds() {
		return rolesIds;
	}

	public void setRolesIds(String rolesIds) {
		this.rolesIds = rolesIds;
	}

	public String getUserPwdNew() {
		return userPwdNew;
	}

	public void setUserPwdNew(String userPwdNew) {
		this.userPwdNew = userPwdNew;
	}

	public List<UsersRolesEntity> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<UsersRolesEntity> rolesList) {
		this.rolesList = rolesList;
	}

}
