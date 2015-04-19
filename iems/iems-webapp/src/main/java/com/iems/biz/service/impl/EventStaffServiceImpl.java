package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.IEventStaffDao;
import com.iems.biz.entity.EventStaff;
import com.iems.biz.model.EventStaffModel;
import com.iems.biz.service.IEventStaffService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class EventStaffServiceImpl implements IEventStaffService {
	
	@Autowired
	private IEventStaffDao eventStaffDaoImpl;
	
	@Override
	public PageResults<EventStaffModel> getEventStaffModels(int pageNo, int pageSize,
			SearchConditions<EventStaffModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.EventStaffModel(companyInfo, eventInfo, eventStaff) from CompanyInfo companyInfo, EventInfo eventInfo, EventStaff eventStaff where companyInfo.id=eventStaff.companyid and eventInfo.id=eventStaff.eventid";
		String hqlCount = "select count(*) from CompanyInfo companyInfo, EventInfo eventInfo, EventStaff eventStaff where companyInfo.id=eventStaff.companyid and eventInfo.id=eventStaff.eventid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventStaffDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<EventStaff> getEventStaffs(int pageNo, int pageSize,
			SearchConditions<EventStaff> searchConditions) {
		String hqlSelect = "from EventStaff where 1=1";
		String hqlCount = "select count(*) from EventStaff where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventStaffDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public EventStaff getEventStaff(String id) {
		return eventStaffDaoImpl.get(id);
	}

	@Override
	public void addEventStaff(EventStaff eventStaff) {
		eventStaffDaoImpl.save(eventStaff);
	}

	@Override
	public void updateEventStaff(EventStaff eventStaff) {
		eventStaffDaoImpl.update(eventStaff);
	}

	@Override
	public void deleteEventStaff(String id) {
		eventStaffDaoImpl.deleteById(id);
	}

}
