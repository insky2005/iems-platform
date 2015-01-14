package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysRoleDao;
import com.iems.core.dao.support.PageResults;
import com.iems.core.entity.SysRole;
import com.iems.core.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private ISysRoleDao sysRoleDaoImpl;

	@Override
	public PageResults<SysRole> getRoles(String rolecode, String rolename,
			int pageNo, int pageSize) {
		String hql = "from SysRole where 1=1";
		String countHql = "select count(*) from SysRole where 1=1";

		if (rolecode != null) {
			rolecode = "".concat("%").concat(rolecode).concat("%");
			hql += " and rolecode like ?";
			countHql += " and rolecode like ?";
		}

		if (rolename != null) {
			rolename = "".concat("%").concat(rolename).concat("%");
			hql += " and rolename like ?";
			countHql += " and rolename like ?";
		}

		return sysRoleDaoImpl.findPageByFetchedHql(hql, countHql, pageNo, pageSize, rolecode, rolename);
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

}
