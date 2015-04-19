package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="EventPersonnel")
@Table(name="TB_EVENTPERSONNEL")
public class EventPersonnel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5523273038767117409L;

	/**
	 * 编号
	 */
	@Id
	@Column(name="ID")
	private String id;
	
	/**
	 * 所属企业
	 */
	@Column(name="COMPANYID", length=40, unique=true)
	private String companyid;
	
	/**
	 * 所属活动
	 */
	@Column(name="EVENTID", length=40)
	private String eventid;

	/**
	 * 用户ID
	 */
	@Column(name="USERID", length=40)
	private String userid;

	/**
	 * 类型 3 参会人员
	 */
	@Column(name="TYPE", length=10)
	private String type;

	/**
	 * 姓名
	 */
	@Column(name="NAME", length=100)
	private String name;
	
	/**
	 * 联系电话（+分机）
	 */
	@Column(name="TELEPHONE", length=50)
	private String telephone;
	
	/**
	 * QQ
	 */
	@Column(name="QQ", length=50)
	private String qq;
	
	/**
	 * 微信号
	 */
	@Column(name="WECHAT", length=50)
	private String wechat;

	/**
	 * 邮箱
	 */
	@Column(name="EMAIL", length=100)
	private String email;
	
	/**
	 * 手机
	 */
	@Column(name="MOBILE", length=20)
	private String mobile;
	
	/**
	 * 活动职责
	 */
	@Column(name="DUTY", length=500)
	private String duty;
	/**
	 * 备注
	 */
	@Column(name="MEMO", length=500)
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
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	
}
