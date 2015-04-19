package com.iems.core.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysResourceDao;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysResource;
import com.iems.core.service.IResourceService;
import com.iems.security.access.resourcedetails.ResourceDetails;
import com.iems.security.access.resourcedetails.ResourceDetailsService;

@Service
public class ResourceServiceImpl implements ResourceDetailsService, IResourceService {

	@Autowired
	private ISysResourceDao sysResourceDaoImpl;

	@Override
	public Collection<? extends ResourceDetails> loadResourceDetails() {
		Collection<? extends ResourceDetails> list = sysResourceDaoImpl.loadResourceDetails();
		
		return list;
	}

	@Override
	public PageResults<SysResource> getResources(int pageNo, int pageSize, 
			SearchConditions<SysResource> searchConditions) {
		String hqlSelect = "from SysResource where 1=1";
		String hqlCount = "select count(*) from SysResource where 1=1";

		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;

		return sysResourceDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public SysResource getResource(String resourceid) {
		return sysResourceDaoImpl.get(resourceid);
	}

	@Override
	public void addResource(SysResource resource) {
		sysResourceDaoImpl.save(resource);
	}

	@Override
	public void updateResource(SysResource resource) {
		sysResourceDaoImpl.update(resource);
	}

	@Override
	public void deleteResource(String resourceid) {
		sysResourceDaoImpl.deleteById(resourceid);
	}

	@Override
	public SysResource getResourceByResourcecode(String resourcecode) {
		SysResource sysResource = this.sysResourceDaoImpl.getResourceByResourcecode(resourcecode);

		return sysResource;
	}
	
}
