package com.sathyabodh.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathyabodh.company.domain.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	Certificate findByName(String name);
}
