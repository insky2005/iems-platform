package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysRole;

public interface IRoleService {

	PageResults<SysRole> getRoles(int pageNo, int pageSize, 
			SearchConditions<SysRole> searchConditions);

	SysRole getRole(String roleid);

	void addRole(SysRole role);

	void updateRole(SysRole role);

	void deleteRole(String roleid);

	SysRole getRoleByRolecode(String rolecode);

}
