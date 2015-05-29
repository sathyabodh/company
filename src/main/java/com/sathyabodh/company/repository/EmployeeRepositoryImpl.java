package com.sathyabodh.company.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sathyabodh.company.domain.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
	@PersistenceContext
	private EntityManager em ;
	
	@Override
	public Employee findByName(String companName, String orgName,
			String deptName, String name) {
		return null;
	}

}
