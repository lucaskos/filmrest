package com.filmdatabase.filmdb.application.model.film;

import com.filmdatabase.filmdb.application.model.GenericDao;

import java.util.List;

/**
 * Created by Luke on 02.11.2017.
 */
public interface FilmDao extends GenericDao<Film> {

    List getByTitle(String title);

    List getByYear(int year);
}
