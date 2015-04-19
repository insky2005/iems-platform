package com.iems.core.dao.support;

public class SearchCondition {
	
	public enum SearchOperator {
		 // TODO 
	}

	private String field;
	
	private Object value;
	
	private String operator;
	
	
	public SearchCondition(String field, String operator, Object value) {
		if (value == null) {
			return;
		}
		
		if ("like".equalsIgnoreCase(operator)) {
			value = "%"+value+"%";
		}
		
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
	
	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public String getOperator() {
		return operator;
	}
	
	//private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss fff");
	
	public String getConditionHql() {
		if (value != null) {
			return String.format(" and %s %s ?", field, operator);
		}
		
		return "";
	}
	
	public Object getConditionValue() {
		if (value != null) {
			return value;
		}
		
		return null;
	}

}
