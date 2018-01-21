package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.FilmWrapper;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import com.filmdatabase.filmdb.application.model.test.RatingTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    private final FilmWrapper filmWrapper;

    @Autowired
    public FilmServiceImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
        filmWrapper = new FilmWrapper();
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

    @Override
    public FilmDTO getFilmDetails(int id) {
        return filmWrapper.populateDetails(filmDao.getFilmDetails(id));
    }

}
