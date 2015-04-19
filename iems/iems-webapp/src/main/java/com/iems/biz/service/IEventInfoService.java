package com.iems.biz.service;

import com.iems.biz.entity.EventInfo;
import com.iems.biz.model.EventInfoModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface IEventInfoService {

	PageResults<EventInfoModel> getEventInfoModels(int pageNo, int pageSize, 
			SearchConditions<EventInfoModel> searchConditions);
  
	
	PageResults<EventInfo> getEventInfos(int pageNo, int pageSize, 
			SearchConditions<EventInfo> searchConditions);

	EventInfo getEventInfo(String id);

	void addEventInfo(EventInfo eventInfo);

	void updateEventInfo(EventInfo eventInfo);

	void deleteEventInfo(String id);
}
