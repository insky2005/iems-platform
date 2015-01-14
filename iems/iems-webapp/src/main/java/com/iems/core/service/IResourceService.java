package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.entity.SysResource;

public interface IResourceService {

	PageResults<SysResource> getResources(String resourcecode, String resourcename, int pageNo,
			int pageSize);

	SysResource getResource(String resourceid);

	void addResource(SysResource resource);

	void updateResource(SysResource resource);

	void deleteResource(String resourceid);

}
