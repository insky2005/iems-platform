package com.iems.core.service;

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysDict;

public interface IDictService {

	PageResults<SysDict> getDicts(int pageNo, int pageSize, 
			SearchConditions<SysDict> searchConditions);

	SysDict getDict(String dictid);

	void addDict(SysDict dict);

	void updateDict(SysDict dict);

	void deleteDict(String dictid);

	SysDict getDictByDictcode(String dictcode);
	
}
