package com.filmdatabase.filmdb.application.model.cast;

import com.filmdatabase.filmdb.application.model.GenericDaoHibernateImpl;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class CastDaoImpl extends GenericDaoHibernateImpl<Cast> implements CastDao {

    public CastDaoImpl() {
        super(Cast.class);
    }
}
