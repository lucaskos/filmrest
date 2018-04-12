package com.filmdatabase.filmdb.application.model.cache.service;

import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.application.model.cache.dao.Cache;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

public class CacheServiceImpl implements Cache{

    private final Cache cache;
    private final CacheManager cacheManager;

    @Autowired
    public CacheServiceImpl(Cache cache, CacheManager cacheManager) {
        this.cache = cache;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void init() {
        cache.getGenres();
        cache.getRoles();
    }

    @Override
    public List getRoles() {
        List<PersonRole> personRoles = (List<PersonRole>) cacheManager.getCache(CacheConstants.ROLES);
        if (CollectionUtils.isEmpty(personRoles)) {
            return personRoles;
        }
        return this.cache.getRoles();
    }

    @Override
    public List getGenres() {
        List<GenresDictionary> genres = (List<GenresDictionary>) cacheManager.getCache(CacheConstants.ROLES);
        if (CollectionUtils.isEmpty(genres)) {
            return genres;
        }
        return cache.getGenres();
    }

}
