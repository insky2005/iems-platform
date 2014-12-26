package com.iems.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Override
	public List<SysUser> loadUsers() {
		List<SysUser> userList = new ArrayList<SysUser>();
		// User user;
		int i = 0;
		while (i++ < 10) {
			SysUser user = new SysUser(String.valueOf(i), "username" + i, "123456");
			userList.add(user);
		}
		return userList;
	}

}
