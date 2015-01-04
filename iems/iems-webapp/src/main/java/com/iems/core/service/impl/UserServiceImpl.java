package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.dao.support.PageResults;
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

	@Override
	public PageResults<SysUser> getUsers(String username, 
			int pageNo, int pageSize) {
		String hql = "from SysUser where 1=1";
		String countHql = "select count(*) from SysUser where 1=1";

		if (username != null) {
			username = "".concat("%").concat(username).concat("%");
			hql += " and username like ?";
			countHql += " and username like ?";
		}

		return sysUserDaoImpl.findPageByFetchedHql(hql, countHql, pageNo, pageSize, username);
	}

	@Override
	public SysUser getUser(String userid) {
		return sysUserDaoImpl.get(userid);
	}

	@Override
	public void addUser(SysUser user) {
		sysUserDaoImpl.save(user);
	}

	@Override
	public void updateUser(SysUser user) {
		sysUserDaoImpl.update(user);
	}

	@Override
	public void deleteUser(String userid) {
		sysUserDaoImpl.deleteById(userid);
	}

	@Override
	public SysUser getUserByUsername(String username) {
		SysUser sysUser = this.sysUserDaoImpl.loadUserByUsername(username);

		if (sysUser == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return sysUser;
	}

}
