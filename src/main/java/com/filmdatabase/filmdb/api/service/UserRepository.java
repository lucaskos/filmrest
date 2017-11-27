package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.user.dao.User;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final UserDao userDao;

    @Autowired
    public UserRepository(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(Long id){
        return userDao.findOne(id);
    }

}
