package com.iems.core.dao.impl;

import org.springframework.stereotype.Component;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.entity.SysUser;

@Component
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, String> implements ISysUserDao {

	@Override
	public SysUser loadUserByUsername(String username) {
		String hqlString = "from SysUser where username=?";
		
		SysUser sysUser = super.getByHQL(hqlString, username);
		
		return sysUser;
	}

}