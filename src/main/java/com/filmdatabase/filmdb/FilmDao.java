package com.filmdatabase.filmdb;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class FilmDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> getFilms(){
        String hql = "from Film";
        return (List<Film>) entityManager.createQuery(hql).getResultList();
    }

}
