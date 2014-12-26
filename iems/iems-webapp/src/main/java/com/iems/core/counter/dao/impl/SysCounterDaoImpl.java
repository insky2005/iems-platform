package com.iems.core.counter.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.iems.core.counter.dao.ISysCounterDao;
import com.iems.core.counter.entity.SysCounter;

@Repository
public class SysCounterDaoImpl implements ISysCounterDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public SysCounter getSysCounter(String name) {
		try {
			Session session = getSession();
			return (SysCounter) session.get(SysCounter.class, name);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
		}
	}

	@Override
	public void saveSysCounter(SysCounter sysCounter) {
		try {
			Session session = getSession();
			session.save(sysCounter);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		} finally {
		}
	}

	@Override
	public void updateSysCounter(SysCounter sysCounter) {
		try {
			Session session = getSession();
			session.update(sysCounter);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
