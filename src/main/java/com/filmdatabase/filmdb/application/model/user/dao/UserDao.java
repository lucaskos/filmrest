package com.filmdatabase.filmdb.application.model.user.dao;

import com.filmdatabase.filmdb.application.model.GenericDao;

public interface UserDao extends GenericDao<User> {

    User getUserByUsername(String username);

}
