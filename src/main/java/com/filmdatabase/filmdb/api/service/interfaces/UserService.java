package com.filmdatabase.filmdb.api.service.interfaces;

import com.filmdatabase.filmdb.application.DTO.UserDto;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDetails loadUserByUsername(String username);

}
