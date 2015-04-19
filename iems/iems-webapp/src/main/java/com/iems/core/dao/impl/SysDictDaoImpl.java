package com.iems.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.iems.core.dao.ISysDictDao;
import com.iems.core.entity.SysDict;

@Repository
public class SysDictDaoImpl extends BaseDaoImpl<SysDict, String> implements ISysDictDao {

	@Override
	public SysDict getDictByDictcode(String dictcode) {
		String hqlString = "from SysDict where dictcode=?";
				
		SysDict sysDict = super.getByHQL(hqlString, dictcode);
		
		return sysDict;
	}

}
