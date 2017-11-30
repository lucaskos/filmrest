package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.application.commons.QualifierConstants;
import com.filmdatabase.filmdb.application.model.cache.dao.DictionaryDao;
import com.filmdatabase.filmdb.application.model.cache.dao.GenresDaoImpl;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.procedures.ProcedureDao;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    private ProcedureDao procedureDao;

    @Autowired
    @Qualifier(QualifierConstants.PERSON_DIC_DAO)
    private DictionaryDao genresDao;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilm(@PathVariable @NotNull int id) {
        //todo exception handler if no entity found
        //generic for person, film and what not
        return filmService.getFilmById(id);
    }

    @GetMapping
    public @ResponseBody List<Film> getList() {
        List<PersonRole> all = genresDao.getAll();
        int i = procedureDao.insertRating(1, 1, null, 1);
        List<Film> list = (List<Film>) filmService.getAllFilms();
        return list;
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping
    public void create(@RequestBody Film film) {
        Preconditions.checkNotNull(film);
        filmService.addFilm(film);
    }

    @PutMapping
    public void update(@PathVariable( "id" ) Long id, @RequestBody Film resource) {
        Preconditions.checkNotNull(resource);
        //RestPreconditions.checkNotNull(service.getById( resource.getId()));
        //http://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
        filmService.updateFilm(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        filmService.deleteFilm(null);
        //
    }
}
