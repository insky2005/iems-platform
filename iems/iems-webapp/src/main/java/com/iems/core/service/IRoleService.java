package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.entity.SysRole;

public interface IRoleService {

	PageResults<SysRole> getRoles(String rolecode, String rolename, 
			int pageNo, int pageSize);

	SysRole getRole(String roleid);

	void addRole(SysRole role);

	void updateRole(SysRole role);

	void deleteRole(String roleid);

}
