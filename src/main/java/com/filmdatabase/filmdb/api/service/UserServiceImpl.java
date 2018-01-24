package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.user.dao.User;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        //todo decode password
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void save(User user) {
        userDao.create(user);
    }

    public User getUserById(Long id){
        return userDao.findOne(id);
    }

}
