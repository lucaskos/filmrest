package com.filmdatabase.filmdb.application.model.person;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Luke on 03.11.2017.
 */
@Transactional
@Repository("personDao")
public class PersonDaoImpl extends GenericDaoHibernateImpl<Person> implements PersonDao {

    public PersonDaoImpl() {
        super(Person.class);
    }

    @Override
    public List getPeopleByFirstName(String firstName) {
        return null;
    }

    @Override
    public List getPeopleByLastName(String lastName) {
        return null;
    }
}
