package com.filmdatabase.filmdb.application.model.genre;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Luke on 21.10.2017.
 */
public class GenreDaoImpl implements GenreDao{

    private String query;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Genre getGenreById(int id) {
        query = "from genres where id=?l";
        return (Genre) sessionFactory.getCurrentSession().createQuery(query).setParameter("l", id).list().get(0);
    }

    @Override
    public Genre getGenreByName(String name) {
        query = "from genres where id=?l";
        return (Genre) sessionFactory.getCurrentSession().createQuery(query).setParameter("l", name).list().get(0);
    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }
}
