package com.sathyabodh.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyabodh.company.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

}
