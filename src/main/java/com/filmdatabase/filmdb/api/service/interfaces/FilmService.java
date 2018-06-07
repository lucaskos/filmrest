package com.filmdatabase.filmdb.api.service.interfaces;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.film.Film;

import java.util.Collection;

public interface FilmService {

    Collection<Film> getAllFilms();

    FilmDTO getFilmById(int id);

    FilmDTO addFilm(FilmDTO film);

    void deleteFilm(Film film);

    FilmDTO updateFilm(Film film);

    FilmDTO getFilmDetails(int id);

}
