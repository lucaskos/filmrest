package com.filmdatabase.filmdb.application.model.cache.dao;

import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;

import java.util.List;

public interface Cache {

        List<PersonRole> getRoles();

        List<GenresDictionary> getGenres();
}
