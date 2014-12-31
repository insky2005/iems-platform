package com.iems.security.access;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AccessDecisionManagerImpl implements AccessDecisionManager {
	// ~ Static fields/initializers
	// =====================================================================================

	private static final Log logger = LogFactory
			.getLog(AccessDecisionManagerImpl.class);

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (logger.isDebugEnabled()) {
			logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - start"); //$NON-NLS-1$  
		}
		if (configAttributes == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end"); //$NON-NLS-1$  
			}
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("正在访问的url是：" + object.toString());
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			logger.debug("needRole is：" + ca.getAttribute());
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				logger.debug("/t授权信息是：" + ga.getAuthority());
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					if (logger.isDebugEnabled()) {
						logger.debug("判断到，needRole 是" + needRole + ",用户的角色是:"
								+ ga.getAuthority() + "，授权数据相匹配");
						logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end"); //$NON-NLS-1$  
					}
					return;
				}
			}
		}
		throw new AccessDeniedException("没有权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
