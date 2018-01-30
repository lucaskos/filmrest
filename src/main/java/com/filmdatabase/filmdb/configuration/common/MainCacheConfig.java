package com.filmdatabase.filmdb.configuration.common;

import com.filmdatabase.filmdb.application.commons.CacheConstants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.filmdatabase.filmdb.application.model.cache")
@EnableCaching
public class MainCacheConfig {

    @PostConstruct
    @Bean
    public CacheManager cacheManager() {
        return getAllCaches();
    }

    private SimpleCacheManager getAllCaches() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> cacheList = new ArrayList<>();
        cacheList.add(new ConcurrentMapCache(CacheConstants.ROLES));
        cacheList.add(new ConcurrentMapCache(CacheConstants.GENRES));
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }

}
