package com.iems.biz.dao;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.model.CompanyInfoModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface ICompanyInfoDao extends IBaseDao<CompanyInfo, String> {

	public abstract PageResults<CompanyInfoModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);


}
