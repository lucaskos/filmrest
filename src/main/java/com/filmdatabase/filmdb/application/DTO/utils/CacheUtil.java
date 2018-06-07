package com.filmdatabase.filmdb.application.DTO.utils;

import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.Cache;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.CacheAbstract;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.user.role.Role;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheUtil {

    private static CacheService cacheService;

    @Autowired
    public CacheUtil(CacheService cacheService){
        this.cacheService = cacheService;
    }

    public static Map<String, CacheAbstract> createCacheMap(Collection<CacheAbstract> list) {
        Map<String, CacheAbstract> cache = new HashMap<>();
        if(!CollectionUtils.isEmpty(list)) {
            for(CacheAbstract o : list) {
            }
            return cache;
        }
        return null;
    }

    public static Cache findCacheByKey(String key, List<Cache> list) {
        if(!CollectionUtils.isEmpty(list)) {
            return list.stream().filter(cacheAbstract -> cacheAbstract.getKey().equals(key)).findFirst().orElse(null);
        }
        return null;
    }

     public static Role getDefaultRole() {
        List roles = cacheService.getRoles();
        Cache cacheByKey = findCacheByKey(CacheConstants.DEFAULT_USER_ROLE, roles);
        return (Role) cacheByKey;
    }

}
