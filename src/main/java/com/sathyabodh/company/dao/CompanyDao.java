package com.sathyabodh.company.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sathyabodh.company.domain.Company;
import com.sathyabodh.company.domain.Company_;
import com.sathyabodh.company.exception.ObjectNotFoundException;

@Repository
public class CompanyDao implements CrudRepository<Company, Long>{

	@PersistenceContext
	private EntityManager entityManager ;

	@Override
	public Company findById(Long id) {
		return entityManager.find(Company.class, id);
	}

	@Override
	public Company create(Company entity) {
		 entityManager.persist(entity);
		 return entity;
	}

	@Override
	public Company update(Company entity) {
		return entityManager.merge(entity);
	}

	@Override
	public void delete(Company entity) {
		entityManager.remove(entity);
	}

	public Company findByName(String name) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> query = builder.createQuery(Company.class);
		Root<Company> root = query.from(Company.class);
		query.where(builder.equal(root.get("name"), name));

		List<Company> companies = entityManager.createQuery(query).getResultList();
		if(companies.isEmpty())
			return null;
	
		return companies.get(0);
	}
	
	public List<Company> search(String name){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> query = builder.createQuery(Company.class);
		Root<Company> root = query.from(Company.class);
		if(name != null && !name.isEmpty()){
			Predicate nameLike = builder.like(root.get(Company_.name), name + "%");
			query.where(nameLike);
		}
		return entityManager.createQuery(query).getResultList();
	}
	
}
