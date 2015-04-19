package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventPersonnel;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;

public class EventPersonnelModel extends SysUserModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2079193065179696346L;

	private CompanyInfo companyInfo;
	private EventInfo eventInfo;

	private EventPersonnel eventPersonnel;
	
	public EventPersonnelModel() {
		super();
	}
	
	public EventPersonnelModel(EventPersonnel eventPersonnel) {
		this(null, null, eventPersonnel);
	}
	
	public EventPersonnelModel(CompanyInfo companyInfo, EventInfo eventInfo, EventPersonnel eventPersonnel) {
		this(companyInfo, eventInfo, eventPersonnel, null);
	}

	public EventPersonnelModel(CompanyInfo companyInfo, EventInfo eventInfo, EventPersonnel eventPersonnel, SysUser sysUser) {
		this(eventPersonnel==null || eventPersonnel.getId()==null, companyInfo, eventInfo, eventPersonnel, sysUser);
	}

	public EventPersonnelModel(boolean isNew, CompanyInfo companyInfo, EventInfo eventInfo, EventPersonnel eventPersonnel, SysUser sysUser) {
		super(isNew, sysUser);
		
		this.companyInfo = companyInfo;
		this.eventInfo = eventInfo;

		this.eventPersonnel = eventPersonnel;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public EventPersonnel getEventPersonnel() {
		return eventPersonnel;
	}

	public void setEventPersonnel(EventPersonnel eventPersonnel) {
		this.eventPersonnel = eventPersonnel;
	}

}
