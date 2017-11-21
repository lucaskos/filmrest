package com.filmdatabase.filmdb.application.model.cache;


import org.springframework.cache.Cache;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import java.util.Collection;


/**
 * Created by Luke on 30.10.2017.
 */

@Controller("test")
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
