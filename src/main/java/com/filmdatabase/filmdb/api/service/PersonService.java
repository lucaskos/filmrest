package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Person getPerson(int id){
        Person one = personDao.findOne(id);
        if(one == null) {
            //TODO add logger
            //throw exception
            throw new EntityNotFoundException("Couldn't find Person entity of id: "  + id);
        }
        return personDao.findOne(id);
    }


    public void create( Person person) {
        personDao.create(person);
    }


    public void update(Person person) {
        personDao.update(person);
    }

    public void delete(Long id) {
        //todo
    }

}
