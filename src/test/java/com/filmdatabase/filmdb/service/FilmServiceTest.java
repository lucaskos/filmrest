package com.filmdatabase.filmdb.service;

import com.filmdatabase.filmdb.api.service.interfaces.FilmService;
import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.application.DTO.*;
import com.filmdatabase.filmdb.application.DTO.utils.CacheUtil;
import com.filmdatabase.filmdb.application.DTO.utils.PersonWrapperUtils;
import com.filmdatabase.filmdb.application.commons.PersonRolesKeys;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.Cache;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
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

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheService cacheService;

    private List<Film> lista = new ArrayList<>();

    private Film film;

    private FilmDTO insertedFilm;

    private final PersonWrapperUtils personWrapperUtils;

    private final String filmDescription = "testDescription";
    private final String filmTitle = "testTitle";
    private final int filmYear = 1990;

    public FilmServiceTest() {
        personWrapperUtils = new PersonWrapperUtils();
    }

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

        Film filmWithoutRelations;
        Film filmWithRelations = null;
        if (!lista.isEmpty()) {
            filmWithoutRelations = lista.get(0);
            assertNotNull(filmWithoutRelations);
            LOGGER.info("Getting film by id : " + filmWithoutRelations.toString());
            FilmDTO filmById = filmService.getFilmById(filmWithoutRelations.getFilmId());
            assertNotNull(filmById);

            LOGGER.info("Comparing : " + filmWithoutRelations.toString() + " TO  : " + filmById.toString());

            assertEquals(filmWithoutRelations.getTitle(), filmById.getTitle());
            assertEquals(filmWithoutRelations.getDescription(), filmById.getDescription());
            assertEquals(filmWithoutRelations.getYear(), filmById.getYear());

            for(Film f : lista){
                if(f.getFilmRelations() != null || !f.getFilmRelations().isEmpty()){
                    filmWithRelations = f;
                    continue;
                }
            }
            LOGGER.info("Getting details for film by id : " + filmWithRelations.toString());
            FilmDTO filmDTOWithRelations = filmService.getFilmDetails(filmWithRelations.getFilmId());
            assertTrue(filmDTOWithRelations.getPeopleList() != null || !filmDTOWithRelations.getPeopleList().isEmpty());

        }
    }

    @Test
    @WithMockUser(username = "luke", roles = {"ADMIN"})
    public void saveFilm() {
        FilmDTO map = modelMapper.map(film, FilmDTO.class);
        if (film != null) {
            insertedFilm = filmService.addFilm(map);
            assertNotNull(insertedFilm);
            assertEquals(insertedFilm.getTitle(), map.getTitle());
            assertEquals(insertedFilm.getDescription(), map.getDescription());
            assertEquals(insertedFilm.getYear(), map.getYear());
        }
    }

    @Test
    @WithMockUser(username = "luke", roles = {"ADMIN"})
    public void saveComplexFilm() {
        FilmDTO map = modelMapper.map(film, FilmDTO.class);
        assertTrue(CollectionUtils.isEmpty(map.getPeopleList()));

        addMockPersonToFilm(map);

        assertTrue(!CollectionUtils.isEmpty(map.getPeopleList()));

        Collection<PersonDTO> values = map.getPeopleRoleMap().values();
        values.stream().forEach(e -> assertNotNull(e.getId()));

        filmService.addFilm(map);
    }

    private void addMockPersonToFilm(FilmDTO map) {
        PersonDTO person1 = new PersonDTO(new Person("mock_test_for_film"));
        PersonDTO person2 = new PersonDTO(new Person("mock_test_2"));

        PersonDTO firstPerson = personService.create(person1);
        Cache actorCache = CacheUtil.findCacheByKey(PersonRolesKeys.ACTOR.name(), cacheService.getRoles());
        firstPerson.setRole("testowa rola");
        firstPerson.setRoleDto(modelMapper.map(actorCache, RoleDto.class));
        PersonDTO secondPerson = personService.create(person2);
        Cache directorCache = CacheUtil.findCacheByKey(PersonRolesKeys.DIRECTOR.name(), cacheService.getRoles());
        secondPerson.setRole("rezyser");
        secondPerson.setRoleDto(modelMapper.map(directorCache, RoleDto.class));

        map.addPerson(firstPerson);
        map.addPerson(secondPerson);

        List<Person> all = personService.findAll();
        Person person = all.get(all.size() - 1);
        PersonDTO person3 = personService.getPerson(person.getId());

        map.addPerson(person3);

    }

    @Test
    @WithMockUser(username = "luke", roles = {"ADMIN"})
    public void deleteFilm() {
        List<Film> allFilms = (List<Film>) filmService.getAllFilms();
        Film filmToDelete = allFilms.get(allFilms.size() - 1);
        assertNotNull(filmToDelete);

        filmService.deleteFilm(filmToDelete);

    }

    @Test
    public void filmDetails() {

        List<Film> allFilms = (List<Film>) filmService.getAllFilms();


        FilmDTO filmDto = filmService.getFilmDetails(film.getFilmId() != null ? film.getFilmId() : allFilms.get(allFilms.size()-1).getFilmId() );
        assertNotNull(filmDto);
        assertEquals(filmDto.getDescription(), film.getDescription());
        assertEquals(filmDto.getTitle(), film.getTitle());

        FilmDTO filmDetails = filmService.getFilmDetails(((List<Film>) filmService.getAllFilms()).get(filmService.getAllFilms().size()-1).getFilmId());
        assertNotNull(filmDetails);
        assertNotNull(filmDetails.getPeopleRoleMap());
    }

    public Film getFilmToInsert() {
        Film filmToInsert = new Film(null, filmTitle, filmYear, filmDescription);
        assertNotNull(filmToInsert);

        return filmToInsert;

    }

}
