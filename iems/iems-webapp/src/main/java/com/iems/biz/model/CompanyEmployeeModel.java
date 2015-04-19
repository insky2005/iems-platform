package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.entity.CompanyInfo;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;

public class CompanyEmployeeModel extends SysUserModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5634519599468048657L;

	private CompanyInfo companyInfo;

	private CompanyEmployee companyEmployee;
	
	public CompanyEmployeeModel() {
		super();
	}
	
	public CompanyEmployeeModel(CompanyEmployee companyEmployee) {
		this(null, companyEmployee);
	}
	
	public CompanyEmployeeModel(CompanyInfo companyInfo, CompanyEmployee companyEmployee) {
		this(null, companyInfo, companyEmployee);
	}

	public CompanyEmployeeModel(SysUser sysUser, CompanyInfo companyInfo, CompanyEmployee companyEmployee) {
		this(companyEmployee==null || companyEmployee.getId()==null, sysUser, companyInfo, companyEmployee);
	}

	public CompanyEmployeeModel(boolean isNew, SysUser sysUser, CompanyInfo companyInfo, CompanyEmployee companyEmployee) {
		super(isNew, sysUser);
		
		this.companyInfo = companyInfo;
		this.companyEmployee = companyEmployee;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	
	public CompanyEmployee getCompanyEmployee() {
		return companyEmployee;
	}

	public void setCompanyEmployee(CompanyEmployee companyEmployee) {
		this.companyEmployee = companyEmployee;
	}
}
