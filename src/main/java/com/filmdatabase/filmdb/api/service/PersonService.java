package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.WrapperUtils;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao){
        this.personDao = personDao;
    }

    public List<Person> findAll(){
        return personDao.findAll();
    }

    public PersonDTO getPerson(int id){
        Person person = personDao.findOne(id);
        PersonDTO personDTO1 = WrapperUtils.getFullDetailsPersonObject(person);

        if(person == null) {
            //TODO add logger
            //throw exception
            throw new EntityNotFoundException("Couldn't find Person entity of id: "  + id);
        }
        return personDTO1;
    }


    public void create(PersonDTO personWrapper) {
//        Person person = personWrapper.getPerson();
//        personDao.create(person);
        if (!CollectionUtils.isEmpty(personWrapper.getFilmList())) {

        }
    }


    public void update(Person person) {
        personDao.update(person);
    }

    public void delete(Long id) {
        //todo
    }

}
