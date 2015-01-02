package com.sathyabodh.company.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="name", unique=true)
	private String name;
	
	@Column(name="start_date")
	private long startDate;
	
	@Column(name="end_date")
	private long endDate;
	
	@JsonIgnore
	@OneToMany(mappedBy="company", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Organization> organizations;
	
	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

}
