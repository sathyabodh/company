package com.sathyabodh.company.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sathyabodh.company.dao.CertificateDao;
import com.sathyabodh.company.dao.CompanyDao;
import com.sathyabodh.company.dao.DepartmentDao;
import com.sathyabodh.company.dao.EmployeeDao;
import com.sathyabodh.company.dao.EmployeeRoleDao;
import com.sathyabodh.company.dao.OrganizationDao;
import com.sathyabodh.company.domain.Certificate;
import com.sathyabodh.company.domain.Company;
import com.sathyabodh.company.domain.Department;
import com.sathyabodh.company.domain.Employee;
import com.sathyabodh.company.domain.EmployeeRole;
import com.sathyabodh.company.domain.Organization;
import com.sathyabodh.company.exception.ObjectAlreadyExists;
import com.sathyabodh.company.exception.ObjectNotFoundException;

@Service
public class PersistanceService {

	@Inject
	private CompanyDao companyDao;
	
	@Inject
	private OrganizationDao organizationDao;
	
	@Inject
	private DepartmentDao deptDao ;
	
	@Inject
	private EmployeeDao empDao ;
	
	@Inject
	private EmployeeRoleDao roleDao;
	
	@Inject
	private CertificateDao certDao ;
	
	@Transactional
	public void createCompany(Company company){
		if(companyDao.findByName(company.getName()) != null)
			throw new ObjectAlreadyExists();
		companyDao.create(company);
	}
	
	@Transactional
	public void updateCompany(Company company){
		if(companyDao.findByName(company.getName()) == null){
			throw new ObjectNotFoundException();
		}
		
		companyDao.update(company);
	}
	
	public Company getCompany(String name){
		Company company = companyDao.findByName(name);
		if(company == null)
			throw new ObjectNotFoundException();
		return company;
	}
	
	public List<Company> searchCompany(String name){
		return companyDao.search(name);
	}
	
	
	@Transactional
	public void createDepartment(String company, String orgName, Department dept){
		if(deptDao.findByName(company, orgName,dept.getName()) != null)
			throw new ObjectAlreadyExists();
		Organization org = organizationDao.findByName(orgName, company);
		if(org == null)
			throw new ObjectNotFoundException(String.format("Org:%s doest not exist", orgName));
		
		dept.setOrganization(org);;
		deptDao.create(dept);
	}
	
	@Transactional
	public void updateDepartment(String company, String orgName,Department dept){
		if(deptDao.findByName(company, orgName,dept.getName()) != null)
			throw new ObjectAlreadyExists();
		Organization org = organizationDao.findByName(orgName, company);
		if(org == null)
			throw new ObjectNotFoundException(String.format("Org:%s doest not exist", orgName));
		
		dept.setOrganization(org);;
		deptDao.update(dept);
	}
	
	public Department getDepartment(String company, String orgName,String name){
		Department dept = deptDao.findByName(company, orgName, name);
		if(dept == null)
			throw new ObjectNotFoundException();
		return dept;
	}
	
	
	@Transactional
	public void createOrganization(Organization org){
		if(organizationDao.findByName(org.getName(), org.getCompanyName()) != null)
			throw new ObjectAlreadyExists();
		Company company = companyDao.findByName(org.getCompanyName());
		if(company == null)
			throw new ObjectNotFoundException(String.format("Company:%s doest not exist", org.getCompanyName()));
		
		org.setCompany(company);
		organizationDao.create(org);
	}
	
	@Transactional
	public void updateOrganization(Organization org){
		if(organizationDao.findByName(org.getName(),org.getCompanyName()) == null){
			throw new ObjectNotFoundException();
		}

		Company company = companyDao.findByName(org.getCompanyName());
		if(company == null)
			throw new ObjectNotFoundException(String.format("Company:%s doest not exist", org.getCompanyName()));
		org.setCompany(company);
		organizationDao.update(org);
	}
	
