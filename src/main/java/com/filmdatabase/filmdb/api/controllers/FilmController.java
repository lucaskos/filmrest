package com.filmdatabase.filmdb.api.controllers;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.cache.dao.Cache;
import com.filmdatabase.filmdb.application.model.cache.dao.CacheDao;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.film.Film;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;
    private final Cache cacheManager;

    @Autowired
    public FilmController(FilmService filmService, Cache cacheManager) {
        this.filmService = filmService;
        this.cacheManager = cacheManager;
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

    @GetMapping("/")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Film> getList() {
        List<PersonRole> roles = cacheManager.getRoles();
        List<GenresDictionary> genres = cacheManager.getGenres();

        List<Film> list = null;
        try {
            list = (List<Film>) filmService.getAllFilms();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        Preconditions.checkNotNull(film);
        return filmService.addFilm(film);
    }

    @PutMapping
    public void update(@PathVariable( "id" ) Long id, @RequestBody Film resource) {
        Preconditions.checkNotNull(resource);
        //RestPreconditions.checkNotNull(service.getById( resource.getId()));
        //http://www.baeldung.com/building-a-restful-web-service-with-spring-and-java-based-configuration
        filmService.updateFilm(resource);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        FilmDTO filmDetails = filmService.getFilmDetails(id);
//todo
        filmService.deleteFilm(null);
    }
}
