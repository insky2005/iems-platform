package com.iems.biz.dao;

import com.iems.biz.entity.UserInfo;
import com.iems.biz.model.UserInfoModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface IUserInfoDao extends IBaseDao<UserInfo, String> {

	public abstract PageResults<UserInfoModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);

	public abstract UserInfo getUserInfoByUserid(String userid);

	public abstract UserInfo getUserInfoByCompanyid(String companyid);

}
