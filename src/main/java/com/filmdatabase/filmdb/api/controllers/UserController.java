package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.interfaces.UserService;
import com.filmdatabase.filmdb.application.DTO.UserDto;
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
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/sign-up")
    public UserDto register(@RequestBody UserDto user) {
        UserDto save = userService.save(user);
        return save;
    }

}


