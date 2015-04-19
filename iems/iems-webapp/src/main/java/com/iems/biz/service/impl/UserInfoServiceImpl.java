package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.IUserInfoDao;
import com.iems.biz.entity.UserInfo;
import com.iems.biz.model.UserInfoModel;
import com.iems.biz.service.IUserInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
	
	@Autowired
	private IUserInfoDao userInfoDaoImpl;
	
	@Override
	public PageResults<UserInfoModel> getUserInfoModels(int pageNo, int pageSize,
			SearchConditions<UserInfoModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.UserInfoModel(sysuser, userinfo) from UserInfo userinfo, SysUser sysuser where userinfo.userid=sysuser.userid";
		String hqlCount = "select count(*) from UserInfo userinfo, SysUser sysuser where userinfo.userid=sysuser.userid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return userInfoDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<UserInfo> getUserInfos(int pageNo, int pageSize,
			SearchConditions<UserInfo> searchConditions) {
		String hqlSelect = "from UserInfo where 1=1";
		String hqlCount = "select count(*) from UserInfo where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return userInfoDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public UserInfo getUserInfo(String id) {
		return userInfoDaoImpl.get(id);
	}

	@Override
	public void addUserInfo(UserInfo userInfo) {
		userInfoDaoImpl.save(userInfo);
	}

	@Override
	public void updateUserInfo(UserInfo userInfo) {
		userInfoDaoImpl.update(userInfo);
	}

	@Override
	public void deleteUserInfo(String id) {
		userInfoDaoImpl.deleteById(id);
	}

	@Override
	public UserInfo getUserInfoByUserid(String userid) {
		return userInfoDaoImpl.getUserInfoByUserid(userid);
	}

	@Override
	public UserInfo getUserInfoByCompanyid(String companyid) {
		return userInfoDaoImpl.getUserInfoByCompanyid(companyid);
	}

}
