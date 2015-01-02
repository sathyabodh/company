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
import com.sathyabodh.company.domain.Employee;
import com.sathyabodh.company.domain.Employee_;
import com.sathyabodh.company.domain.Organization;
import com.sathyabodh.company.domain.Organization_;

@Repository
public class EmployeeDao implements CrudRepository<Employee, Long> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Employee findById(Long id) {
		return em.find(Employee.class, id);
	}

	@Override
	public Employee create(Employee entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public Employee update(Employee entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(Employee entity) {
		em.remove(entity);
		em.flush();
	}

	public Employee findByName(String company, String org, String dept, String name){
		
//		String sql = "SELECT e FROM Employee e WHERE e.name=:name and e.department.organization.name=:org "
//				+ "and e.department.name=:dept and e.department.organization.company.name=:company";
//		
//		List<Employee> list = em.createQuery(sql, Employee.class).setParameter("name", name)
//		.setParameter("org", org)
//		.setParameter("company", company)
//		.setParameter("dept", dept).getResultList();
//		if(list == null || list.isEmpty())
//			return null;
//		
//		return list.get(0);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		
		Root<Employee> root = query.from(Employee.class);
		Join<Employee, Department> deptJoin = root.join(Employee_.department);
		Join<Department, Organization> orgJoin = deptJoin.join(Department_.organization);
		Join<Organization, Company> cmpJoin = orgJoin.join(Organization_.company);
		Predicate empPredicate = cb.equal(root.get(Employee_.name), name);
		Predicate deptPredicate = cb.equal(deptJoin.get(Department_.name), dept);
		Predicate orgPredicate = cb.equal(orgJoin.get(Organization_.name), org);
		Predicate cmpPredicate = cb.equal(cmpJoin.get(Company_.name), company);
		query.where(cb.and(empPredicate, deptPredicate, orgPredicate, cmpPredicate));
		
		List<Employee> list = em.createQuery(query).getResultList();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}
	
	public List<Employee> findAll(){
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
	}

}
