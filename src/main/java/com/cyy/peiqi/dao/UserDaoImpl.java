package com.cyy.peiqi.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cyy.peiqi.domain.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
/*
	@Autowired
	private SessionFactory sessionFactory;

	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from User").getResultList();
	}
	
	public User getUser(String id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(User.class, id);
	}

	public void deleteUserById(String id)
	{
		Session session = sessionFactory.getCurrentSession();
		session.delete(id);
	}
	*/
	public User findUserById(long id) {
		return getByKey(id);
	}

	public void deleteUserById(long id) {
		Query query = getSession().createQuery("delete from User where id=:id"); // delete 1 by hibernate api
		query.setParameter("id", id);
		query.executeUpdate();
		
		//hibernateTemplate.delete(getByKey(id));//delete 2 by hibernate template
		
		//hibernateTemplate.bulkUpdate("delete from User where id=?", id); //delete 3
		
		//User user = new User(); // delete 4
		//user.setId(id);
		//hibernateTemplate.delete(user);
	}

	public List<User> findAllUsers() {
		CriteriaQuery<User> query = createCriteriaQuery();

		return getSession().createQuery(query).getResultList();
	}

	public User findUserByEmail(String email) {
		CriteriaBuilder criteriaBuilder=getSession().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> from = criteriaQuery.from(User.class);
		criteriaQuery.select(from);
		criteriaQuery.where(criteriaBuilder.equal(from.get("emailId"),email));
		return DataAccessUtils.singleResult(getSession().createQuery(criteriaQuery).getResultList());
	}

	public void saveUser(User user) {
		persist(user);
	}
	
}