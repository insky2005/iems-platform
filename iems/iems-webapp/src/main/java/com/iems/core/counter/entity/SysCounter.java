package com.iems.core.counter.entity;

public class SysCounter implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5025756616846949292L;

	private String name;
	private Long currentId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCurrentId() {
		return currentId;
	}
	public void setCurrentId(Long currentId) {
		this.currentId = currentId;
	}

}
