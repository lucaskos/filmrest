package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    private final FilmDao filmDao;

    @Autowired
    public FilmController(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilm(@PathVariable @NotNull int id) {
        //todo exception handler if no entity found
        //generic for person, film and what not
        return filmDao.findOne(id);
    }

    @GetMapping
    public @ResponseBody List<Film> getList() {
        List<Film> list = filmDao.findAll();
        return list;
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping
    public void create(@RequestBody Film film) {
        Preconditions.checkNotNull(film);
        filmDao.create(film);
    }

    @PutMapping
    public void update(@PathVariable( "id" ) Long id, @RequestBody Film resource) {
        Preconditions.checkNotNull(resource);
        //RestPreconditions.checkNotNull(service.getById( resource.getId()));
        //http://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
        filmDao.update(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        //
    }
}
