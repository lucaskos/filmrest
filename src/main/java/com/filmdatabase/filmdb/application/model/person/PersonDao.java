package com.filmdatabase.filmdb.application.model.person;

import com.filmdatabase.filmdb.application.model.GenericDao;

import java.util.List;

/**
 * Created by Luke on 03.11.2017.
 */
public interface PersonDao extends GenericDao<Person> {

    List getPeopleByFirstName(String firstName);

    List getPeopleByLastName(String lastName);

    //todo getActors / getActor
    //so far the class will get all the people entities

}
