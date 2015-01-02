package com.sathyabodh.company.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sathyabodh.company.domain.Certificate;

@Repository
public class CertificateDao {
	@PersistenceContext
	private EntityManager em;
	
	public Certificate findByName(String name){
		return em.createQuery("select c from Certificate c where c.name=:name", Certificate.class).setParameter("name", name).getSingleResult();
	}
}
