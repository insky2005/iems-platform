package com.iems.core.model;

public abstract class BaseModel {

	private boolean isNew = true;
	
	public BaseModel() {
		this.isNew = true;
	}
	
	public BaseModel(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
}
