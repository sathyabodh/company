package com.sathyabodh.company.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sathyabodh.company.domain.Company;
import com.sathyabodh.company.domain.Company_;
import com.sathyabodh.company.domain.Department;
import com.sathyabodh.company.domain.Department_;
import com.sathyabodh.company.domain.Organization;
import com.sathyabodh.company.domain.Organization_;

@Repository
public class DepartmentDao implements CrudRepository<Department	, Long> {
	@PersistenceContext
	private EntityManager em ;
	
	@Override
	public Department findById(Long id) {
		return em.find(Department.class, id);
	}

	@Override
	public Department create(Department entity) {
		 em.persist(entity);
		 return entity ;
	}

	@Override
	public Department update(Department entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Department entity) {
		em.detach(entity);
	}

	public Department findByName(String company, String orgName, String dept){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery< Department> query = cb.createQuery(Department.class);
		Root<Department> root = query.from(Department.class);
		Join<Department, Organization> orgJoin = root.join(Department_.organization);
		Join<Organization, Company> cmpJoin = orgJoin.join(Organization_.company);
		Predicate deptPred = cb.equal(root.get(Department_.name), dept);
		Predicate orgPredicate = cb.equal(orgJoin.get(Organization_.name), orgName);
		Predicate cmpPredicte = cb.equal(cmpJoin.get(Company_.name), company);
		
		query.where(cb.and(deptPred, orgPredicate, cmpPredicte));
		List<Department> deptList = em.createQuery(query).getResultList();
		if(!deptList.isEmpty())
			return deptList.get(0);
		return null;
	}
}
