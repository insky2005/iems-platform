package com.iems.core.dao.support;

import java.util.ArrayList;
import java.util.List;

public class SearchConditions<T> {

	private List<SearchCondition> conditions = new ArrayList<SearchCondition>();
	
	public boolean add(SearchCondition condition) {
		return this.conditions.add(condition);
	}

	public String getConditionHql() {
		
		String conditionHql = "";
		
		for (SearchCondition condition : conditions) {
			conditionHql += condition.getConditionHql();
		}
		
		return conditionHql;

	}
	
	public Object[] getConditionValues() {
		
		Object[] conditionValues = new Object[conditions.size()];
		int i =0;
		
		for (SearchCondition condition : conditions) {
			if (condition.getConditionValue() == null) 
				continue;
			
			conditionValues[i] = condition.getConditionValue();
			
			i++;
		}
		
		return conditionValues;
		
	}
	
}

