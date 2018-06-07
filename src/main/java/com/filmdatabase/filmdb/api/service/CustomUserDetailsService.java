package com.filmdatabase.filmdb.api.service;


import com.filmdatabase.filmdb.api.service.interfaces.UserService;
import com.filmdatabase.filmdb.application.DTO.UserDto;
import com.filmdatabase.filmdb.application.DTO.utils.CacheUtil;
import com.filmdatabase.filmdb.application.model.user.role.Role;
import com.filmdatabase.filmdb.configuration.common.ConfigurationConstants;
import org.modelmapper.ModelMapper;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("customUserService")
public class CustomUserDetailsService implements UserDetailsService, UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    private List<Role> defaultRole;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDto save(UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        LOGGER.info("User has been created " + user.getUsername());
        if (CollectionUtils.isEmpty(user.getRoles())) {
            user.setRoles(getDefaultRole());
        }
        com.filmdatabase.filmdb.application.model.user.dao.User map = modelMapper.map(user, com.filmdatabase.filmdb.application.model.user.dao.User.class);
        com.filmdatabase.filmdb.application.model.user.dao.User insertedUser = userDao.create(map);
        if(insertedUser != null) {
            return modelMapper.map(insertedUser, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getUserById(Long id) {
        return modelMapper.map(userDao.findOne(id), UserDto.class);    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userList = new ArrayList<>();
        userDao.findAll().stream().forEach(user -> {userList.add(modelMapper.map(user, UserDto.class));});
        return userList;
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

    private List<Role> getDefaultRole() {
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(CacheUtil.getDefaultRole());
        return defaultRole;
    }
}
