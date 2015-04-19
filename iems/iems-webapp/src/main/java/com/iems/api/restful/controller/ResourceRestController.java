package com.iems.api.restful.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysRole;
import com.iems.core.entity.SysResource;
import com.iems.core.service.IResourceService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class ResourceRestController {
	private static final Log logger = LogFactory
			.getLog(ResourceRestController.class);

	@Autowired
	private IResourceService resourceServiceImpl;

	@RequestMapping(value = "resources", method = RequestMethod.GET)
	public @ResponseBody PageResults<SysResource> listResource(
			@RequestParam(value = "resourcecode", required = false) String resourcecode,
			@RequestParam(value = "resourcename", required = false) String resourcename,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {

		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;
		
		SearchConditions<SysResource> searchConditions = new SearchConditions<SysResource>();
		searchConditions.add(new SearchCondition("resourcecode", "like", resourcecode));
		searchConditions.add(new SearchCondition("resourcename", "like", resourcename));

		PageResults<SysResource> pageResults = resourceServiceImpl.getResources(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.GET)
	public @ResponseBody SysResource getResource(
			@PathVariable("resourceid") String resourceid) {
		logger.info("获取资源信息resourceid=" + resourceid);

		SysResource resource = resourceServiceImpl.getResource(resourceid);
		return resource;
	}

	@RequestMapping(value = "resources", method = RequestMethod.POST)
	public @ResponseBody Object addResource(
			@RequestBody SysResource resource) {
		logger.info("注册资源信息成功id=" + resource.getResourceid());

		if (resource.getResourceid() == null || resource.getResourceid().trim().length() == 0) {
			resource.setResourceid(CounterUtil.increment(SysResource.class.getName()));
		}
		
		resourceServiceImpl.addResource(resource);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册资源信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateResource(
			@PathVariable("resourceid") String resourceid, 
			@RequestBody SysResource resource) {
		logger.info("更新资源信息id=" + resourceid);

		resourceServiceImpl.updateResource(resource);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新资源信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteResource(
			@PathVariable("resourceid") String resourceid) {
		logger.info("删除资源信息id=" + resourceid);

		resourceServiceImpl.deleteResource(resourceid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除资源信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getResourceRoles(
			@PathVariable("resourceid") String resourceid) {
		logger.info("获取资源的角色信息resourceid=" + resourceid);

		SysResource resource = resourceServiceImpl.getResource(resourceid);

		return resource.getRoles();
	}
	
	@RequestMapping(value = "resources/resourcecode/{resourcecode}", method = RequestMethod.GET)
	public @ResponseBody SysResource getResourceByResourcecode(
			@PathVariable("resourcecode") String resourcecode) {
		logger.info("获取资源信息resourcecode=" + resourcecode);

		SysResource resource = resourceServiceImpl.getResourceByResourcecode(resourcecode);
		return resource;
	}
	
	@RequestMapping(value = "resources/resourcecode/{resourcecode}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getResourceRolesByResourcecode(
			@PathVariable("resourcecode") String resourcecode) {
		logger.info("获取资源的角色信息resourcecode=" + resourcecode);

		SysResource resource = resourceServiceImpl.getResourceByResourcecode(resourcecode);

		return resource.getRoles();
	}
}
