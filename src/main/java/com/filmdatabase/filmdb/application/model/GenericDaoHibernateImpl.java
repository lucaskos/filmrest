package com.filmdatabase.filmdb.application.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Luke on 02.11.2017.
 */
public abstract class GenericDaoHibernateImpl <T>
        implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private EntityManager entityManager;

    private Class<T> eClass;

    public Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    public GenericDaoHibernateImpl(Class<T> typeParameterClass) {
        this.eClass = typeParameterClass;
    }

    @Override
    public List<T> findAll() {

        //CriteriaBuilder cb = getSession().getCriteriaBuilder();

//        CriteriaQuery<T> q = cb.createQuery(eClass);
//        Root<T> c = q.from(eClass);
//        q.select(c);
//
//        TypedQuery<T> query = getSession().createQuery(q);
//        List<T> results = query.getResultList();

        List<T> resultList = entityManager.createQuery("from " + eClass.getName()).getResultList();

        //List resultList = getSession().createQuery("from " + eClass.getName()).getResultList();
        return resultList;
    }

    @Override
    public T findOne(final Serializable id) {
        T t = getSession().get(eClass, id);
        return t;
    }

    @Override
    public T create(T t) {
        this.getSession().persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        getSession().merge(t);
        return t;
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

}