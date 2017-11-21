package com.filmdatabase.filmdb.application.model.genre;

import java.util.List;

/**
 * Created by Luke on 21.10.2017.
 */
public interface GenreDao {

    Genre getGenreById(int id);

    Genre getGenreByName(String name);

    List<Genre> getAllGenres();
}
