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
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "per_page", required = false, defaultValue = "20") int pageSize) {
		PageResults<SysResource> pageResults = resourceServiceImpl.getResources(resourcecode, resourcename,
				pageNo, pageSize);
		return pageResults;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.GET)
	public @ResponseBody SysResource getResource(
			@PathVariable("resourceid") String resourceid) {
		logger.info("获取人员信息resourceid=" + resourceid);

		SysResource resource = resourceServiceImpl.getResource(resourceid);
		return resource;
	}

	@RequestMapping(value = "resources", method = RequestMethod.POST)
	public @ResponseBody Object addResource(
			@RequestBody SysResource resource) {
		logger.info("注册人员信息成功id=" + resource.getResourceid());

		if (resource.getResourceid() == null || resource.getResourceid().trim().length() == 0) {
			resource.setResourceid(CounterUtil.increment(SysResource.class.getName()));
		}
		
		resourceServiceImpl.addResource(resource);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateResource(
			@PathVariable("resourceid") String resourceid, 
			@RequestBody SysResource resource) {
		logger.info("更新人员信息id=" + resourceid);

		resourceServiceImpl.updateResource(resource);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteResource(
			@PathVariable("resourceid") String resourceid) {
		logger.info("删除人员信息id=" + resourceid);

		resourceServiceImpl.deleteResource(resourceid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "resources/{resourceid}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getResourceRoles(
			@PathVariable("resourceid") String resourceid) {
		logger.info("获取人员信息resourceid=" + resourceid);

		SysResource resource = resourceServiceImpl.getResource(resourceid);

		return resource.getRoles();
	}
}
