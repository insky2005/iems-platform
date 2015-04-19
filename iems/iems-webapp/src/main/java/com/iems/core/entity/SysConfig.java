package com.iems.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="SysConfig")
@Table(name="SYS_CONFIG")
public class SysConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3629329576686725840L;
	
	@Id
	@Column(name="CONFIGID")
	private String configid;

	@Column(name="SYSTEMCODE", length=100)
	private String systemcode;

	@Column(name="SYSTEMNAME", length=100)
	private String systemname;

	public String getConfigid() {
		return configid;
	}

	public void setConfigid(String configid) {
		this.configid = configid;
	}

	public String getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	public String getSystemname() {
		return systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	
	
}
