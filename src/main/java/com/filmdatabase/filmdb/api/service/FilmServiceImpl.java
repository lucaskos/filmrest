package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    @Autowired
    public FilmServiceImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
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
    public void addFilm(Film film) {
        filmDao.create(film);
    }

    @Override
    public void deleteFilm(Film film) {
        filmDao.delete(film);
    }

    @Override
    public void updateFilm(Film film) {
        filmDao.update(film);
    }
}
