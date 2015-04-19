package com.iems.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iems.core.dao.ISysDictDao;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysDict;
import com.iems.core.service.IDictService;

@Service
public class DictServiceImpl implements IDictService {

	@Autowired
	private ISysDictDao sysDictDaoImpl;

	@Override
	public PageResults<SysDict> getDicts(int pageNo, int pageSize, 
			SearchConditions<SysDict> searchConditions) {
		String hqlSelect = "from SysDict where 1=1";
		String hqlCount = "select count(*) from SysDict where 1=1";
		
		String whereClause = searchConditions.getConditionHql();
		
		Object[] values = searchConditions.getConditionValues();

		hqlSelect += whereClause;
		hqlCount += whereClause;

		return sysDictDaoImpl.findPageByFetchedHql(hqlSelect, hqlCount, pageNo, pageSize, values);
	}

	@Override
	public SysDict getDict(String dictid) {
		return sysDictDaoImpl.get(dictid);
	}

	@Override
	public void addDict(SysDict dict) {
		sysDictDaoImpl.save(dict);
	}

	@Override
	public void updateDict(SysDict dict) {
		sysDictDaoImpl.update(dict);
	}

	@Override
	public void deleteDict(String dictid) {
		sysDictDaoImpl.deleteById(dictid);
	}

	@Override
	public SysDict getDictByDictcode(String dictcode) {
		SysDict sysDict = this.sysDictDaoImpl.getDictByDictcode(dictcode);

		return sysDict;
	}
}
