package com.iems.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.access.ConfigAttribute;

import com.iems.security.access.resourcedetails.ResourceDetails;

@Entity(name="SysResource")
@Table(name="SYS_RESOURCE")
public class SysResource implements ResourceDetails, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5831830955244453532L;
	
	@Id
	@Column(name="RESOURCEID")
	private String resourceid;
	
	@Column(name="RESOURCECODE", length=100, unique=true)
	private String resourcecode;
	@Column(name="RESOURCENAME", length=100)
	private String resourcename;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="ENABLED")
	private boolean enabled;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SYS_ROLERESOURCE", 
		joinColumns={ @JoinColumn(name="RESOURCEID")},
		inverseJoinColumns={ @JoinColumn(name="ROLEID")}
	)
	private List<SysRole> roles;
	
	@Override
	@JsonIgnore
	public Collection<? extends ConfigAttribute> getAttributes() {
		return roles;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getResourcecode() {
		return resourcecode;
	}

	public void setResourcecode(String resourcecode) {
		this.resourcecode = resourcecode;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	@Override
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonIgnore
	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	
}
