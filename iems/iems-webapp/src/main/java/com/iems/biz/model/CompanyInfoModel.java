package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.UserInfo;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;

public class CompanyInfoModel extends SysUserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2818792196989766568L;
	
	private CompanyInfo companyInfo;
	private UserInfo companyAdmin;
	
	public CompanyInfoModel() {
		super();
	}
	
	public CompanyInfoModel(CompanyInfo companyInfo) {
		this(companyInfo, null);
	}

	public CompanyInfoModel(CompanyInfo companyInfo, UserInfo companyAdmin) {
		this(null, companyInfo, companyAdmin);
	}

	public CompanyInfoModel(SysUser sysUser, CompanyInfo companyInfo, UserInfo companyAdmin) {
		this(companyInfo==null || companyInfo.getId()==null, sysUser, companyInfo, companyAdmin);
	}

	public CompanyInfoModel(boolean isNew, SysUser sysUser, CompanyInfo companyInfo, UserInfo companyAdmin) {
		super(isNew, sysUser);
		
		this.companyInfo = companyInfo;
		this.companyAdmin = companyAdmin;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public UserInfo getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(UserInfo companyAdmin) {
		this.companyAdmin = companyAdmin;
	}
}
