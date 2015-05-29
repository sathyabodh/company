package com.sathyabodh.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyabodh.company.domain.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	Organization findByNameAndCompany_Name(String name, String companyName);
}
