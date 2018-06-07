package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.interfaces.UserService;
import com.filmdatabase.filmdb.application.DTO.UserDto;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.Assert.*;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
    }

    @Test
    public void getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        assertNotNull(allUsers);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getSingleUser(){
        String username = userService.getAllUsers().get(0).getUsername();
        assertNotNull(username);
        UserDetails byUsername = userService.loadUserByUsername(username);
        assertNotNull(byUsername.getPassword());
        assertNotNull(byUsername.getAuthorities());
        assertTrue(byUsername.isEnabled());
    }

    @Test
    public void invalidUserTest() {
        UserDto userToInsert = getInvalidUser();
        assertNotNull(userToInsert.getEmail());
        assertNotNull(userToInsert.getPassword());
        assertNotNull(userToInsert.getUsername());
        expectedException.expect(TransactionSystemException.class);
        userService.save(userToInsert);
    }

    @Test
    public void saveValidUser() {
        UserDto userToInsert = getValidUser();
        UserDto save = userService.save(userToInsert);
        assertNotNull(save);
    }

    private UserDto getInvalidUser() {
        UserDto u = new UserDto();
        u.setEmail("test");
        u.setEnabled(true);
        u.setUsername("test");
        u.setPassword("a");
        return u;
    }

    private UserDto getValidUser() {
        UserDto u = new UserDto();
        u.setEnabled(true);
        u.setPassword("password");
        u.setUsername("longtest");
        u.setEmail("email@email.com");
        return u;
    }

}
