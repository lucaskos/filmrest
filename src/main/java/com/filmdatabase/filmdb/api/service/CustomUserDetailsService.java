package com.filmdatabase.filmdb.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.filmdatabase.filmdb.application.model.user.dao.UserDao;
import com.filmdatabase.filmdb.application.model.user.role.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.filmdatabase.filmdb.application.model.user.dao.User activeUsr = userDao.getUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(activeUsr.getRole());

        return buildUserForAuthentication(activeUsr, authorities);
    }
    /**
     * Converts the user from com.luke.films.dao package
     * to org.springframework.security.core.userdetails.User
     */
    private User buildUserForAuthentication(com.filmdatabase.filmdb.application.model.user.dao.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role userRole) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}