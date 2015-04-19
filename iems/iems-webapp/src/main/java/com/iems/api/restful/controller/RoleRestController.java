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
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {

		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<SysRole> searchConditions = new SearchConditions<SysRole>();
		searchConditions.add(new SearchCondition("rolecode", "like", rolecode));
		searchConditions.add(new SearchCondition("rolename", "like", rolename));
		
		PageResults<SysRole> pageResults = 
				roleServiceImpl.getRoles(pageNo, pageSize, 
				searchConditions);
		
		return pageResults;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.GET)
	public @ResponseBody SysRole getRole(
			@PathVariable("roleid") String roleid) {
		logger.info("获取角色信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);
		return role;
	}

	@RequestMapping(value = "roles", method = RequestMethod.POST)
	public @ResponseBody Object addRole(
			@RequestBody SysRole role) {
		logger.info("注册角色信息成功id=" + role.getRoleid());

		if (role.getRoleid() == null || role.getRoleid().trim().length() == 0) {
			role.setRoleid(CounterUtil.increment(SysRole.class.getName()));
		}
		
		roleServiceImpl.addRole(role);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册角色信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateRole(
			@PathVariable("roleid") String roleid, 
			@RequestBody SysRole role) {
		logger.info("更新角色信息id=" + roleid);

		roleServiceImpl.updateRole(role);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新角色信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteRole(
			@PathVariable("roleid") String roleid) {
		logger.info("删除角色信息id=" + roleid);

		roleServiceImpl.deleteRole(roleid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除角色信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "roles/{roleid}/users", method = RequestMethod.GET)
	public @ResponseBody List<SysUser> getRoleUsers(
			@PathVariable("roleid") String roleid) {
		logger.info("获取角色的用户信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);

		return role.getUsers();
	}
	
	@RequestMapping(value = "roles/{roleid}/resources", method = RequestMethod.GET)
	public @ResponseBody List<SysResource> getRoleResources(
			@PathVariable("roleid") String roleid) {
		logger.info("获取角色的资源信息roleid=" + roleid);

		SysRole role = roleServiceImpl.getRole(roleid);

		return role.getResources();
	}
	
	@RequestMapping(value = "roles/rolecode/{rolecode}", method = RequestMethod.GET)
	public @ResponseBody SysRole getRoleByRolecode(
			@PathVariable("rolecode") String rolecode) {
		logger.info("获取角色信息rolecode=" + rolecode);

		SysRole role = roleServiceImpl.getRoleByRolecode(rolecode);
		return role;
	}
	
	@RequestMapping(value = "roles/rolecode/{rolecode}/users", method = RequestMethod.GET)
	public @ResponseBody List<SysUser> getRoleUsersByRolecode(
			@PathVariable("rolecode") String rolecode) {
		logger.info("获取角色的用户信息rolecode=" + rolecode);

		SysRole role = roleServiceImpl.getRoleByRolecode(rolecode);

		return role.getUsers();
	}
	
	@RequestMapping(value = "roles/rolecode/{rolecode}/resources", method = RequestMethod.GET)
	public @ResponseBody List<SysResource> getRoleResourcesByRolecode(
			@PathVariable("rolecode") String rolecode) {
		logger.info("获取角色的资源信息rolecode=" + rolecode);

		SysRole role = roleServiceImpl.getRoleByRolecode(rolecode);

		return role.getResources();
	}
}
