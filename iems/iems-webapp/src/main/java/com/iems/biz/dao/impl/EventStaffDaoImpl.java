package com.iems.biz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.springframework.stereotype.Repository;

import com.iems.biz.dao.IEventStaffDao;
import com.iems.biz.entity.EventStaff;
import com.iems.biz.model.EventStaffModel;
import com.iems.core.dao.impl.BaseDaoImpl;
import com.iems.core.dao.support.PageResults;

@Repository
public class EventStaffDaoImpl extends BaseDaoImpl<EventStaff, String> implements IEventStaffDao {

	@Override
	public PageResults<EventStaffModel> findObjectPageByFetchedHql(String hql, String countHql,
			int pageNo, int pageSize, Object... values) {
		PageResults<EventStaffModel> retValue = new PageResults<EventStaffModel>();
		Query query = this.getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null) {
					query.setParameter(i, values[i]);
				}
			}
		}
		int currentPage = pageNo > 1 ? pageNo : 1;
		retValue.setCurrentPage(currentPage);
		retValue.setPageSize(pageSize);
		if (countHql == null) {
			ScrollableResults results = query.scroll();
			results.last();
			retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
		} else {
			Long count = countByHql(countHql, values);
			retValue.setTotalCount(count.intValue());
		}
		retValue.resetPageNo();
		List<EventStaffModel> itemList = query.setFirstResult((currentPage - 1) * pageSize)
				.setMaxResults(pageSize).list();
		if (itemList == null) {
			itemList = new ArrayList<EventStaffModel>();
		}
		retValue.setResults(itemList);

		return retValue;
	}
	

}
