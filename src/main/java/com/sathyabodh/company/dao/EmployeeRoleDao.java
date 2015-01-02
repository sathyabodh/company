package com.sathyabodh.company.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sathyabodh.company.domain.EmployeeRole;

@Repository
public class EmployeeRoleDao implements CrudRepository<EmployeeRole, Long> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EmployeeRole findById(Long id) {
		return em.find(EmployeeRole.class, id);
	}

	@Override
	public EmployeeRole create(EmployeeRole entity) {
		em.persist(entity);;
		return entity;
	}

	@Override
	public EmployeeRole update(EmployeeRole entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(EmployeeRole entity) {
		em.remove(entity);
	}

	public EmployeeRole findByName(String name){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EmployeeRole> query = cb.createQuery(EmployeeRole.class);
		Root<EmployeeRole> root = query.from(EmployeeRole.class);
		query.where(cb.equal(root.get("name"), name));
		List<EmployeeRole> roles = em.createQuery(query).getResultList();
		return roles.isEmpty() ? null : roles.get(0);
	}
	
	public List<EmployeeRole> findAll(){
		return em.createQuery("SELECT er from EmployeeRole er", EmployeeRole.class).getResultList();
	}
}
