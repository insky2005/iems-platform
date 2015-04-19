package com.iems.core.model;

import java.io.Serializable;

import com.iems.core.entity.SysUser;

public class SysUserModel extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6812281142760727915L;
	
	private SysUser sysUser;
	private String passcheck;
	
	public SysUserModel() {
		super();
	}
	
	public SysUserModel(SysUser sysUser) {
		this(sysUser==null || sysUser.getUserid()==null, sysUser);
	}

	public SysUserModel(boolean isNew, SysUser sysUser) {
		super(isNew);
		
		this.sysUser = sysUser;
	}

	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public String getPasscheck() {
		return passcheck;
	}
	public void setPasscheck(String passcheck) {
		this.passcheck = passcheck;
	}
	
}
