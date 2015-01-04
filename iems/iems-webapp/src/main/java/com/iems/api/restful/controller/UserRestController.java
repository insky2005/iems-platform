package com.iems.api.restful.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.iems.core.dao.support.PageResults;
import com.iems.core.entity.SysRole;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;

@Controller
@RequestMapping("/v1")
public class UserRestController {
	private static final Log logger = LogFactory
			.getLog(UserRestController.class);

	@Autowired
	private IUserService userServiceImpl;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public @ResponseBody PageResults<SysUser> listUser(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "per_page", required = false, defaultValue = "20") int pageSize) {
		PageResults<SysUser> pageResults = userServiceImpl.getUsers(username,
				pageNo, pageSize);
		return pageResults;
	}

	@RequestMapping(value = "user/{userid}", method = RequestMethod.GET)
	public @ResponseBody SysUser getUser(@PathVariable("userid") String userid) {
		logger.info("获取人员信息userid=" + userid);

		SysUser user = userServiceImpl.getUser(userid);
		return user;
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public @ResponseBody Object addUser(SysUser user) {
		logger.info("注册人员信息成功id=" + user.getUserid());

		userServiceImpl.addUser(user);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "user/{userid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateUser(
			@PathVariable("userid") String userid, SysUser user) {
		logger.info("更新人员信息id=" + userid);

		userServiceImpl.updateUser(user);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "user/{userid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(@PathVariable("userid") String userid) {
		logger.info("删除人员信息id=" + userid);

		userServiceImpl.deleteUser(userid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除人员信息成功");
		return jsonObject;
	}

	
	@RequestMapping(value = "user/username/{username}", method = RequestMethod.GET)
	public @ResponseBody SysUser getUserByUsername(
			@PathVariable("username") String username) {
		logger.info("获取人员信息userid=" + username);

		SysUser user = userServiceImpl.getUserByUsername(username);
		return user;
	}

	@RequestMapping(value = "user/username/{username}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getUserRolesByUsername(
			@PathVariable("username") String username) {
		logger.info("获取人员信息userid=" + username);

		SysUser user = userServiceImpl.getUserByUsername(username);

		return user.getRoles();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "user/username/{username}", method = RequestMethod.PATCH)
	public @ResponseBody Object updateUserPasswordByUsername(
			@PathVariable("username") String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "newpassword", required = false) String newpassword,
			@RequestParam(value = "confirmpassword", required = false) String confirmpassword) {
		logger.info("更新人员信息id=" + username);

		JSONObject jsonObject = new JSONObject();

		try {
			SysUser user = userServiceImpl.getUserByUsername(username);

			Object salt = null;

			if (this.saltSource != null) {
				salt = this.saltSource.getSalt(user);
			}
			if (!passwordEncoder.isPasswordValid(user.getPassword(), password, salt)) {
				throw new Exception("帐户原密码验证失败");
			}

			if (newpassword == null 
					|| confirmpassword == null
					|| newpassword.length() <= 0
					|| confirmpassword.length() <= 0) {
				throw new Exception("新密码和确认密码必须填写，并且一致");
			}

			if (!newpassword.equals(confirmpassword)) {
				throw new Exception("新密码和确认密码必须一致");
			}

			user.setPassword(newpassword);

			userServiceImpl.updateUser(user);

			jsonObject.put("msg", "更新帐户密码成功");
		} catch (Exception e) {
			jsonObject.put("msg", "更新帐户密码失败：" + e.getMessage());
		}

		return jsonObject;
	}
}
