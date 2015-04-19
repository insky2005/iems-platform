package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.UserInfo;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;

public class UserInfoModel extends SysUserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764466912098447988L;

	private UserInfo userInfo;
	
	public UserInfoModel() {
		super();
	}
	
	public UserInfoModel(SysUser sysUser, UserInfo userInfo) {
		this(userInfo==null || userInfo.getId()==null, sysUser, userInfo);
	}

	public UserInfoModel(boolean isNew, SysUser sysUser, UserInfo userInfo) {
		super(isNew, sysUser);
		
		this.setUserInfo(userInfo);
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
