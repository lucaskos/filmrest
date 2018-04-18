package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.person.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    PersonDTO getPerson(int id);

    PersonDTO create(PersonDTO person);

    void update(PersonDTO person);

    void delete(Person person);

}
