package com.filmdatabase.filmdb.web.controllers;

import com.filmdatabase.filmdb.web.service.UserService;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    //@PostMapping("/sign-up")
    public void register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //userService.save(user);
    }

}


