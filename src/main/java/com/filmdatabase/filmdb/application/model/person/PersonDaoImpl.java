package com.filmdatabase.filmdb.application.model.person;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import com.filmdatabase.filmdb.application.model.film.Film;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.Serializable;
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

    @Override
    public Person findOne(Serializable id) {
        Query query = entityManager.createQuery("from Person p LEFT JOIN FETCH p.filmRelations " +
                "LEFT JOIN FETCH p.actorFilms " +
                "WHERE p.id=:id");
        query.setParameter("id", id);

        Person singleResult = (Person) query.getSingleResult();

        if (singleResult != null) {
            return singleResult;
        } else {
            return null;
        }
    }
}
