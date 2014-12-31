package com.iems.core.dao;

import java.util.List;

import com.iems.core.entity.SysResource;

public interface ISysResourceDao extends IBaseDao<SysResource, String> {

	List<SysResource> loadResourceDetails();

}
