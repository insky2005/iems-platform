package com.iems.biz.dao;

import com.iems.biz.entity.EventStaff;
import com.iems.biz.model.EventStaffModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface IEventStaffDao extends IBaseDao<EventStaff, String> {

	public abstract PageResults<EventStaffModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);

}
