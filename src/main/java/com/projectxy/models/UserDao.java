
/**
 * tkasozi <talik.aziizi@gmail.com>
 */

package com.projectxy.models;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao<T> {
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public UserDao(SessionFactory sessionFactory, Class<T> clazz) {
		this.sessionFactory = sessionFactory;
		this.clazz = clazz;
	}

	@Transactional
	public List<T> list() {
		@SuppressWarnings("unchecked")
		List<T> listSentence = (List<T>) sessionFactory.getCurrentSession().createCriteria(clazz)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listSentence;
	}
 
	@Transactional
	public T get(Serializable id) {
		String hql = "from " + clazz.getSimpleName() + " where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<T> listSentence = (List<T>) query.list();

		if (listSentence != null && !listSentence.isEmpty()) {
			return listSentence.get(0);
		}
		return null;
	}

	@Transactional
	public void saveOrUpdate(T entityName) {
		sessionFactory.getCurrentSession().saveOrUpdate(entityName);
	}

	@Transactional
	public void delete(Serializable id) {
		T entity = get(id);
		if (entity != null) {
			sessionFactory.getCurrentSession().delete(entity);
		}
	}

	@Transactional
	public T findByProperty(String propertyName, Object propertyValue) {
		List<T> list = getSessionFactory().getCurrentSession().createCriteria(clazz)
				.add(Restrictions.eq(propertyName, propertyValue)).list();
		return list.isEmpty() ? null : list.get(0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
