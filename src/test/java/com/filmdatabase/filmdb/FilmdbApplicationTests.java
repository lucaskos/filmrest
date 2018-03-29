package com.filmdatabase.filmdb;

import com.filmdatabase.filmdb.api.service.FilmService;
import com.filmdatabase.filmdb.api.service.PersonService;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmdbApplicationTests {

	@Autowired
	PersonService personService;

	@Autowired
	FilmService filmService;

	@Autowired
	PersonDao personDao;

	@Test
	public void contextLoads() {

		Person one = personDao.findOne(20);

	}

}
