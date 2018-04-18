package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.api.service.PersonService;
import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.DTO.RoleDto;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class PersonServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilmServiceTest.class);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper modelMapper;

    private PersonDTO complexPerson = createComplexPersonWithRelation();

    private Person person;

    private List<Person> peopleList = null;

    private Person personToInsert = getSimplePerson();

    @Autowired
    private CacheService cacheService;

    @Autowired
    private FilmService filmService;

    @Test
    public void getAll() {
        peopleList = personService.findAll();
        if (peopleList != null && !peopleList.isEmpty()) {
            person = peopleList.get(0);
        }
        assertNotNull(peopleList);
    }

    @Test
    public void getSingle() {
        if (person != null && person.getId() != null) {
            PersonDTO person = personService.getPerson(this.person.getId());
            assertNotNull(person);
            if (person.getFilmList() != null && !person.getFilmList().isEmpty()) {
            }
        } else {
            List<Person> all = personService.findAll();
            if (all != null && !all.isEmpty()) {
                Person person = all.get(0);
                PersonDTO personDetails = personService.getPerson(person.getId());
                assertNotNull(personDetails);
            }
        }
    }

    @Test
    public void createSingleSimplePerson() {
        if (person == null) {
            person = getSimplePerson();
            PersonDTO map = modelMapper.map(person, PersonDTO.class);
            PersonDTO personDTO = personService.create(map);
            assertNotNull(personDTO);
            assertEquals(personDTO.getFirstName(), person.getFirstName());
        }
    }



    @Test
    public void removeSingle() {
        if (peopleList != null) {
            Person person = peopleList.get(peopleList.size() - 1);
            personService.delete(person);
            assertNull(personService.getPerson(person.getId()));
        }
    }

    @Test
    public void updatePersonData() {
        String newFirstName = "test1";
        if (peopleList != null) {
            Person person = peopleList.get(peopleList.size() - 1);
            assertNotNull(person);
            PersonDTO mapPerson = modelMapper.map(person, PersonDTO.class);
            mapPerson.setFirstName(newFirstName);
            personService.update(mapPerson);
            PersonDTO person1 = personService.getPerson(person.getId());
            assertEquals(newFirstName, person1.getFirstName());
        }
    }

    @Test
    public void testPersonWithRelations() {
        assertNotNull(complexPerson);
        assertNotNull(complexPerson.getFirstName());
        assertNotNull(complexPerson.getBiography());
        assertNotNull(complexPerson.getLastName());
        addRelation(complexPerson);
        PersonDTO personDTO = personService.create(complexPerson);
        assertNotNull(personDTO.getId());
        assertTrue(personDTO.getFilmList().size() > 0);

    }

    private void addRelation(PersonDTO complexPerson) {
        List<Film> allFilms = (List<Film>) filmService.getAllFilms();
        Film film = allFilms.get(allFilms.size() - 1);
        Film anotherFilm = allFilms.get(allFilms.size() - 2);
        FilmDTO firstFilm = filmService.getFilmById(film.getFilmId());
        FilmDTO secondFilm = filmService.getFilmById(anotherFilm.getFilmId());
        addRole(firstFilm, complexPerson);
        addRole(secondFilm, complexPerson);
        complexPerson.getFilmList().add(firstFilm);
        complexPerson.getFilmList().add(secondFilm);
    }

    private void addRole(FilmDTO filmById, PersonDTO complexPerson) {
        Map<RoleDto, PersonDTO> peopleRoleMap = filmById.getPeopleRoleMap();
        Map<RoleDto, PersonDTO> personRole = new HashMap<>();
        List roles = cacheService.getRoles();
//        for (PersonRole p : roles) {
//            if (p.getKey().equals(CacheConstants.ACTOR_ROLE_KEY)) {
//                RoleDto map = modelMapper.map(p, RoleDto.class);
//                personRole.put(map, complexPerson);
//            }
//        }
        filmById.getPeopleRoleMap().putAll(personRole);
    }

    @Test
    public void updateSimpleFilmWithoutRole() {
        if (person == null) {
            person = getSimplePerson();
            PersonDTO map = modelMapper.map(person, PersonDTO.class);
            PersonDTO personDTO = personService.create(map);
            List<Film> allFilms = (List<Film>) filmService.getAllFilms();
            List<FilmDTO> newFilmList = new ArrayList<>();
            newFilmList.add(modelMapper.map(allFilms.get(0), FilmDTO.class));

            assertTrue(!CollectionUtils.isEmpty(newFilmList));

            personDTO.setFilmList(newFilmList);

            personService.update(personDTO);

        }
    }


    public Person getSimplePerson() {
        return new Person("testPerson");
    }

    public PersonDTO createComplexPersonWithRelation() {
        PersonDTO complexPerson = new PersonDTO();
        complexPerson.setFirstName("1test");
        complexPerson.setLastName("test");
        complexPerson.setBiography("test test test");
        return complexPerson;
    }

}
