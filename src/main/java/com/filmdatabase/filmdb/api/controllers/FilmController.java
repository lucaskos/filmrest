package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.commons.QualifierConstants;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.cache.dao.DictionaryDao;
import com.filmdatabase.filmdb.application.model.cache.dao.GenresDaoImpl;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.test.RatingTest;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import com.filmdatabase.filmdb.application.procedures.ProcedureDao;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    private ProcedureDao procedureDao;

    @Autowired
    @Qualifier(QualifierConstants.PERSON_DIC_DAO)
    private DictionaryDao genresDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    FilmDTO getFilm(@PathVariable @NotNull int id) {
        //todo exception handler if no entity found
        if(id == 0) {
            return null;
        }
        FilmDTO film = filmService.getFilmDetails(id);

        return film;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Film> getList() {
        List<PersonRole> all = genresDao.getAll();

        List<Film> list = (List<Film>) filmService.getAllFilms();
        return list;
    }

    @PostMapping
    public void create(@RequestBody Film film) {
        Preconditions.checkNotNull(film);
        //filmService.addFilm(film);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
