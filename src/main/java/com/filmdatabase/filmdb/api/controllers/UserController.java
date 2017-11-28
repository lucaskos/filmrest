package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.UserRepository;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @GetMapping("{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Long id) {
        return userRepository.getUserById(id);
    }

}


