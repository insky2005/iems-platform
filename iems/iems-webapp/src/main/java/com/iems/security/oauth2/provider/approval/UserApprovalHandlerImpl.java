package com.iems.security.oauth2.provider.approval;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;

public class UserApprovalHandlerImpl extends DefaultUserApprovalHandler {

	private Collection<String> autoApproveClients = new HashSet<String>();
	
	public void setAutoApproveClients(Collection<String> autoApproveClients) {
        this.autoApproveClients = autoApproveClients;
	}
	
	@Override
	public boolean isApproved(AuthorizationRequest authorizationRequest,
			Authentication userAuthentication) {
		boolean approved = super.isApproved(authorizationRequest, userAuthentication);
		
		boolean autoApproved = 
				(authorizationRequest.getResponseTypes().contains("code") || authorizationRequest.getResponseTypes().contains("token")) && 
				autoApproveClients.contains(authorizationRequest.getClientId());
		
		return approved || autoApproved;
	}
}
