package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.model.film.Film;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class FilmServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmServiceTest.class);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private FilmService filmService;

    private List<Film> lista = new ArrayList<>();

    private Film film;

    private Film insertedFilm;

    private final String filmDescription = "testDescription";
    private final String filmTitle = "testTitle";
    private final int filmYear = 1990;

    @Before
    public final void before() {
        film = getFilmToInsert();
    }

    @Test
    public void testNewFilm() {
        assertNotNull(film);
    }

    @Test
    public void getAllFilms() {
        lista = (List<Film>) filmService.getAllFilms();
        assertNotNull(lista);
    }

    @Test
    public void getSingleFilm() {

        lista = (List<Film>) filmService.getAllFilms();
        assertNotNull(lista);

        Film film;
        if (!lista.isEmpty()) {
            film = lista.get(0);
            assertNotNull(film);

            Film filmById = filmService.getFilmById(film.getFilmId());
            assertNotNull(filmById);

            assertEquals(film, filmById);

        }
    }

    @Test
    @WithMockUser(username = "luke", roles = {"ADMIN"})
    public void saveFilm() {
        if (film != null) {
            insertedFilm = filmService.addFilm(this.film);
            assertNotNull(insertedFilm);
            assertEquals(insertedFilm, this.film);
        }
    }

    @Test
    @WithMockUser(username = "luke", roles = {"ADMIN"})
    public void deleteFilm() {
        List<Film> allFilms = (List<Film>) filmService.getAllFilms();
        Film filmToDelete = allFilms.get(allFilms.size() - 1);
        assertNotNull(filmToDelete);

        filmService.deleteFilm(filmToDelete);

        /** after deletion*/
        assertNull(filmService.getFilmById(filmToDelete.getFilmId()));
    }

    @Test
    public void filmDetails() {
        List<Film> allFilms = (List<Film>) filmService.getAllFilms();
        Film film = allFilms.get(0);
        assertNotNull(film);

        FilmDTO filmDetails = filmService.getFilmDetails(film.getFilmId());
        assertNotNull(filmDetails);
    }


    public Film getFilmToInsert() {
        Film filmToInsert = new Film(filmTitle, filmYear, filmDescription);
        assertNotNull(filmToInsert);
        return filmToInsert;
    }

}
