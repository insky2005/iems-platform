package com.iems.core.util;

import java.util.UUID;

import javax.annotation.Resource;

import com.iems.core.counter.dao.ISysCounterDao;
import com.iems.core.counter.entity.SysCounter;

public class CounterUtil {
	
	private String counterType = "uuid";
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCounterType() {
		return counterType;
	}

	private static CounterUtil util;
	public void init(){
		util = this;
	}
	
	@Resource(name="sysCounterDaoImpl")
	private ISysCounterDao sysCounterDaoImpl;
	
	public static String increment() {
		return increment(SysCounter.class.getName());
	}
	
	public static String increment(String name) {
		return increment(name, 0);
	}
	
	public static String increment(String name, int size) {
		if (util.counterType==null || util.counterType.equalsIgnoreCase("uuid")) {
			return UUID.randomUUID().toString().replaceAll("-", "");
		} else if (util.counterType.equalsIgnoreCase("counter")) {
			SysCounter sysCounter = null;
			synchronized (CounterUtil.class) {
				sysCounter = util.sysCounterDaoImpl.getSysCounter(name);
				if (sysCounter==null) {
					sysCounter = new SysCounter();
					sysCounter.setName(name);
					sysCounter.setCurrentId(0L);
					
					util.sysCounterDaoImpl.saveSysCounter(sysCounter);
				}

				//System.out.println(sysCounter.getCurrentId());
				sysCounter.setCurrentId(new Long(sysCounter.getCurrentId()+size+1L));
	
				util.sysCounterDaoImpl.updateSysCounter(sysCounter);
			}
			
			return sysCounter.getCurrentId().toString();
		}
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
