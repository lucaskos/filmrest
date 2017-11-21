package com.filmdatabase.filmdb.application.model.cache.dao;

import com.filmdatabase.filmdb.application.commons.QualifierConstants;
import com.filmdatabase.filmdb.application.model.cache.CacheConstants;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Luke on 01.11.2017.
 */
@Component(QualifierConstants.PERSON_DIC_DAO)
public class PersonRoleDaoImpl extends DictionaryAbstractClass<PersonRole> {

    public PersonRoleDaoImpl() {
        super(PersonRole.class);
    }

    @Override
    @Cacheable(CacheConstants.CACHE_DICTIONARY)
    public List<PersonRole> getAll() {
        return super.getAll();
    }
}
