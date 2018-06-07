package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.api.service.interfaces.UserService;
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
import java.util.Set;

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

    private PersonDTO person = null;
    private UserDto user = null;

    @Before
    public void setUp() {
        person = getPerson();
        user = getUser();
    }

    @Test
    public void getSingleComment() {
        PersonComments personCommentsSet = person.getPersonCommentsSet().iterator().next();
        assertNotNull(personCommentsSet.getUser().getId());
        assertNotNull(personCommentsSet.getPerson());
    }

    @Test
    public void addSingleCommentWithRemovalOfOld() {

        PersonDTO update = removeComments();

        PersonComments personComments = firstComment();
        person.addPersonComment(personComments);

        update = personService.addComment(person);
        assertNotNull(update.getPersonCommentsSet());
        Iterator<PersonComments> iterator = update.getPersonCommentsSet().iterator();
        PersonComments next = iterator.next();
        int commentId = next.getCommentId();
        assertEquals(next.getText(), personComments.getText());
        assertEquals(next.getTitle(), personComments.getTitle());
        assertEquals(next.getUser().getId(), personComments.getUser().getId());
    }

    private PersonDTO removeComments() {
        person.setPersonCommentsSet(null);
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

        update.addPersonComment(firstPersonComments);
        update.addPersonComment(secondPersonComments);

        PersonDTO update1 = personService.addComment(update);

        assertNotNull(update1.getPersonCommentsSet());
        assertTrue(update1.getPersonCommentsSet().size() > 1);


    }

    public PersonDTO getPerson() {
        Integer id = personService.findAll().get(0).getId();
        return personService.getPerson(id);
    }

    public UserDto getUser() {
        return userService.getAllUsers().get(0);
    }
}
