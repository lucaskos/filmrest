package com.filmdatabase.filmdb.application.model.film;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import com.filmdatabase.filmdb.application.model.test.RatingTest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Luke on 02.11.2017.
 */
@Transactional
@Component("FilmDao")
public class FilmDaoImpl extends GenericDaoHibernateImpl<Film> implements FilmDao {

    public FilmDaoImpl(){
        super(Film.class);
    }


    @Override
    public Film getFilmDetails(int id) {

        Query query = entityManager.createQuery("from Film f LEFT JOIN FETCH f.filmRelations WHERE f.id=:id");
        query.setParameter("id", id);

        Film singleResult = (Film) query.getSingleResult();

        if (singleResult != null) {
            return singleResult;
        } else {
            return null;
        }
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

    public Set<RatingTest> getRatingTestList(Film film) {
        Query query = entityManager.createQuery("from " + RatingTest.class + "ft WHERE ft.filmId=" + film.getFilmId());
        return (Set<RatingTest>) query.getResultList();
    }

}
