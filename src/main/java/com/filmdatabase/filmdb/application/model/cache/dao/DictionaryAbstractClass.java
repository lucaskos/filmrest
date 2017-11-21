package com.filmdatabase.filmdb.application.model.cache.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class DictionaryAbstractClass<T> implements DictionaryDao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    private Class<T> dictionaryClazz;

    public DictionaryAbstractClass(Class<T> clazz) {
        this.dictionaryClazz = clazz;
    }

    @Override
    public List<T> getAll() {

        entityManager.getCriteriaBuilder();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(dictionaryClazz);

        Root<T> c = query.from(dictionaryClazz);

        List resultList = entityManager.createQuery("from " + dictionaryClazz.getName()).getResultList();

        return resultList;
    }
}
