package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysResource;

public interface IResourceService {

	PageResults<SysResource> getResources(int pageNo, int pageSize, 
			SearchConditions<SysResource> searchConditions);

	SysResource getResource(String resourceid);

	void addResource(SysResource resource);

	void updateResource(SysResource resource);

	void deleteResource(String resourceid);

	SysResource getResourceByResourcecode(String resourcecode);

}
