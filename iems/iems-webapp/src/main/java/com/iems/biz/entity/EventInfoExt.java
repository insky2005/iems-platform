package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="EventInfoExt")
@Table(name="TB_EVENTINFO_EXT")
public class EventInfoExt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5242968449838374512L;

	/**
	 * 编号
	 */
	@Id
	@Column(name="EVENTID")
	private String eventid;
	
	/**
	 * 活动详情
	 */
	@Column(name="TXT", length=100)
	private String txt;

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

}
