package com.filmdatabase.filmdb.application.model.film;

import com.filmdatabase.filmdb.application.model.GenericDao;
import com.filmdatabase.filmdb.application.model.test.RatingTest;

import java.util.List;
import java.util.Set;

/**
 * Created by Luke on 02.11.2017.
 */
public interface FilmDao extends GenericDao<Film> {

    Film getFilmDetailsById(int id);

    List getByTitle(String title);

    List getByYear(int year);

    Set<RatingTest> getRatingTestList(Film f);
}
