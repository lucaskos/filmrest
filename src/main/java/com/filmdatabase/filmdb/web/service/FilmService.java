package com.filmdatabase.filmdb.web.service;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.film.Film;

import java.util.Collection;

public interface FilmService {

    Collection<Film> getAllFilms();

    Film getFilmById(int id);

    void addFilm(Film film);

    void deleteFilm(Film film);

    void updateFilm(Film film);

    FilmDTO getFilmDetails(int id);

}
