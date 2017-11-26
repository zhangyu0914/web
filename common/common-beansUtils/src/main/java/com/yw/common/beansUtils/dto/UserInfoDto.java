package com.yw.common.beansUtils.dto;

import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:17
 * Q    Q: 308053847
 *</pre>
 */
public class UserInfoDto extends BaseDto{
	
	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String token;// token
	private String userAccount;// 账号
	private String displayName;// 姓名
	private String phone;// 电话号码
	private String email;// 邮箱地址
	private Integer userState;// 用户状态
	private Map<String, List<FunctionDto>> myFunction;
	
	public UserInfoDto() {
		super();
	}
	
	public UserInfoDto(UserInfoEntity data) {
		super();

		if (data != null) {
			this.setTid(data.getTid());
			this.setToken(data.getToken());
			this.setUserAccount(data.getUserAccount());
			this.setDisplayName(data.getDisplayName());
			this.setPhone(data.getPhone());
			this.setEmail(data.getEmail());
			this.setUserState(data.getUserState());
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Map<String, List<FunctionDto>> getMyFunction() {
		return myFunction;
	}

	public void setMyFunction(Map<String, List<FunctionDto>> myFunction) {
		this.myFunction = myFunction;
	}

}
