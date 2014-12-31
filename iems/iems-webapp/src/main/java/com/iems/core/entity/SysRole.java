package com.iems.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

@Entity(name="SysRole")
@Table(name="SYS_ROLE")
public class SysRole implements GrantedAuthority, ConfigAttribute, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8188036072737558973L;

	@Override
	public String getAuthority() {
		return rolecode;
	}

	@Override
	public String getAttribute() {
		return rolecode;
	}
	
	@Id
	@Column(name="ROLEID")
	private String roleid;
	
	@Column(name="ROLECODE", length=100, unique=true)
	private String rolecode;
	@Column(name="ROLENAME", length=100)
	private String rolename;
	
	@ManyToMany
	@JoinTable(name="SYS_USERROLE", 
		joinColumns={ @JoinColumn(name="ROLEID")},
		inverseJoinColumns={ @JoinColumn(name="USERID")}
	)
	private List<SysUser> users;
	
	@ManyToMany
	@JoinTable(name="SYS_ROLERESOURCE", 
		joinColumns={ @JoinColumn(name="ROLEID")},
		inverseJoinColumns={ @JoinColumn(name="RESOURCEID")}
	)
	private List<SysResource> resources;
	
	
	public SysRole() {
		
	}
	
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<SysUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysUser> users) {
		this.users = users;
	}

	public List<SysResource> getResources() {
		return resources;
	}

	public void setResources(List<SysResource> resources) {
		this.resources = resources;
	}
	
	

}
