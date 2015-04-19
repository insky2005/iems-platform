package com.iems.biz.service;

import com.iems.biz.entity.EventStaff;
import com.iems.biz.model.EventStaffModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface IEventStaffService {

	PageResults<EventStaffModel> getEventStaffModels(int pageNo, int pageSize, 
			SearchConditions<EventStaffModel> searchConditions);
  
	
	PageResults<EventStaff> getEventStaffs(int pageNo, int pageSize, 
			SearchConditions<EventStaff> searchConditions);

	EventStaff getEventStaff(String id);

	void addEventStaff(EventStaff eventStaff);

	void updateEventStaff(EventStaff eventStaff);

	void deleteEventStaff(String id);
}
