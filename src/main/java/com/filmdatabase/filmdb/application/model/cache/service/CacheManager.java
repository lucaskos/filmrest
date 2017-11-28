package com.filmdatabase.filmdb.application.model.cache.service;


import org.springframework.cache.Cache;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import java.util.Collection;


/**
 * Created by Luke on 30.10.2017.
 */

@Component
public class CacheManager {

    //@Resource(name = "cacheManager")
    private org.springframework.cache.CacheManager  cache;

    public Cache getCache(String key){
       return cache.getCache(key);
    }

    public Collection<String> getAllCacheNames() {
        return cache.getCacheNames();
    }

}
