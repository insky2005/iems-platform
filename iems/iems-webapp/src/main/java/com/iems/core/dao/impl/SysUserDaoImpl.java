package com.iems.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.entity.SysUser;

@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, String> implements ISysUserDao {

	@Override
	public SysUser loadUserByUsername(String username) {
		String hqlString = "from SysUser where username=?";
		
		SysUser sysUser = super.getByHQL(hqlString, username);
		
		return sysUser;
	}

}
