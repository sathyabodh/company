package com.sathyabodh.company.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sathyabodh.company.domain.Company;
import com.sathyabodh.company.domain.Company_;
import com.sathyabodh.company.domain.Organization;
import com.sathyabodh.company.domain.Organization_;
@Repository
public class OrganizationDao implements CrudRepository<Organization, Long> {

	@PersistenceContext
	EntityManager entityManger;
	
	@Override
	public Organization findById(Long id) {
		return entityManger.find(Organization.class, id);
	}

	@Override
	public Organization create(Organization entity) {
		entityManger.persist(entity);
		return entity;
	}

	@Override
	public Organization update(Organization entity) {
		entityManger.merge(entity);
		return entity;
	}

	@Override
	public void delete(Organization entity) {
		entityManger.remove(entity);
	}

	public Organization findByName(String name, String companyName) {
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Organization> query = cb.createQuery(Organization.class);
		Root<Organization> root = query.from(Organization.class);
		Predicate pred = cb.equal(root.get(Organization_.name), name);
		Join<Organization, Company> join = root.join(Organization_.company);
		Predicate companyPred = cb.equal(join.get(Company_.name), companyName);
		query.where(pred, companyPred);
		List<Organization> list = entityManger.createQuery(query).getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<Organization> search(String name, String companyName){
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Organization> query = cb.createQuery(Organization.class);
		Root<Organization> root = query.from(Organization.class);
		List<Predicate> predictes = new ArrayList<Predicate>();
		if(name != null && !name.isEmpty()){
			predictes.add(cb.like(root.get(Organization_.name), name+"%"));
		}
		if(companyName != null && !companyName.isEmpty()){
			Join<Organization, Company> join = root.join(Organization_.company);
			predictes.add(cb.like(join.get(Company_.name), companyName+"%"));
		}
		
		if(!predictes.isEmpty()){
//			cb.and(predictes.toArray(new Predicate[predictes.size()]));
			query.where(predictes.toArray(new Predicate[predictes.size()]));
		}
			

		return entityManger.createQuery(query).getResultList();
	}
}
