package com.filmdatabase.filmdb.application.model.user.dao;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import com.filmdatabase.filmdb.application.model.user.role.Role;
import com.filmdatabase.filmdb.application.model.user.role.RoleDao;
import com.filmdatabase.filmdb.configuration.common.ConfigurationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends GenericDaoHibernateImpl<User> implements UserDao {

    @Autowired
    private RoleDao roleDao;

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User create(User user) {
        Role role = roleDao.getRole(ConfigurationConstants.ROLE_USER);
        user.setRoles(role);
        return super.create(user);
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("from User u WHERE u.username=:username");
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();

        return user;
    }
}
