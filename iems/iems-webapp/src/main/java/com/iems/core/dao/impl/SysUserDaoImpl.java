package com.iems.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.entity.SysUser;

@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, String> implements ISysUserDao {

	@Override
	public SysUser getUserByUsername(String username) {
		String hqlString = "from SysUser where username=?";
		
		SysUser sysUser = super.getByHQL(hqlString, username);
		
		return sysUser;
	}
	
	@Override
	public SysUser getUserByEmail(String email) {
		String hqlString = "from SysUser where email=?";
		
		SysUser sysUser = super.getByHQL(hqlString, email);
		
		return sysUser;
	}
	
	@Override
	public SysUser getUserByMobile(String mobile) {
		String hqlString = "from SysUser where mobile=?";
		
		SysUser sysUser = super.getByHQL(hqlString, mobile);
		
		return sysUser;
	}


}
