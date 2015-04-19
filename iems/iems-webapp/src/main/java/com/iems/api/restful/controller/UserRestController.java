package com.iems.api.restful.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysRole;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;
import com.iems.core.service.IUserService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class UserRestController {
	private static final Log logger = LogFactory.getLog(UserRestController.class);

	@Autowired
	private IUserService userServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public @ResponseBody PageResults<SysUser> listUser(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<SysUser> searchConditions = new SearchConditions<SysUser>();
		searchConditions.add(new SearchCondition("username", "like", username));

		PageResults<SysUser> pageResults = userServiceImpl.getUsers(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "users/{userid}", method = RequestMethod.GET)
	public @ResponseBody SysUserModel getUser(
			@PathVariable("userid") String userid) {
		logger.info("获取人员信息userid=" + userid);
		
		SysUser user = userServiceImpl.getUser(userid);
		
		SysUserModel userModel = new SysUserModel(user);
		
		return userModel;
	}
	
	@RequestMapping(value = "users", method = RequestMethod.POST)
	public @ResponseBody Object addUser(
			@RequestBody SysUserModel userModel) {
		logger.info("注册人员信息");
		
		JSONObject jsonObject = new JSONObject();

		try {
			valid(userModel);
			
			SysUser user = userModel.getSysUser();
			
			if (user.getUserid() == null || user.getUserid().trim().length() == 0) {
				user.setUserid(CounterUtil.increment(SysUser.class.getName()));
			}
			
			userServiceImpl.addUser(user);
			
			jsonObject.put("msg", "注册人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "users/{userid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateUser(
			@PathVariable("userid") String userid, 
			@RequestBody SysUserModel userModel) {
		logger.info("更新人员信息id=" + userid);
		
		JSONObject jsonObject = new JSONObject();

		try {
			valid(userModel);
			
			SysUser user = userServiceImpl.getUser(userid);
			
			user.setUsername(userModel.getSysUser().getUsername());
			user.setEmail(userModel.getSysUser().getEmail());
			user.setMobile(userModel.getSysUser().getMobile());
			if (userModel.getSysUser().getPassword() != null && userModel.getSysUser().getPassword().length() > 0){
				user.setPassword(userModel.getSysUser().getPassword());
			}
			
			userServiceImpl.updateUser(user);
	
			jsonObject.put("msg", "更新人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "users/{userid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("userid") String userid) {
		logger.info("删除人员信息id=" + userid);

		JSONObject jsonObject = new JSONObject();

		try {
			userServiceImpl.deleteUser(userid);
	
			jsonObject.put("msg", "删除人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(SysUserModel userModel) throws Exception {
		if (userModel == null || userModel.getSysUser() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (userModel.getIsNew()) {
			if (userModel.getSysUser().getPassword() == null || userModel.getSysUser().getPassword().length() <= 0){
				throw new Exception("密码不能为空");
			}
			if (userModel.getPasscheck() == null || userModel.getPasscheck().length() <= 0){
				throw new Exception("确认密码不能为空");
			}
			if (!userModel.getSysUser().getPassword().equals(userModel.getPasscheck())){
				throw new Exception("密码与确认密码不一致");
			}
		} else {
			if (userModel.getSysUser().getPassword() != null && userModel.getSysUser().getPassword().length() > 0){
				if (userModel.getPasscheck() != null && userModel.getPasscheck().length() > 0){
					if (!userModel.getSysUser().getPassword().equals(userModel.getPasscheck())){
						throw new Exception("密码与确认密码不一致");
					}
				}
			}
		}
		
		// Usertype
		userModel.getSysUser().setUsertype(SysUser.class.getName());
				
		// Password
		if (userModel.getSysUser().getPassword() != null && userModel.getSysUser().getPassword().length() > 0){
			Object salt = null;
	
			if (this.saltSource != null) {
				salt = this.saltSource.getSalt(userModel.getSysUser());
			}
			userModel.getSysUser().setPassword(
				passwordEncoder.encodePassword(userModel.getSysUser().getPassword(), salt)
			);
		}
		
		return true;
	}

	@RequestMapping(value = "users/{userid}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getUserRoles(
			@PathVariable("userid") String userid) {
		logger.info("获取人员的角色信息userid=" + userid);

		SysUser user = userServiceImpl.getUser(userid);

		return user.getRoles();
	}
	
	
	@RequestMapping(value = "users/username/{username}", method = RequestMethod.GET)
	public @ResponseBody SysUser getUserByUsername(
			@PathVariable("username") String username) {
		logger.info("获取人员信息userid=" + username);

		SysUser user = userServiceImpl.getUserByUsername(username);
		return user;
	}
	
	@RequestMapping(value = "users/username/{username}/roles", method = RequestMethod.GET)
	public @ResponseBody List<SysRole> getUserRolesByUsername(
			@PathVariable("username") String username) {
		logger.info("获取人员的角色信息username=" + username);

		SysUser user = userServiceImpl.getUserByUsername(username);

		return user.getRoles();
	}

	@RequestMapping(value = "users/password", method = RequestMethod.PATCH)
	public @ResponseBody Object updateUserPasswordByUsername(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newpassword", required = true) String newpassword,
			@RequestParam(value = "confirmpassword", required = true) String confirmpassword) {
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
			
			user.setPassword(passwordEncoder.encodePassword(newpassword, salt));

			userServiceImpl.updateUser(user);

			jsonObject.put("msg", "更新帐户密码成功");
		} catch (Exception e) {
			jsonObject.put("msg", "更新帐户密码失败：" + e.getMessage());
		}

		return jsonObject;
	}
	
	@RequestMapping(value = "users/current", method = RequestMethod.GET)
	public @ResponseBody Object current() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		
		SysUser user = (SysUser) authentication.getPrincipal();
		String username = user.getUsername();

		return authentication;
	}
	
}
