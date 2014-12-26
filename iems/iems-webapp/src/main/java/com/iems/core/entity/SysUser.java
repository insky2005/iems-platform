package com.iems.core.entity;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
public class SysUser implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5331275719227162188L;
	
	private String userid;
	private String username;
	private String password;
	
	private boolean enabled = true;
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	
	
	private Collection<GrantedAuthority>  authorities;
	
	
	public SysUser(String userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	
	
}
