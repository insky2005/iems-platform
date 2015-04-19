package com.iems.core.dao;

import com.iems.core.entity.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser, String> {

	SysUser getUserByUsername(String username);

	SysUser getUserByEmail(String email);

	SysUser getUserByMobile(String mobile);
	
}
