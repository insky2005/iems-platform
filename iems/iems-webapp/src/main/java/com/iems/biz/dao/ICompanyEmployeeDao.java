package com.iems.biz.dao;

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.model.CompanyEmployeeModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface ICompanyEmployeeDao extends IBaseDao<CompanyEmployee, String> {

	public abstract PageResults<CompanyEmployeeModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);


}
