package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CompanyEmployee")
@Table(name="TB_COMPANYEMPLOYEE")
public class CompanyEmployee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880141545344354641L;

	@Id
	@Column(name="ID", length=40)
	private String id;
	
	@Column(name="COMPANYID", length=40)
	private String companyid;
	
	@Column(name="USERID", length=40, unique=true)
	private String userid;
	
	@Column(name="NAME", length=100)
	private String name;
	

	/**
	 * 工号
	 */
	@Column(name="STAFFNO", length=50)
	private String staffno;
	
	/**
	 * 部门
	 */
	@Column(name="DEPARTMENT", length=100)
	private String department;
	
	/**
	 * 职位
	 */
	@Column(name="JOB", length=100)
	private String job;
	
	/**
	 * 联系电话（+分机）
	 */
	@Column(name="TELEPHONE", length=50)
	private String telephone;
	
	/**
	 * 邮箱
	 */
	@Column(name="EMAIL", length=50)
	private String email;
	
	/**
	 * 手机
	 */
	@Column(name="MOBILE", length=50)
	private String mobile;
	
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStaffno() {
		return staffno;
	}

	public void setStaffno(String staffno) {
		this.staffno = staffno;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
}
