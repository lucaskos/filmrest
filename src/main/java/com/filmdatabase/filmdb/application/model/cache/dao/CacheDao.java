package com.filmdatabase.filmdb.application.model.cache.dao;

import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
public interface CacheDao<T> {

    List<T> getAll();
}
