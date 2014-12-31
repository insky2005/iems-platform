package com.iems.core.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysResourceDao;
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

}
