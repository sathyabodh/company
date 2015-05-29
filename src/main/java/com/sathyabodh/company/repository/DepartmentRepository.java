package com.sathyabodh.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyabodh.company.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByNameAndOrganization_NameAndOrganization_Company_Name(String name, String orgName, String cmpName);
}
