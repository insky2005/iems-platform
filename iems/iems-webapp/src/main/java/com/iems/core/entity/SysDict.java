package com.iems.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="SysDict")
@Table(name="SYS_DICT")
public class SysDict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5952365623434003401L;

	@Id
	@Column(name="DICTID", length=40)
	private String dictid;
	
	@ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "DICTPID") 
	private SysDict dictparent;
	
	@Column(name="DICTCODE", length=100)
	private String dictcode;
	
	@Column(name="DICTNAME", length=100)
	private String dictname;
	
	@Column(name="DICTORDER")
	private Integer dictorder;
	
	@Column(name="DICTVALUE1", length=100)
	private String dictvalue1;
	
	@Column(name="DICTVALUE2", length=100)
	private String dictvalue2;
	
	@Column(name="DICTVALUE3", length=100)
	private String dictvalue3;
	
	@Column(name="DICTVALUE4", length=100)
	private String dictvalue4;
	
	@OneToMany(targetEntity = SysDict.class, cascade = { CascadeType.ALL }, mappedBy = "dictparent")  
    @Fetch(FetchMode.SUBSELECT)  
    @OrderBy("dictorder") 
    private List<SysDict> dictchildren;

	public String getDictid() {
		return dictid;
	}

	public void setDictid(String dictid) {
		this.dictid = dictid;
	}

	@JsonIgnore
	public SysDict getDictparent() {
		return dictparent;
	}

	public void setDictparent(SysDict dictparent) {
		this.dictparent = dictparent;
	}

	public String getDictcode() {
		return dictcode;
	}

	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}

	public String getDictname() {
		return dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	public Integer getDictorder() {
		return dictorder;
	}

	public void setDictorder(Integer dictorder) {
		this.dictorder = dictorder;
	}

	public String getDictvalue1() {
		return dictvalue1;
	}

	public void setDictvalue1(String dictvalue1) {
		this.dictvalue1 = dictvalue1;
	}

	public String getDictvalue2() {
		return dictvalue2;
	}

	public void setDictvalue2(String dictvalue2) {
		this.dictvalue2 = dictvalue2;
	}

	public String getDictvalue3() {
		return dictvalue3;
	}

	public void setDictvalue3(String dictvalue3) {
		this.dictvalue3 = dictvalue3;
	}

	public String getDictvalue4() {
		return dictvalue4;
	}

	public void setDictvalue4(String dictvalue4) {
		this.dictvalue4 = dictvalue4;
	}

	public List<SysDict> getDictchildren() {
		return dictchildren;
	}

	public void setDictchildren(List<SysDict> dictchildren) {
		this.dictchildren = dictchildren;
	} 
	
	
	
}
