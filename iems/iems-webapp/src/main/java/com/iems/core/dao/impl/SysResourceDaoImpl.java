package com.iems.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iems.core.dao.ISysResourceDao;
import com.iems.core.entity.SysResource;

@Component
public class SysResourceDaoImpl extends BaseDaoImpl<SysResource, String> implements ISysResourceDao {

	@Override
	public List<SysResource> loadResourceDetails() {
		String hqlString = "from SysResource where enabled=?";
		List<SysResource> list = super.getListByHQL(hqlString, true);
		return list;
	}

}
