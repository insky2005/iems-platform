package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysRoleDao;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysRole;
import com.iems.core.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private ISysRoleDao sysRoleDaoImpl;

	@Override
	public PageResults<SysRole> getRoles(int pageNo, int pageSize, 
			SearchConditions<SysRole> searchConditions) {
		String hqlSelect = "from SysRole where 1=1";
		String hqlCount = "select count(*) from SysRole where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();

		hqlSelect += whereClause;
		hqlCount += whereClause;

		return sysRoleDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public SysRole getRole(String roleid) {
		return sysRoleDaoImpl.get(roleid);
	}

	@Override
	public void addRole(SysRole role) {
		sysRoleDaoImpl.save(role);
	}

	@Override
	public void updateRole(SysRole role) {
		sysRoleDaoImpl.update(role);
	}

	@Override
	public void deleteRole(String roleid) {
		sysRoleDaoImpl.deleteById(roleid);
	}

	@Override
	public SysRole getRoleByRolecode(String rolecode) {
		SysRole sysRole = this.sysRoleDaoImpl.getRoleByRolecode(rolecode);

		return sysRole;
	}
}
