package com.filmdatabase.filmdb.application.model.user.dao;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends GenericDaoHibernateImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public User getUserByUsername(String username) {
                CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> c = q.from(User.class);

        CriteriaQuery<User> user = q.where(cb.equal(c.get("username"), username));

        return null;
    }
}
