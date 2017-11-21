package com.filmdatabase.filmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private FilmDao filmDao;

    private static final String template = "Hello, %s";

    @RequestMapping("/allFilms")
    public Film allFils() {
        List<Film> list = filmDao.getFilms();
        return new Film("The matrix", 1999);
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public @ResponseBody Film sayHello() {
        List<Film> list = filmDao.getFilms();
        System.out.println(list);
        System.out.println(list.get(0));
        return list.get(0);
    }

}
