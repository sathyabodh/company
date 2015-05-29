package com.sathyabodh.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyabodh.company.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
}
