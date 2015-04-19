package com.iems.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="SysUser")
@Table(name="SYS_USER")
public class SysUser implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5331275719227162188L;
	
	@Id
	@Column(name="USERID")
	private String userid;

	/**
	 * 不能为带有 @ 的字符串
	 * 不能为 11位纯数字的字符串
	 */
	@Column(name="USERNAME", length=100, unique=true)
	private String username;
	
	@Column(name="PASSWORD", length=500)
	private String password;
	
	@Column(name="USERTYPE", length=50)
	private String usertype;
	
	@Column(name="ENABLED")
	private boolean enabled = true;
	@Column(name="ACCOUNT_NON_EXPIRED")
	private boolean accountNonExpired = true;
	@Column(name="ACCOUNT_NON_LOCKED")
	private boolean accountNonLocked = true;
	@Column(name="CREDENTIALS_NON_EXPIRED")
	private boolean credentialsNonExpired = true;
	
	@Column(name="EMAIL", length=100, unique=true)
	private String email;
	@Column(name="MOBILE", length=20, unique=true)
	private String mobile;
	
	@ManyToMany
	@JoinTable(name="SYS_USERROLE", 
		joinColumns={ @JoinColumn(name="USERID")},
		inverseJoinColumns={ @JoinColumn(name="ROLEID")}
	)
	private List<SysRole> roles;

	public SysUser() {
		
	}
	
	public SysUser(String userid, String username, String password, String usertype, boolean enabled, String email, String mobile) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		
		this.enabled = enabled;
		
		this.email = email;
		this.mobile = mobile;
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
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public String getUsertype() {
		return usertype;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
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

	
	public String getEmail() {
		return email;
	}
	
	public String getMobile() {
		return mobile;
	}

	
	@JsonIgnore
	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	
}
