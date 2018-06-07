package com.filmdatabase.filmdb.controller;

import com.filmdatabase.filmdb.FilmdbApplication;
import com.filmdatabase.filmdb.configuration.JpaConfiguration;
import com.filmdatabase.filmdb.configuration.WebSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmdbApplication.class)
@ContextConfiguration(classes = {FilmdbApplication.class, JpaConfiguration.class, WebSecurityConfig.class})
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSingleUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/", 1))
                .andExpect(status().isOk());
    }

}
