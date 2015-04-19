package com.iems.biz.service;

import com.iems.biz.entity.EventInfoExt;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface IEventInfoExtService {
	
	PageResults<EventInfoExt> getEventInfoExts(int pageNo, int pageSize, 
			SearchConditions<EventInfoExt> searchConditions);

	EventInfoExt getEventInfoExt(String id);

	void addEventInfoExt(EventInfoExt eventInfoExt);

	void updateEventInfoExt(EventInfoExt eventInfoExt);

	void deleteEventInfoExt(String id);
}
