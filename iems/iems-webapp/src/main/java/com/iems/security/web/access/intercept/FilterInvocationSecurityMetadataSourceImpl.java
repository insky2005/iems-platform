package com.iems.security.web.access.intercept;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.Assert;

import com.iems.security.access.resourcedetails.ResourceDetails;
import com.iems.security.access.resourcedetails.ResourceDetailsService;

public class FilterInvocationSecurityMetadataSourceImpl 
	implements FilterInvocationSecurityMetadataSource, InitializingBean {
	// ~ Static fields/initializers
	// =====================================================================================

	private static final Log logger = LogFactory
			.getLog(FilterInvocationSecurityMetadataSourceImpl.class);

	
	//private RoleService roleService;
	//private ActionService actionService;
	
	private ResourceDetailsService resourceDetailsService;
	public void setResourceDetailsService(ResourceDetailsService resourceDetailsService) {
		this.resourceDetailsService = resourceDetailsService;
	}

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	private void loadResourceDefine() {
		// 查找到所有权限
		//List<String> query = loadAuthorityByQuery();
		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		 * sparta
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		Collection<? extends ResourceDetails> resources = resourceDetailsService.loadResourceDetails();
		
		for (ResourceDetails resource : resources) {
			String url = resource.getUrl();
			
			Collection<? extends ConfigAttribute> attributes = resource.getAttributes();
			
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			for (ConfigAttribute attr : attributes) {
				configAttributes.add(new SecurityConfig(attr.getAttribute()));
			}
			
			resourceMap.put(url, configAttributes);
		}

		/*
		for (String auth : query) {
			// 获取authority的authorityName String auth=authority.getAuthorityName();
			ConfigAttribute ca = new SecurityConfig(auth);
			// 获取authority的resources
			List<String> resourceses = loadResourcesByQuery(auth);
			System.out.println("resourceses" + resourceses);
			for (String res : resourceses) {
				// 获取res的url
				String url = res;
				// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		}
		*/
	}
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - start"); //$NON-NLS-1$  
		}
		
		// FIXME: 修正匹配规则
		// guess object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		System.out.println("reqURL:" + url);

		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();	// System.out.println("resURL:"+resURL);

			if (resURL.equals(url)) {
				System.out.println("匹配?true,resURL:" + resURL);
				return resourceMap.get(resURL);
			}
			
			/*
			FilterInvocation filterInvocation = (FilterInvocation) object;
			HttpServletRequest request = filterInvocation.getHttpRequest();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			System.out.println("匹配"+requestMatcher.matches(request));
			 */
			
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$  
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.resourceDetailsService, "A resourceDetailsService must be set");
		this.loadResourceDefine();
	}

}
