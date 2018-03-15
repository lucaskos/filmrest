package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public interface UserService {

    void save(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User findByUsername(String username);

}
