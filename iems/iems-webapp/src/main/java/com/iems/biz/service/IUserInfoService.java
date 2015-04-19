package com.iems.biz.service;

import com.iems.biz.entity.UserInfo;
import com.iems.biz.model.UserInfoModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface IUserInfoService {

	PageResults<UserInfoModel> getUserInfoModels(int pageNo, int pageSize, 
			SearchConditions<UserInfoModel> searchConditions);

	
	PageResults<UserInfo> getUserInfos(int pageNo, int pageSize, 
			SearchConditions<UserInfo> searchConditions);

	UserInfo getUserInfo(String id);

	void addUserInfo(UserInfo userInfo);

	void updateUserInfo(UserInfo userInfo);

	void deleteUserInfo(String id);


	UserInfo getUserInfoByUserid(String userid);

	UserInfo getUserInfoByCompanyid(String companyid);

}
