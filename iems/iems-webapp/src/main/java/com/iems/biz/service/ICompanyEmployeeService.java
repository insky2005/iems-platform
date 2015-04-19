package com.iems.biz.service;

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.model.CompanyEmployeeModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface ICompanyEmployeeService {

	PageResults<CompanyEmployeeModel> getCompanyEmployeeModels(int pageNo, int pageSize, 
			SearchConditions<CompanyEmployeeModel> searchConditions);

	
	PageResults<CompanyEmployee> getCompanyEmployees(int pageNo, int pageSize, 
			SearchConditions<CompanyEmployee> searchConditions);

	CompanyEmployee getCompanyEmployee(String id);

	void addCompanyEmployee(CompanyEmployee companyEmployee);

	void updateCompanyEmployee(CompanyEmployee companyEmployee);

	void deleteCompanyEmployee(String id);
}
