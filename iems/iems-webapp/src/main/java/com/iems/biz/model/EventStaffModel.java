package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventStaff;
import com.iems.core.entity.SysUser;
import com.iems.core.model.SysUserModel;

public class EventStaffModel extends SysUserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6622264895287408654L;
	
	private CompanyInfo companyInfo;
	private EventInfo eventInfo;
	private CompanyEmployee companyEmployee;
	
	private EventStaff eventStaff;
	
	public EventStaffModel() {
		super();
	}
	
	public EventStaffModel(EventStaff eventStaff) {
		this(null, null, eventStaff);
	}

	public EventStaffModel(CompanyInfo companyInfo, EventInfo eventInfo, EventStaff eventStaff) {
		this(companyInfo, eventInfo, eventStaff, null, null);
	}

	public EventStaffModel(CompanyInfo companyInfo, EventInfo eventInfo, EventStaff eventStaff, CompanyEmployee companyEmployee, SysUser sysUser) {
		this(eventInfo==null || eventInfo.getId()==null, companyInfo, eventInfo, eventStaff, companyEmployee, sysUser);
	}

	public EventStaffModel(boolean isNew, CompanyInfo companyInfo, EventInfo eventInfo, EventStaff eventStaff, CompanyEmployee companyEmployee, SysUser sysUser) {
		super(isNew, sysUser);
		
		this.companyInfo = companyInfo;
		this.eventInfo = eventInfo;
		this.companyEmployee = companyEmployee;
		
		this.eventStaff = eventStaff;
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

	public CompanyEmployee getCompanyEmployee() {
		return companyEmployee;
	}

	public void setCompanyEmployee(CompanyEmployee companyEmployee) {
		this.companyEmployee = companyEmployee;
	}

	public EventStaff getEventStaff() {
		return eventStaff;
	}

	public void setEventStaff(EventStaff eventStaff) {
		this.eventStaff = eventStaff;
	}


}
