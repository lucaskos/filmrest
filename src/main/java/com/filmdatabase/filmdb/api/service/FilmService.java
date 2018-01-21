package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.test.RatingTest;

import java.util.Collection;
import java.util.Set;

public interface FilmService {

    Collection<Film> getAllFilms();

    Film getFilmById(int id);

    void addFilm(Film film);

    void deleteFilm(Film film);

    void updateFilm(Film film);

    FilmDTO getFilmDetails(int id);

}
