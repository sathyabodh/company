package com.sathyabodh.company.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "join_date")
	private DateTime joinDateTime;

	@Column(name = "end_date_time")
	private DateTime endDateTime;

	@Column(name = "ext_no")
	private String extentionNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "salary")
	private Double salary;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dept_id", referencedColumnName = "id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@JsonIgnore
	private EmployeeRole empRole;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "employee_certificate", joinColumns = { @JoinColumn(name = "emp_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "cert_id", referencedColumnName = "id") })
	private List<Certificate> certificatesObj;
	
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	private EmployeeAddress address;
	
	@Transient
	private String role;

	@Transient
	@JsonProperty("certificates")
	private List<String> certificateNames;
	
	@JsonIgnore
	@Version
	private  long version  = 1;
	
	@PostLoad
	private void init() {
		role = empRole.getName();
		if (certificatesObj != null) {
			certificateNames = new ArrayList<String>();
			for (Certificate cert : certificatesObj) {
				certificateNames.add(cert.getName());
			}
		}
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getDepartmentName() {
		return department.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtentionNumber() {
		return extentionNumber;
	}

	public void setExtentionNumber(String extentionNumber) {
		this.extentionNumber = extentionNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

	public EmployeeRole getEmpRole() {
		return empRole;
	}

	public void setEmpRole(EmployeeRole empRole) {
		this.empRole = empRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Certificate> getCertificatesObj() {
		return certificatesObj;
	}

	public void setCertificatesObj(List<Certificate> certificates) {
		this.certificatesObj = certificates;
	}

	public List<String> getCertificateNames() {
		return certificateNames;
	}

	public void setCertificateNames(List<String> certificateNames) {
		this.certificateNames = certificateNames;
	}
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}
	
	public EmployeeAddress getAddress() {
		return address;
	}
	
}
