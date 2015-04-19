package com.iems.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.iems.core.dao.ISysRoleDao;
import com.iems.core.entity.SysRole;

@Repository
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole, String> implements ISysRoleDao {

	@Override
	public SysRole getRoleByRolecode(String rolecode) {
		String hqlString = "from SysRole where rolecode=?";
		
		SysRole sysRole = super.getByHQL(hqlString, rolecode);
		
		return sysRole;
	}

}
