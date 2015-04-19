package com.iems.api.restful.controller;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iems.biz.entity.UserInfo;
import com.iems.biz.model.UserInfoModel;
import com.iems.biz.service.IUserInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class UserInfoRestController {
	private static final Log logger = LogFactory.getLog(UserInfoRestController.class);

	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IUserInfoService userInfoServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "userinfos", method = RequestMethod.GET)
	public @ResponseBody PageResults<UserInfoModel> listUserInfo(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<UserInfoModel> searchConditions = new SearchConditions<UserInfoModel>();
		searchConditions.add(new SearchCondition("userinfo.name", "like", name));

		PageResults<UserInfoModel> pageResults = userInfoServiceImpl.getUserInfoModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "userinfos/{id}", method = RequestMethod.GET)
	public @ResponseBody UserInfoModel getUserInfo(
			@PathVariable("id") String id) {
		logger.info("获取人员信息id=" + id);
		
		UserInfo userInfo = userInfoServiceImpl.getUserInfo(id);
		SysUser user = userServiceImpl.getUser(userInfo.getUserid());
		
		UserInfoModel userInfoModel = new UserInfoModel(user, userInfo);
		
		return userInfoModel;
	}
	
	@RequestMapping(value = "userinfos", method = RequestMethod.POST)
	public @ResponseBody Object addUserInfo(
			@RequestBody UserInfoModel userInfoModel) {
		logger.info("注册人员信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(userInfoModel);
			
			userServiceImpl.addUser(userInfoModel.getSysUser());
			
			userInfoServiceImpl.addUserInfo(userInfoModel.getUserInfo());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "userinfos/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateUserInfo(
			@PathVariable("id") String id, 
			@RequestBody UserInfoModel userInfoModel) {
		logger.info("更新人员信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(userInfoModel);
			
			UserInfo userInfo = userInfoServiceImpl.getUserInfo(id);
			SysUser user = userServiceImpl.getUser(userInfo.getUserid());
			
			userInfo.setName(userInfoModel.getUserInfo().getName());
			
			user.setUsername(userInfoModel.getSysUser().getUsername());
			user.setEmail(userInfoModel.getSysUser().getEmail());
			user.setMobile(userInfoModel.getSysUser().getMobile());
			if (userInfoModel.getSysUser().getPassword() != null && userInfoModel.getSysUser().getPassword().length() > 0){
				user.setPassword(userInfoModel.getSysUser().getPassword());
			}
			
			userServiceImpl.updateUser(user);
			
			userInfoServiceImpl.updateUserInfo(userInfo);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "userinfos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUserInfo(
			@PathVariable("id") String id) {
		logger.info("删除人员信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			UserInfo userInfo = userInfoServiceImpl.getUserInfo(id);
			
			userServiceImpl.deleteUser(userInfo.getUserid());
			
			userInfoServiceImpl.deleteUserInfo(userInfo.getId());
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(UserInfoModel userInfoModel) throws Exception {
		if (userInfoModel == null || userInfoModel.getSysUser() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (userInfoModel.getIsNew()) {
			if (userInfoModel.getSysUser().getPassword() == null || userInfoModel.getSysUser().getPassword().length() <= 0){
				throw new Exception("密码不能为空");
			}
			if (userInfoModel.getPasscheck() == null || userInfoModel.getPasscheck().length() <= 0){
				throw new Exception("确认密码不能为空");
			}
			if (!userInfoModel.getSysUser().getPassword().equals(userInfoModel.getPasscheck())){
				throw new Exception("密码与确认密码不一致");
			}
			
			userInfoModel.getUserInfo().setId(CounterUtil.increment(UserInfo.class.getName()));
			userInfoModel.getSysUser().setUserid(CounterUtil.increment(SysUser.class.getName()));

			userInfoModel.getUserInfo().setUserid(userInfoModel.getSysUser().getUserid());
			
		} else {
			if (userInfoModel.getSysUser().getPassword() != null && userInfoModel.getSysUser().getPassword().length() > 0){
				if (userInfoModel.getPasscheck() != null && userInfoModel.getPasscheck().length() > 0){
					if (!userInfoModel.getSysUser().getPassword().equals(userInfoModel.getPasscheck())){
						throw new Exception("密码与确认密码不一致");
					}
				}
			}
		}

		// Usertype
		userInfoModel.getSysUser().setUsertype(UserInfo.class.getName());
		
		// Password
		if (userInfoModel.getSysUser().getPassword() != null && userInfoModel.getSysUser().getPassword().length() > 0){
			Object salt = null;
	
			if (this.saltSource != null) {
				salt = this.saltSource.getSalt(userInfoModel.getSysUser());
			}
			userInfoModel.getSysUser().setPassword(
				passwordEncoder.encodePassword(userInfoModel.getSysUser().getPassword(), salt)
			);
		}
		
		return true;
	}


	@RequestMapping(value = "userinfos/userid/{userid}", method = RequestMethod.GET)
	public @ResponseBody UserInfoModel getUserInfoByUserid(
			@PathVariable("userid") String userid) {
		logger.info("获取人员信息userid=" + userid);

		UserInfo userInfo = userInfoServiceImpl.getUserInfoByUserid(userid);
		SysUser user = userServiceImpl.getUser(userid);
		
		UserInfoModel userInfoModel = new UserInfoModel(user, userInfo);
		
		return userInfoModel;
	}
}
