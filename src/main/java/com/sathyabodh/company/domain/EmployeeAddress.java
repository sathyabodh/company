package com.sathyabodh.company.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="emp_address")
public class EmployeeAddress {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="address", nullable=false)
	private String address;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@Column(name="state", nullable=false)
	private String state;
	
	@Column(name="country", nullable=false)
	private String country ;
	
	@OneToOne
	@JoinColumn(name="emp_id", referencedColumnName="id")
	private Employee employee;
	
	@SuppressWarnings("unused")
	private EmployeeAddress(){
		
	}
	
	public EmployeeAddress(String address, String city, String state, String country) {
		this.address = address;
		this.city=city;
		this.state = state;
		this.country = country;		
	}
	
	public String getAddress() {
		return address;
	}

//	public void setAddress(String address) {
//		this.address = address;
//	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
