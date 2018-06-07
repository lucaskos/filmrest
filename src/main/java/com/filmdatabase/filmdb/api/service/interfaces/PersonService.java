package com.filmdatabase.filmdb.api.service.interfaces;

import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.person.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    PersonDTO getPerson(int id);

    PersonDTO getPersonDetails(int id);

    PersonDTO create(PersonDTO person);

    PersonDTO update(PersonDTO person);

    void delete(Person person);

    PersonDTO addComment(PersonDTO person);

    PersonDTO updateRelations(PersonDTO personDTO);
}
