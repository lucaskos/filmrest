package com.filmdatabase.filmdb.application.model.user.dao;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends GenericDaoHibernateImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
