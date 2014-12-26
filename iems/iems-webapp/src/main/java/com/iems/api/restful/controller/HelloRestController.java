package com.iems.api.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;

@Controller
public class HelloRestController {
	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		return "你好！hello";
	}

	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody String say(@PathVariable(value = "msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@Autowired
	private IUserService userServiceImpl;

	@RequestMapping(value = "/jsonfeed", produces = "application/json;charset=UTF-8")
	public @ResponseBody Object getJSON(Model model) {
		List<SysUser> userList = userServiceImpl.loadUsers();
		model.addAttribute("items", userList);
		model.addAttribute("status", 0);

		return model;
	}

}