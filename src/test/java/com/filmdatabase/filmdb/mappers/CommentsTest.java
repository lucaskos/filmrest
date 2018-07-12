package com.filmdatabase.filmdb.mappers;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.DTO.UserDto;
import com.filmdatabase.filmdb.application.DTO.utils.FilmRelationMapping;
import com.filmdatabase.filmdb.application.model.FilmRelations;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class CommentsTest {

    private static final String PERSON_FIRST_NAME = "Test";
    private static final String PERSON_LAST_NAME = "Test2";
    private static final Integer PERSON_ID = 1;
    private static final LocalDate PERSON_BORN_DATE = LocalDate.of(1988,10,10);
    private static final LocalDate PERSON_DIED_DATE = LocalDate.of(2033, 3, 21);
    private static final LocalDate PERSON_CREATION_DATE = LocalDate.now();
    private static final LocalDate PERSON_MODIFICATION_DATE = LocalDate.now();
    private static final String PERSON_BIO = "Biography";

    private static final Integer FILM_ID_1 = 1;
    private static final String FILM_TITLE_1 = "Test Film";
    private static final int FILM_YEAR_1 = 1999;
    private static final String FILM_DESCRIPTION_1 = "Test film description";

    private static final Integer FILM_ID_2 = 1;
    private static final String FILM_TITLE_2 = "Another film";
    private static final int FILM_YEAR_2 = 2010;
    private static final String FILM_DESCRIPTION_2 = "Another film description";

    private static final int ROLE_ID = 1;
    private static final String ROLE_TYPE = "ACTOR";
    private static final String ROLE_KEY = "ACTOR_KEY";

    private static final String FILM_RELATION_ROLE_FIRST = "test role";
    private static final String FILM_RELATION_ROLE_SECOND = "test role";

    private static final String COMMENT_TITLE_1 = "test comment 1";
    private static final LocalDate COMMENT_DATE_1 = LocalDate.now();
    private static final String COMMENT_TEXT_1 = "Testowy tekst komentarza";

    private static final String USER_NAME = "test_user";
    private static final String USER_PASSWORD = "test";//todo userdto
    private static final Integer USER_ID = 1;


    private Person person = null;
    private UserDto user = null;
    private FilmDTO film = null;
    ModelMapper modelMapper;
    ModelMapper filmMapper;

    @Before
    public void setUp() {
        person = getPerson();
        modelMapper = new ModelMapper();



        filmMapper = new ModelMapper();
    }

    @Test
    public void personToDto() {
        assertTrue(person != null);

        PersonDTO map = modelMapper.map(person, PersonDTO.class);
        PersonDTO person = FilmRelationMapping.getPerson(this.person);
        assertNotNull(map);
    }

    public Person getPerson()  {

        Person p = new Person(PERSON_ID, PERSON_FIRST_NAME,PERSON_LAST_NAME,Date.valueOf(PERSON_BORN_DATE),
                Date.valueOf(PERSON_DIED_DATE),Date.valueOf(PERSON_CREATION_DATE),
                Date.valueOf(PERSON_MODIFICATION_DATE),PERSON_BIO);

        p.setFilmRelations(getFilmRelationis(p));
        p.setPersonComments(getPersonComments(p));
        return p;
    }

    private Set<PersonComments> getPersonComments(Person p) {
        Set<PersonComments> personComments = new LinkedHashSet<>();

        User simpleUser = new User(USER_NAME, USER_ID, USER_PASSWORD);

        PersonComments comment1 = new PersonComments();
        comment1.setPerson(p);
        comment1.setTitle(COMMENT_TITLE_1);
        comment1.setCreatedDate(Date.valueOf(COMMENT_DATE_1));
        comment1.setText(COMMENT_TEXT_1);
        comment1.setCommentId(1);
        comment1.setDepth(0);
        comment1.setParentCommentId(null);
        comment1.setUser(simpleUser);
        personComments.add(comment1);

        return personComments;
    }

    private List<FilmRelations> getFilmRelationis(Person person) {
        List<FilmRelations> filmRelations = new ArrayList<>();

        FilmRelations fR1 = new FilmRelations();
        PersonRole personRole = new PersonRole(ROLE_ID, ROLE_TYPE, ROLE_KEY);
        Film film = new Film(FILM_ID_1, FILM_TITLE_1, FILM_YEAR_1, FILM_DESCRIPTION_1);
        fR1.setPersonRoleDictionary(personRole);
        fR1.setPerson(person);
        fR1.setFilm(film);
        fR1.setFilmRelationId(1);
        fR1.setRole(FILM_RELATION_ROLE_FIRST);
        filmRelations.add(fR1);

        FilmRelations fR2 = new FilmRelations();
        Film film2 = new Film(FILM_ID_2, FILM_TITLE_2, FILM_YEAR_2, FILM_DESCRIPTION_2);
        fR2.setPersonRoleDictionary(personRole);
        fR2.setPerson(person);
        fR2.setFilm(film2);
        fR2.setFilmRelationId(2);
        fR2.setRole(FILM_RELATION_ROLE_SECOND);
        filmRelations.add(fR2);

        return filmRelations;
    }
}
