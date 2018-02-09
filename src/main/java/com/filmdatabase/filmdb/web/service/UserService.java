package com.filmdatabase.filmdb.web.service;

import com.filmdatabase.filmdb.application.model.user.dao.User;

import java.util.List;

public interface UserService {

    void save(User user);
    User getUserById(Long id);
    List<User> getAllUsers();

}
