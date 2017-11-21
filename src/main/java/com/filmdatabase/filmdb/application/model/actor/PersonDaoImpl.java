package com.filmdatabase.filmdb.application.model.actor;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Luke on 03.11.2017.
 */
@Component("ADaoI")
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
