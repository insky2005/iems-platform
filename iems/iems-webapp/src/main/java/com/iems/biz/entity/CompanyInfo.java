package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CompanyInfo")
@Table(name="TB_COMPANYINFO")
public class CompanyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7621124174525109642L;

	@Id
	@Column(name="ID", length=40)
	private String id;
	
	/**
	 * 公司名称
	 */
	@Column(name="NAME", length=200)
	private String name;
	
	/**
	 * 公司类型
	 */
	@Column(name="TYPE", length=50)
	private String type;
	
	/**
	 * 所属行业
	 */
	@Column(name="INDUSTRY", length=50)
	private String industry;
	
	/**
	 * 经营模式
	 */
	@Column(name="BUSINESS_MODEL", length=50)
	private String businessModel;
	
	/**
	 * 公司规模(1xx-2xx人)
	 */
	@Column(name="SIZE", length=50)
	private String size;
	
	/**
	 * 注册资本
	 */
	@Column(name="REGISTERED_CAPITAL", length=50)
	private String registeredCapital;
	
	/**
	 * 所在地区：省、直辖市、自治区
	 */
	@Column(name="PROVINCE", length=50)
	private String province;
	
	/**
	 * 公司地址
	 */
	@Column(name="ADDRESS", length=200)
	private String address;
	
	/**
	 * 联系电话
	 */
	@Column(name="TELEPHONE", length=50)
	private String telephone;
	
	/**
	 * 传真号码
	 */
	@Column(name="FAX", length=50)
	private String fax;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}
