package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.user.dao.User;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        LOGGER.info("\n\nUser has been created " + user.getEmail());
        userDao.create(user);
    }

    public User getUserById(Long id) {
        return userDao.findOne(id);
    }

}
