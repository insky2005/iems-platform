package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysRoleDao;
import com.iems.core.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private ISysRoleDao sysRoleDaoImpl;

}
