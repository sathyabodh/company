package com.sathyabodh.company.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Organization {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Transient
	private String companyName ;
	
	@ManyToOne
	@JoinColumn(name="company_id", referencedColumnName="id")
	private Company company;
	
	@OneToMany(mappedBy="organization",cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Department> departments;
	
	@PostLoad
	private void init(){
		this.companyName = getCompany().getName();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public List<Department> getDepartments() {
		return departments;
	}
}
