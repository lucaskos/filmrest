package com.filmdatabase.filmdb.application.model;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Luke on 02.11.2017.
 */
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
    public T findOneWithRelations(Serializable id, Object... args) {
        String a = this.eClass.getSimpleName();
        StringBuilder builder = new StringBuilder();
        builder.append("from " + a +" p");
        if (args.length > 0) {
            for(int i = 0; i < args.length; i++) {
                String simpleName = ((Class) args[i]).getSimpleName();
                String s = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
                builder.append(" LEFT JOIN FETCH p." + s);
                simpleName = null;
                s = null;
            }
        }
        builder.append(" WHERE p.id=:id");
        Query query = entityManager.createQuery(builder.toString());
        query.setParameter("id", id);
        return (T) query.getSingleResult();
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
        T merged = entityManager.merge(t);
        return merged;
    }

    public void delete(T t) {
        entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }

}