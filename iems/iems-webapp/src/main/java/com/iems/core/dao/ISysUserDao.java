package com.iems.core.dao;

import com.iems.core.entity.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser, String> {

	SysUser loadUserByUsername(String username);
	
}
