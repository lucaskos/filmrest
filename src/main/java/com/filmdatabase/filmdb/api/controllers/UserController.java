package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.UserService;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsService customUserDetailsService;

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public void register(@RequestBody User user) {
        LOGGER.info("Register user: " + user.getUsername());
//        todo delete this below
        LOGGER.debug(user.toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        customUserDetailsService.loadUserByUsername(user.getUsername());
        LOGGER.info(user.toString());
    }

}


