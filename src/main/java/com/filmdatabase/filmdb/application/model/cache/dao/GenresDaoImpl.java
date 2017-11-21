package com.filmdatabase.filmdb.application.model.cache.dao;


import com.filmdatabase.filmdb.application.commons.QualifierConstants;
import com.filmdatabase.filmdb.application.model.cache.CacheConstants;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
@Component(QualifierConstants.GENRES_DIC_DAO)
public class GenresDaoImpl extends DictionaryAbstractClass<GenresDictionary> {

    public GenresDaoImpl() {
        super(GenresDictionary.class);
    }

    @Override
    @Cacheable(CacheConstants.CACHE_DICTIONARY)
    public List<GenresDictionary> getAll() {
        return super.getAll();
    }
}
