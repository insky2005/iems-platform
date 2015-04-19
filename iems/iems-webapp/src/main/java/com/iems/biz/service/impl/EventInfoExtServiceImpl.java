package com.iems.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.biz.dao.IEventInfoExtDao;
import com.iems.biz.entity.EventInfoExt;
import com.iems.biz.service.IEventInfoExtService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

@Service
public class EventInfoExtServiceImpl implements IEventInfoExtService {
	
	@Autowired
	private IEventInfoExtDao eventInfoExtDaoImpl;
	
	@Override
	public PageResults<EventInfoExt> getEventInfoExts(int pageNo, int pageSize,
			SearchConditions<EventInfoExt> searchConditions) {
		String hqlSelect = "from EventInfoExt where 1=1";
		String hqlCount = "select count(*) from EventInfoExt where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();
		
		hqlSelect += whereClause;
		hqlCount += whereClause;
		
		return eventInfoExtDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public EventInfoExt getEventInfoExt(String id) {
		return eventInfoExtDaoImpl.get(id);
	}

	@Override
	public void addEventInfoExt(EventInfoExt eventInfoExt) {
		eventInfoExtDaoImpl.save(eventInfoExt);
	}

	@Override
	public void updateEventInfoExt(EventInfoExt eventInfoExt) {
		eventInfoExtDaoImpl.update(eventInfoExt);
	}

	@Override
	public void deleteEventInfoExt(String id) {
		eventInfoExtDaoImpl.deleteById(id);
	}

}
