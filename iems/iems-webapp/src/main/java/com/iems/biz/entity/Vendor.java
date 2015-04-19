package com.iems.biz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="Vendor")
@Table(name="TB_VENDOR")
public class Vendor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -900425427909588400L;

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="USERID", length=40, unique=true)
	private String userid;
	
	@Column(name="NAME", length=100)
	private String name;
}
