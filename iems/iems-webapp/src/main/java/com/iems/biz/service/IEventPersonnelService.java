package com.iems.biz.service;

import com.iems.biz.entity.EventPersonnel;
import com.iems.biz.model.EventPersonnelModel;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;

public interface IEventPersonnelService {

	PageResults<EventPersonnelModel> getEventPersonnelModels(int pageNo, int pageSize, 
			SearchConditions<EventPersonnelModel> searchConditions);
  
	
	PageResults<EventPersonnel> getEventPersonnels(int pageNo, int pageSize, 
			SearchConditions<EventPersonnel> searchConditions);

	EventPersonnel getEventPersonnel(String id);

	void addEventPersonnel(EventPersonnel eventPersonnel);

	void updateEventPersonnel(EventPersonnel eventPersonnel);

	void deleteEventPersonnel(String id);
}
