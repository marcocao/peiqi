package com.cyy.peiqi.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected CriteriaQuery<T> createCriteriaQuery() {
		CriteriaQuery<T> criteriaQuery = getSession().getCriteriaBuilder().createQuery(persistentClass);
		Root<T> from = criteriaQuery.from(persistentClass);
		criteriaQuery.select(from);
		return criteriaQuery;
	}
}