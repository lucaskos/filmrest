package com.filmdatabase.filmdb.application.model.person;

import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.model.FilmRelations;
import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Luke on 03.11.2017.
 */
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
        Query query = entityManager.createQuery("from Person p LEFT JOIN FETCH p.filmRelations LEFT JOIN FETCH p.personComments " +
                "WHERE p.id=:id");
        query.setParameter("id", id);

        Person singleResult = (Person) query.getSingleResult();

        if (singleResult != null) {
            return singleResult;
        } else {
            return null;
        }
    }

//    public Person findOneWithRelations(Serializable id, Object ... args) {
//                StringBuilder builder = new StringBuilder();
//        builder.append("from Person p");
//        if (args.length > 0) {
//            for(int i = 0; i < args.length; i++) {
//                String simpleName = ((Class) args[i]).getSimpleName();
//                String s = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
//                builder.append(" LEFT JOIN FETCH p." + s);
//                simpleName = null;
//                s = null;
//            }
//        }
//        builder.append(" WHERE p.id=:id");
//        Query query = entityManager.createQuery(builder.toString());
//        query.setParameter("id", id);
//        return (Person) query.getSingleResult();
//    }
}
