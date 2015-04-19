package com.iems.biz.dao;

import com.iems.biz.entity.EventPersonnel;
import com.iems.biz.model.EventPersonnelModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface IEventPersonnelDao extends IBaseDao<EventPersonnel, String> {

	public abstract PageResults<EventPersonnelModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);

}
