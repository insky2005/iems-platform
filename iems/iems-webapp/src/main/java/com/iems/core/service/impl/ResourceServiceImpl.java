package com.iems.core.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysResourceDao;
import com.iems.core.dao.support.PageResults;
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
	public PageResults<SysResource> getResources(String resourcecode, String resourcename, 
			int pageNo, int pageSize) {
		String hql = "from SysResource where 1=1";
		String countHql = "select count(*) from SysResource where 1=1";

		if (resourcecode != null) {
			resourcecode = "".concat("%").concat(resourcecode).concat("%");
			hql += " and resourcecode like ?";
			countHql += " and resourcecode like ?";
		}

		if (resourcename != null) {
			resourcename = "".concat("%").concat(resourcename).concat("%");
			hql += " and resourcename like ?";
			countHql += " and resourcename like ?";
		}

		return sysResourceDaoImpl.findPageByFetchedHql(hql, countHql, pageNo, pageSize, resourcecode, resourcename);
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

}
