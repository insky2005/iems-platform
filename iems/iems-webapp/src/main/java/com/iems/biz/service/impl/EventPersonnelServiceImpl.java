package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.IEventPersonnelDao;
import com.iems.biz.entity.EventPersonnel;
import com.iems.biz.model.EventPersonnelModel;
import com.iems.biz.service.IEventPersonnelService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class EventPersonnelServiceImpl implements IEventPersonnelService {
	
	@Autowired
	private IEventPersonnelDao eventPersonnelDaoImpl;
	
	@Override
	public PageResults<EventPersonnelModel> getEventPersonnelModels(int pageNo, int pageSize,
			SearchConditions<EventPersonnelModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.EventPersonnelModel(companyInfo, eventInfo, eventPersonnel) from CompanyInfo companyInfo, EventInfo eventInfo, EventPersonnel eventPersonnel where companyInfo.id=eventPersonnel.companyid and eventInfo.id=eventPersonnel.eventid";
		String hqlCount = "select count(*) from CompanyInfo companyInfo, EventInfo eventInfo, EventPersonnel eventPersonnel where companyInfo.id=eventPersonnel.companyid and eventInfo.id=eventPersonnel.eventid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventPersonnelDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<EventPersonnel> getEventPersonnels(int pageNo, int pageSize,
			SearchConditions<EventPersonnel> searchConditions) {
		String hqlSelect = "from EventPersonnel where 1=1";
		String hqlCount = "select count(*) from EventPersonnel where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventPersonnelDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public EventPersonnel getEventPersonnel(String id) {
		return eventPersonnelDaoImpl.get(id);
	}

	@Override
	public void addEventPersonnel(EventPersonnel eventPersonnel) {
		eventPersonnelDaoImpl.save(eventPersonnel);
	}

	@Override
	public void updateEventPersonnel(EventPersonnel eventPersonnel) {
		eventPersonnelDaoImpl.update(eventPersonnel);
	}

	@Override
	public void deleteEventPersonnel(String id) {
		eventPersonnelDaoImpl.deleteById(id);
	}

}
