package com.filmdatabase.filmdb.api.service;


import com.filmdatabase.filmdb.configuration.common.MyUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        com.filmdatabase.filmdb.application.model.user.dao.User applicationUser = userDao.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(applicationUser.getRoles().getRole());
//        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        if (simpleGrantedAuthority != null){
//            grantedAuthorities.add(simpleGrantedAuthority);
//        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), null);
    }
}