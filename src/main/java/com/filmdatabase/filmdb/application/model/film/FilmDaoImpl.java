package com.filmdatabase.filmdb.application.model.film;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke on 02.11.2017.
 */
@Transactional
@Component("FilmDao")
public class FilmDaoImpl extends GenericDaoHibernateImpl<Film> implements FilmDao {

    public FilmDaoImpl(){
        super(Film.class);
    }


    public List getByTitle(String title) {
//        CriteriaBuilder cb = getSession().getCriteriaBuilder();
//        CriteriaQuery<Film> q = cb.createQuery(Film.class);
//        Root<Film> c = q.from(Film.class);
//        CriteriaQuery<Film> film = q.where(cb.equal(c.get("title"), title));
//
//        Film singleResult = getSession().createQuery(film).getSingleResult();
//
//        List list = getSession().createQuery(film).getResultList();
        List list = new ArrayList();
        return list;
    }

    @Override
    public List getByYear(int year) {
//        CriteriaBuilder cb = getSession().getCriteriaBuilder();
//        CriteriaQuery<Film> q = cb.createQuery(Film.class);
//        Root<Film> c = q.from(Film.class);
//        CriteriaQuery<Film> film = q.where(cb.equal(c.get("year"), year));
//
//        List list = getSession().createQuery(film).getResultList();


        List list = new ArrayList();
        return list;

    }

}
