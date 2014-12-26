package com.iems.core.counter.dao;

import com.iems.core.counter.entity.SysCounter;

public interface ISysCounterDao {
	
	public SysCounter getSysCounter(String name);

	public void saveSysCounter(SysCounter sysCounter);
	
	public void updateSysCounter(SysCounter sysCounter);
}
