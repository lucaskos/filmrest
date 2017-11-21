package com.filmdatabase.filmdb;

import com.filmdatabase.filmdb.application.model.actor.Person;
import com.filmdatabase.filmdb.application.model.actor.PersonDao;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
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

    @Autowired
    private PersonDao personDao;

    private static final String template = "Hello, %s";

    @RequestMapping("/allFilms")
    public Film allFils() {
        List<Film> all = filmDao.findAll();
        Film oneFilm = null;
        if (!all.isEmpty()) {
            oneFilm = all.get(0);
            System.out.println(oneFilm);
        }
        return oneFilm;
    }

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public @ResponseBody List<Film> sayHello() {
        List<Film> list = filmDao.findAll();
        System.out.println(list);
        System.out.println(list.get(0));
        return list;
    }

    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public @ResponseBody List<Person> getActors() {
        return personDao.findAll();
    }

}
