package com.filmdatabase.filmdb;

import com.filmdatabase.filmdb.api.service.interfaces.FilmService;
import com.filmdatabase.filmdb.api.service.PersonServiceImpl;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmdbApplicationTests {

	@Autowired
    PersonServiceImpl personService;

	@Autowired
	FilmService filmService;

	@Autowired
	PersonDao personDao;

	@Test
	public void contextLoads() {

		Person one = personDao.findOne(20);

	}

}