	public Organization getOrganization(String name, String companyName){
		Organization org = organizationDao.findByName(name, companyName);
		if(org == null)
			throw new ObjectNotFoundException();
		return org;
	}
	
	public List<Organization> searchOrganization(String name, String companyName){
		return organizationDao.search(name,companyName);
	}

	@Transactional
	public void createEmployee(String company, String orgName, String dept, Employee emp){
		if(empDao.findByName(company, orgName,dept, emp.getName()) != null)
			throw new ObjectAlreadyExists();
		Department dept1 = deptDao.findByName(company, orgName, dept);
		if(dept1 == null)
			throw new ObjectNotFoundException(String.format("Org:%s doest not exist", orgName));
		
		EmployeeRole role = roleDao.findByName(emp.getRole());

		if(role == null)
			throw new ObjectNotFoundException(String.format("Role:%s doest not exist", emp.getRole()));
		
		
		if(emp.getCertificateNames() != null)
		{
			List<Certificate> certificates = new ArrayList<Certificate>();
			for(String certName : emp.getCertificateNames()){
				Certificate cert = certDao.findByName(certName);
				certificates.add(cert);
			}
			emp.setCertificatesObj(certificates);
		}
		
		emp.setDepartment(dept1);
		emp.setEmpRole(role);
		emp.getAddress().setEmployee(emp);
		empDao.create(emp);
	}
	
	@Transactional
	public void updateEmployee(String company, String orgName,String dept,Employee emp){
		Employee empDb = empDao.findByName(company, orgName,dept, emp.getName());
		if(empDb == null)
			throw new ObjectNotFoundException();
		Department dept1 = deptDao.findByName(company, orgName, dept);
		if(dept1 == null)
			throw new ObjectNotFoundException(String.format("Org:%s doest not exist", dept));
		EmployeeRole role = roleDao.findByName(emp.getRole());

		if(role == null)
			throw new ObjectNotFoundException(String.format("Role:%s doest not exist", emp.getRole()));
		
		if(emp.getCertificateNames() != null)
		{
			List<Certificate> certificates = new ArrayList<Certificate>();
			for(String certName : emp.getCertificateNames()){
				Certificate cert = certDao.findByName(certName);
				certificates.add(cert);
			}
			emp.setCertificatesObj(certificates);
		}
		emp.setId(empDb.getId());
		emp.setVersion(empDb.getVersion());
		emp.getAddress().setId(empDb.getAddress().getId());
		emp.getAddress().setEmployee(emp);
		emp.setDepartment(dept1);
		emp.setEmpRole(role);
		empDao.update(emp);
	}
	
	public Employee getEmployee(String company, String orgName,String dept,String name){
		Employee emp = empDao.findByName(company, orgName,dept, name);
		if(emp == null)
			throw new ObjectNotFoundException();
		return emp;
	}
	
	public List<Employee> findAllEmployees(){
		return empDao.findAll();
	}
	
	@Transactional
	public void deleteEmployee(String company, String orgName,String dept,String name){
		Employee emp = empDao.findByName(company, orgName,dept, name);
		if(emp == null)
			throw new ObjectNotFoundException();
		 empDao.delete(emp);
	}

	
	
//
	
	@Transactional
	public void createEmployeeRole(EmployeeRole emp){
		if(roleDao.findByName(emp.getName()) != null)
			throw new ObjectAlreadyExists();
		roleDao.create(emp);
	}
	
	@Transactional
	public void updateEmployeeRole(EmployeeRole emp){
		if(roleDao.findByName(emp.getName()) == null)
			throw new ObjectNotFoundException();
		roleDao.update(emp);
	}
	
	public EmployeeRole getEmployeeRole(String name){
		EmployeeRole emp = roleDao.findByName(name);
		if(emp == null)
			throw new ObjectNotFoundException();
		return emp;
	}
	
	public List<EmployeeRole> findAllEmployeeRoles(){
		return roleDao.findAll();
	}

}
