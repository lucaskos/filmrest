package com.filmdatabase.filmdb.application.model.cache.dao;

import com.filmdatabase.filmdb.application.commons.QualifierConstants;
import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.GenresDictionary;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
@Repository(QualifierConstants.PERSON_DIC_DAO)
public class PersonRoleDaoImpl extends DictionaryAbstractClass<PersonRole> {

    public PersonRoleDaoImpl() {
        super(PersonRole.class);
    }

    @Override
    @Cacheable(CacheConstants.ROLES)
    public List<PersonRole> getAll() {
        return super.getAll();
    }

}
