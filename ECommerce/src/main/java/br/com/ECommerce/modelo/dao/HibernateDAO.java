package br.com.ECommerce.modelo.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

public class HibernateDAO<T> implements InterfaceDAO<T>, Serializable {

	private static final long serialVersionUID = 1L;

	public HibernateDAO(Class<T> classe, Session session) {
		super();
		this.classe = classe;
		this.session = session;
	}

	private Class<T> classe;
	private Session session;

	@Override
	public void save(T entity) {
		session.save(entity);
	}

	@Override
	public void update(T entity) {
		Transaction tc = session.beginTransaction();
		session.update(entity);
		tc.commit();
	}

	@Override
	public void remove(T entity) {
		Transaction tc = session.beginTransaction();
		session.delete(entity);
		tc.commit();
	}

	@Override
	public void merge(T entity) {
		Transaction tc = session.beginTransaction();
		session.merge(entity);
		tc.commit();
	}

	@Override
	public T getEntity(Serializable id) {
		T entity = (T) session.get(classe, id);
		return entity;
	}

	@Override
	public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
		T entity = (T) criteria.getExecutableCriteria(session).uniqueResult();
		return entity;
	}

	@Override
	public List<T> getListByDetachedCriteria(DetachedCriteria criteria) {
		return criteria.getExecutableCriteria(session).list();
	}

	@Override
	public List<T> getEntities() {
		List<T> enties = (List<T>) session.createCriteria(classe).list();
		return enties;
	}

}
