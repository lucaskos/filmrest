package com.filmdatabase.filmdb.application.model.film;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Component;

/**
 * Created by Luke on 15.11.2017.
 */
@Component("filmItemDao")
public class FilmItemDaoImpl extends GenericDaoHibernateImpl<FilmItem> {

    public FilmItemDaoImpl() {
        super(FilmItem.class);
    }
}
