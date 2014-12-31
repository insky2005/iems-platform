package com.iems.security.access.resourcedetails;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public interface ResourceDetails {

	Collection<? extends ConfigAttribute> getAttributes();
	
	String getUrl();
}
