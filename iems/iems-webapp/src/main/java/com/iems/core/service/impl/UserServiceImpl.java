package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	private ISysUserDao sysUserDaoImpl;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		SysUser sysUser = this.sysUserDaoImpl.loadUserByUsername(username);

		if (sysUser == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return (UserDetails) sysUser;
	}

}
