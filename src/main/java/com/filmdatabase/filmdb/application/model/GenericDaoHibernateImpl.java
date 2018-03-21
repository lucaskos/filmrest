package com.filmdatabase.filmdb.application.model;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Luke on 02.11.2017.
 */
@Transactional
@Repository
public abstract class GenericDaoHibernateImpl<T>
        implements GenericDao<T> {

    @Autowired
    @PersistenceContext
    public EntityManager entityManager;

    private Class<T> eClass;

    public GenericDaoHibernateImpl(Class<T> typeParameterClass) {
        this.eClass = typeParameterClass;
    }

    @Override
    public List<T> findAll() {
        List<T> resultList = entityManager.createQuery("from " + eClass.getName()).getResultList();
        if(!resultList.isEmpty())
            for(T t : resultList) {
                entityManager.detach(t);
            }
        return resultList;
    }

    @Override
    public T findOne(final Serializable id) {
        T t = entityManager.find(eClass, id);
        return t;
    }

    @Override
    public T create(T t) {
        entityManager.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        entityManager.merge(t);
        return t;
    }

    public void delete(T t) {
        entityManager.remove(t);
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }

}