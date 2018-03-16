package com.filmdatabase.filmdb.api.service;


import com.filmdatabase.filmdb.application.model.user.role.Role;
import com.filmdatabase.filmdb.configuration.common.ConfigurationConstants;
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

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.filmdatabase.filmdb.application.model.user.dao.User applicationUser = userDao.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        try {
            if (applicationUser != null && !CollectionUtils.isEmpty(applicationUser.getRoles())) {
                for (Role userRole : applicationUser.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
                }
            } else {
                LOGGER.error("Can't find roles for user");
                grantedAuthorities.add(new SimpleGrantedAuthority(ConfigurationConstants.ROLE_DUMMY));
            }
        } catch(Exception ex) {
            LOGGER.error("User " + username + " not found." + ex);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities);
    }
}
