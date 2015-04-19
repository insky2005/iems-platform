package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.IEventInfoDao;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.model.EventInfoModel;
import com.iems.biz.service.IEventInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class EventInfoServiceImpl implements IEventInfoService {
	
	@Autowired
	private IEventInfoDao eventInfoDaoImpl;
	
	@Override
	public PageResults<EventInfoModel> getEventInfoModels(int pageNo, int pageSize,
			SearchConditions<EventInfoModel> searchConditions) {
		String hqlSelect = "select new com.iems.biz.model.EventInfoModel(companyInfo, eventInfo) from CompanyInfo companyInfo, EventInfo eventInfo where companyInfo.id=eventInfo.companyid";
		String hqlCount = "select count(*) from CompanyInfo companyInfo, EventInfo eventInfo where companyInfo.id=eventInfo.companyid";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventInfoDaoImpl.findObjectPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}
	
	@Override
	public PageResults<EventInfo> getEventInfos(int pageNo, int pageSize,
			SearchConditions<EventInfo> searchConditions) {
		String hqlSelect = "from EventInfo where 1=1";
		String hqlCount = "select count(*) from EventInfo where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventInfoDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public EventInfo getEventInfo(String id) {
		return eventInfoDaoImpl.get(id);
	}

	@Override
	public void addEventInfo(EventInfo eventInfo) {
		eventInfoDaoImpl.save(eventInfo);
	}

	@Override
	public void updateEventInfo(EventInfo eventInfo) {
		eventInfoDaoImpl.update(eventInfo);
	}

	@Override
	public void deleteEventInfo(String id) {
		eventInfoDaoImpl.deleteById(id);
	}

}
