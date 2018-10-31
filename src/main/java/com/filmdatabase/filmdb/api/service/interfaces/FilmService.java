package com.filmdatabase.filmdb.api.service.interfaces;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.film.Film;

import java.util.Collection;
import java.util.List;

public interface FilmService {



    List<Film> getAllFilms();

    FilmDTO getFilmById(int id);

    Film getFilmEntity(int id);

    FilmDTO addFilm(FilmDTO film);

    void deleteFilm(Film film);

    FilmDTO updateFilm(Film film);

    FilmDTO getFilmDetails(int id);

}
