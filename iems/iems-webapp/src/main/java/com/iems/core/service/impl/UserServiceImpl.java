package com.iems.core.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysUserDao;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

	@Autowired
	private ISysUserDao sysUserDaoImpl;
	
	private boolean isMobile(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	private boolean isEmail(String email){
		Pattern p = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		SysUser sysUser = null;
		
		if (username == null) {
			sysUser = null;
		} else if (isEmail(username)) {
			sysUser = this.sysUserDaoImpl.getUserByEmail(username);
		} else if (isMobile(username)) {
			sysUser = this.sysUserDaoImpl.getUserByMobile(username);
		} else {
			sysUser = this.sysUserDaoImpl.getUserByUsername(username);
		}

		if (sysUser == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return (UserDetails) sysUser;
	}

	@Override
	public PageResults<SysUser> getUsers(int pageNo, int pageSize, 
			SearchConditions<SysUser> searchConditions) {
		String hqlSelect = "from SysUser where 1=1";
		String hqlCount = "select count(*) from SysUser where 1=1";

		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;

		return sysUserDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
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
		SysUser sysUser = this.sysUserDaoImpl.getUserByUsername(username);

		if (sysUser == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}

		return sysUser;
	}

}
