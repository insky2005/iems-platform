package com.iems.security.access.resourcedetails;

import java.util.Collection;

public interface ResourceDetailsService {
	
	Collection<? extends ResourceDetails> loadResourceDetails();

}
