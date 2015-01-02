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
import javax.persistence.Transient;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Transient
	private long orgId;
	
	@ManyToOne
	@JoinColumn(name="org_id", referencedColumnName="id")
	private Organization organization;

//	@OneToMany(mappedBy="department", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
//	private List<Employee> employees;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
//	public List<Employee> getEmployees() {
//		return employees;
//	}
}
