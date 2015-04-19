package com.iems.biz.service;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.model.CompanyInfoModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface ICompanyInfoService {

	PageResults<CompanyInfoModel> getCompanyInfoModels(int pageNo, int pageSize, 
			SearchConditions<CompanyInfoModel> searchConditions);
  
	
	PageResults<CompanyInfo> getCompanyInfos(int pageNo, int pageSize, 
			SearchConditions<CompanyInfo> searchConditions);

	CompanyInfo getCompanyInfo(String id);

	void addCompanyInfo(CompanyInfo companyInfo);

	void updateCompanyInfo(CompanyInfo companyInfo);

	void deleteCompanyInfo(String id);
}
