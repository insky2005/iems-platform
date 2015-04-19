package com.iems.biz.model;

import java.io.Serializable;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventInfoExt;
import com.iems.core.model.BaseModel;

public class EventInfoModel extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921948228969268115L;

	private CompanyInfo companyInfo;

	private EventInfo eventInfo;
	private EventInfoExt eventInfoExt;
	
	public EventInfoModel() {
		super();
	}
	
	public EventInfoModel(CompanyInfo companyInfo, EventInfo eventInfo) {
		this(companyInfo, eventInfo, null);
	}

	public EventInfoModel(CompanyInfo companyInfo, EventInfo eventInfo, EventInfoExt eventInfoExt) {
		this(eventInfo==null || eventInfo.getId()==null, companyInfo, eventInfo, eventInfoExt);
	}

	public EventInfoModel(boolean isNew, CompanyInfo companyInfo, EventInfo eventInfo, EventInfoExt eventInfoExt) {
		super(isNew);
		
		this.companyInfo = companyInfo;
		
		this.eventInfo = eventInfo;
		this.eventInfoExt = eventInfoExt;
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

	public EventInfoExt getEventInfoExt() {
		return eventInfoExt;
	}

	public void setEventInfoExt(EventInfoExt eventInfoExt) {
		this.eventInfoExt = eventInfoExt;
	}
}
