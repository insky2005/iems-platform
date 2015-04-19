package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysUser;

public interface IUserService {

	PageResults<SysUser> getUsers(int pageNo, int pageSize, 
			SearchConditions<SysUser> searchConditions);

	SysUser getUser(String userid);

	void addUser(SysUser user);

	void updateUser(SysUser user);

	void deleteUser(String userid);

	SysUser getUserByUsername(String username);

}
