package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.WrapperUtils;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    private final WrapperUtils filmWrapper;

    @Autowired
    public FilmServiceImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
        filmWrapper = new WrapperUtils();
    }

    @Override
    public Collection<Film> getAllFilms() {
        return filmDao.findAll();
    }

    @Override
    public Film getFilmById(int id) {
        return filmDao.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Film addFilm(Film film) {
        return filmDao.create(film);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFilm(Film film) {
        filmDao.delete(film);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateFilm(Film film) {
        filmDao.update(film);
    }

    @Transactional
    @Override
    public FilmDTO getFilmDetails(int id) {
        Film one = filmDao.findOne(id);
        List<FilmRelation> filmRelations = one.getFilmRelations();
        return filmWrapper.getFullDetailsFilmObject(filmDao.getFilmDetails(id));
    }

}
