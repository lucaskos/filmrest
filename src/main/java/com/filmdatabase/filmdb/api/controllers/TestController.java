package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.application.model.film.Film;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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
