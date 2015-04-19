package com.iems.biz.dao;

import com.iems.biz.entity.EventInfo;
import com.iems.biz.model.EventInfoModel;
import com.iems.core.dao.IBaseDao;
import com.iems.core.dao.support.PageResults;

public interface IEventInfoDao extends IBaseDao<EventInfo, String> {

	public abstract PageResults<EventInfoModel> findObjectPageByFetchedHql(String hql,
			String countHql, int pageNo, int pageSize, Object... values);

}
