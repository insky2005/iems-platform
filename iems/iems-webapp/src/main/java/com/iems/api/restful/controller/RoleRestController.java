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
import com.iems.core.entity.SysResource;
import com.iems.core.entity.SysRole;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IRoleService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class RoleRestController {
	private static final Log logger = LogFactory
			.getLog(RoleRestController.class);

	@Autowired
	private IRoleService roleServiceImpl;
	
	
	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public @ResponseBody PageResults<SysRole> listRole(
			@RequestParam(value = "rolecode", required = false) String rolecode,
			@RequestParam(value = "rolename", required = false) String rolename,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "per_page", required = false, defaultValue = "20") int pageSize) {
		PageResults<SysRole> pageResults = roleServiceImpl.getRoles(rolecode, rolename,
				pageNo, pageSize);
		return pageResults;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.GET)
	public @ResponseBody SysRole getRole(
			@PathVariable("roleid") String roleid) {
		logger.info("获取人员信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);
		return role;
	}

	@RequestMapping(value = "roles", method = RequestMethod.POST)
	public @ResponseBody Object addRole(
			@RequestBody SysRole role) {
		logger.info("注册人员信息成功id=" + role.getRoleid());

		if (role.getRoleid() == null || role.getRoleid().trim().length() == 0) {
			role.setRoleid(CounterUtil.increment(SysRole.class.getName()));
		}
		
		roleServiceImpl.addRole(role);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateRole(
			@PathVariable("roleid") String roleid, 
			@RequestBody SysRole role) {
		logger.info("更新人员信息id=" + roleid);

		roleServiceImpl.updateRole(role);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteRole(
			@PathVariable("roleid") String roleid) {
		logger.info("删除人员信息id=" + roleid);

		roleServiceImpl.deleteRole(roleid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}/users", method = RequestMethod.GET)
	public @ResponseBody List<SysUser> getRoleUsers(
			@PathVariable("roleid") String roleid) {
		logger.info("获取人员信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);

		return role.getUsers();
	}
	
	@RequestMapping(value = "roles/{roleid}/resources", method = RequestMethod.GET)
	public @ResponseBody List<SysResource> getRoleResources(
			@PathVariable("roleid") String roleid) {
		logger.info("获取人员信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);

		return role.getResources();
	}
}
