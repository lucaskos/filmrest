package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.interfaces.FilmService;
import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.api.service.interfaces.UserService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.DTO.UserDto;
import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class CommentsTest {

    @Autowired
    UserService userService;

    @Autowired
    PersonService personService;

    @Autowired
    FilmService filmService;

    private PersonDTO person = null;
    private UserDto user = null;
    private FilmDTO film = null;

    @Before
    public void setUp() {
        person = getPerson();
        user = getUser();
        film = getFilm();
    }

    @Test
    public void getSingleComment() {
        PersonComments personCommentsSet = person.getPersonComments().iterator().next();
        assertNotNull(personCommentsSet.getUser().getId());
        assertNotNull(personCommentsSet.getPerson());
    }

    @Test
    public void addSingleCommentToPersonWithRemovalOfOld() {

        PersonDTO update = removeComments();

        PersonComments personComments = firstComment();
        person.addPersonComments(personComments);

        update = personService.addComment(person);
        assertNotNull(update.getPersonComments());
        Iterator<PersonComments> iterator = update.getPersonComments().iterator();
        PersonComments next = iterator.next();
        int commentId = next.getCommentId();
        assertEquals(next.getText(), personComments.getText());
        assertEquals(next.getTitle(), personComments.getTitle());
        assertEquals(next.getUser().getId(), personComments.getUser().getId());
    }

    @Test
    public void addSingleCommentToFilmWithRemovalOfOld() {
        assertNotNull(film);
    }

    private PersonDTO removeComments() {
        person.setPersonComments(null);
        return personService.update(person);
    }

    private PersonComments firstComment() {
        PersonComments personComments = new PersonComments();
        personComments.setCreatedDate(DateUtil.now());
        personComments.setText("long texxt to support validation");
        personComments.setTitle("single title");
        personComments.setUser(user.getUser());
        return personComments;
    }

    private PersonComments secondComment() {
        PersonComments personComments = new PersonComments();
        personComments.setCreatedDate(DateUtil.now());
        personComments.setText("text1");
        personComments.setTitle("test1");
        personComments.setUser(user.getUser());
        return personComments;
    }

    @Test
    public void addMultipleComments() {

        PersonDTO update = removeComments();
        PersonComments firstPersonComments = firstComment();
        PersonComments secondPersonComments = secondComment();

        update.addPersonComments(firstPersonComments);
        update.addPersonComments(secondPersonComments);

        PersonDTO update1 = personService.addComment(update);

        assertNotNull(update1.getPersonComments());
        assertTrue(update1.getPersonComments().size() > 1);


    }

    public PersonDTO getPerson() {
        Integer id = personService.findAll().get(0).getId();
        return personService.getPerson(id);
    }

    public FilmDTO getFilm() {
        Integer id = filmService.getAllFilms().iterator().next().getFilmId();
        return filmService.getFilmDetails(id);
    }

    public UserDto getUser() {
        return userService.getAllUsers().get(0);
    }
}
