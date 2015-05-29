package com.sathyabodh.company.repository;

import com.sathyabodh.company.domain.Employee;

public interface EmployeeRepositoryCustom {
	Employee findByName(String companName, String orgName, String deptName, String name);
}
