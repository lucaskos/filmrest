package com.filmdatabase.filmdb.api.service;


import com.filmdatabase.filmdb.application.model.cache.dao.PersonRoleDaoImpl;
import org.springframework.cache.Cache;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collection;


/**
 * Created by Luke on 30.10.2017.
 */

@Service("mycachemanager")
public class CacheManager {

    private PersonRoleDaoImpl personRoleDao;


}
