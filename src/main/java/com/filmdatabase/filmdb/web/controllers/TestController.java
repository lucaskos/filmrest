package com.filmdatabase.filmdb.web.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping()
    public @ResponseBody
    String getFilm() {
        String hello = "hello";
        return hello;
    }
}
