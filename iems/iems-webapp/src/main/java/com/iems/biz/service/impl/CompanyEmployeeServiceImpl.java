package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.ICompanyEmployeeDao;
import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.model.CompanyEmployeeModel;
import com.iems.biz.service.ICompanyEmployeeService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class CompanyEmployeeServiceImpl implements ICompanyEmployeeService {
	
	@Autowired
	private ICompanyEmployeeDao companyEmployeeDaoImpl;
	
	@Override
	public PageResults<CompanyEmployeeModel> getCompanyEmployeeModels(int pageNo, int pageSize,
			SearchConditions<CompanyEmployeeModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.CompanyEmployeeModel(sysUser, companyInfo, companyEmployee) from CompanyInfo companyInfo, CompanyEmployee companyEmployee, SysUser sysUser where companyInfo.id=companyEmployee.companyid and companyEmployee.userid=sysUser.userid";
		String hqlCount = "select count(*) from CompanyInfo companyInfo, CompanyEmployee companyEmployee, SysUser sysUser where companyInfo.id=companyEmployee.companyid and companyEmployee.userid=sysUser.userid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return companyEmployeeDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<CompanyEmployee> getCompanyEmployees(int pageNo, int pageSize,
			SearchConditions<CompanyEmployee> searchConditions) {
		String hqlSelect = "from CompanyEmployee where 1=1";
		String hqlCount = "select count(*) from CompanyEmployee where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return companyEmployeeDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public CompanyEmployee getCompanyEmployee(String id) {
		return companyEmployeeDaoImpl.get(id);
	}

	@Override
	public void addCompanyEmployee(CompanyEmployee companyEmployee) {
		companyEmployeeDaoImpl.save(companyEmployee);
	}

	@Override
	public void updateCompanyEmployee(CompanyEmployee companyEmployee) {
		companyEmployeeDaoImpl.update(companyEmployee);
	}

	@Override
	public void deleteCompanyEmployee(String id) {
		companyEmployeeDaoImpl.deleteById(id);
	}

}
