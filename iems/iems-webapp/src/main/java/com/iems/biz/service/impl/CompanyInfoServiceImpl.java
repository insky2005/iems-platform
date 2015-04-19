package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.ICompanyInfoDao;
import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.model.CompanyInfoModel;
import com.iems.biz.service.ICompanyInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class CompanyInfoServiceImpl implements ICompanyInfoService {
	
	@Autowired
	private ICompanyInfoDao companyInfoDaoImpl;
	
	@Override
	public PageResults<CompanyInfoModel> getCompanyInfoModels(int pageNo, int pageSize,
			SearchConditions<CompanyInfoModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.CompanyInfoModel(companyInfo, companyAdmin) from CompanyInfo companyInfo, UserInfo companyAdmin where companyInfo.id=companyAdmin.companyid";
		String hqlCount = "select count(*) from CompanyInfo companyInfo, UserInfo companyAdmin where companyInfo.id=companyAdmin.companyid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return companyInfoDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<CompanyInfo> getCompanyInfos(int pageNo, int pageSize,
			SearchConditions<CompanyInfo> searchConditions) {
		String hqlSelect = "from CompanyInfo where 1=1";
		String hqlCount = "select count(*) from CompanyInfo where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return companyInfoDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public CompanyInfo getCompanyInfo(String id) {
		return companyInfoDaoImpl.get(id);
	}

	@Override
	public void addCompanyInfo(CompanyInfo companyInfo) {
		companyInfoDaoImpl.save(companyInfo);
	}

	@Override
	public void updateCompanyInfo(CompanyInfo companyInfo) {
		companyInfoDaoImpl.update(companyInfo);
	}

	@Override
	public void deleteCompanyInfo(String id) {
		companyInfoDaoImpl.deleteById(id);
	}

}
