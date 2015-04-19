package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="EventInfo")
@Table(name="TB_EVENTINFO")
public class EventInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3665854688018434130L;

	
	/**
	 * 编号
	 */
	@Id
	@Column(name="ID")
	private String id;
	
	/**
	 * 所属企业
	 */
	@Column(name="COMPANYID", length=40)
	private String companyid;
	
	/**
	 * 活动名称
	 */
	@Column(name="NAME", length=100)
	private String name;
	
	/**
	 * 活动备注
	 */
	@Column(name="MEMO", length=100)
	private String memo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}
