package com.sathyabodh.company.dao;

import org.springframework.stereotype.Repository;

@Repository
interface CrudRepository<T, ID> {

	public T findById(ID id);
	public T create(T entity);
	public T update(T entity);
	public void delete(T entity);
}