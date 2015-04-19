package com.iems.core.dao;

import com.iems.core.entity.SysDict;

public interface ISysDictDao extends IBaseDao<SysDict, String> {

	SysDict getDictByDictcode(String dictcode);

}
