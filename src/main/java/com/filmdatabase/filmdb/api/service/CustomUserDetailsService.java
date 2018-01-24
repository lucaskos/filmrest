package com.filmdatabase.filmdb.api.service;


import com.filmdatabase.filmdb.configuration.MyUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("customUserService")
public class CustomUserDetailsService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    public CustomUserDetailsService(){
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.filmdatabase.filmdb.application.model.user.dao.User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        MyUserPrincipal myUserPrincipal = new MyUserPrincipal(user);
        LOGGER.info(myUserPrincipal.toString());
        return myUserPrincipal;
    }
    /**
     * Converts the user from com.luke.films.dao package
     * to org.springframework.security.core.userdetails.User
     */
    private User buildUserForAuthentication(com.filmdatabase.filmdb.application.model.user.dao.User user,
                                            List<GrantedAuthority> authorities) {
        LOGGER.info("buildUser : " + user + " : authorities size : " + authorities.size());
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }
}