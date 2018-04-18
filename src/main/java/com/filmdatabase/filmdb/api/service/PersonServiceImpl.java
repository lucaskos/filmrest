package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.application.DTO.*;
import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.application.commons.PersonRolesKeys;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.film.FilmDao;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonDao personDao;

    private final FilmDao filmDao;

    private final PersonWrapperUtils personWrapperUtils;

    private CacheService cache;

    private ModelMapper modelMapper;

    @Autowired
    public PersonServiceImpl(CacheService cache, PersonDao personDao, FilmDao filmDao, ModelMapper modelMapper) {
        this.cache = cache;
        this.filmDao = filmDao;
        this.personDao = personDao;
        this.personWrapperUtils = new PersonWrapperUtils();
        this.modelMapper = modelMapper;
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public PersonDTO getPerson(int id) {
        Person person = personDao.findOne(id);
        PersonDTO personDTO1 = personWrapperUtils.getFullDetailsPersonObject(person);
        return personDTO1;
    }


    public PersonDTO create(PersonDTO personWrapper) {
        Person person = personWrapper.getPerson();
        person.setId(null);
        Person person1 = personDao.create(person);
        if (personWrapper.getFilmList() != null && !personWrapper.getFilmList().isEmpty()) {
            addFilmRelationsToPerson(personWrapper, person1);
            return personWrapperUtils.getFullDetailsPersonObject(personDao.update(person1));
        } else {
            LOGGER.info("Creating simple person object: " + person.toString());
            person.setId(null);
            Person insertedPerson = personDao.create(person);
            if (insertedPerson != null) {
                return personWrapper;
            }
        }
        return null;
    }

    private void addFilmRelationsToPerson(PersonDTO personWrapper, Person person) {
        List<FilmDTO> filmList = personWrapper.getFilmList();
        for (FilmDTO filmDTO : filmList) {
            FilmRelation filmRelation = new FilmRelation();
            filmRelation.setFilmRelationId(null);
            filmRelation.setPerson(personWrapper.getPerson());
            filmRelation.setRole(personWrapper.getRoleType());
            filmRelation.setFilm(filmDTO.getFilm());
            if(personWrapper.getRoleDto() == null) {
                filmRelation.setPersonRoleDictionary((PersonRole) CacheUtil.findCacheByKey(PersonRolesKeys.ACTOR.name(), cache.getRoles()));
            } else {
                filmRelation.setPersonRoleDictionary(modelMapper.map(personWrapper.getRoleDto(), PersonRole.class));
            }
            person.getFilmRelations().add(filmRelation);
        }
    }


    private void setFilmRelationRole(FilmRelation filmRelation, Set<RoleDto> roleDtos) {
        if (CollectionUtils.isEmpty(roleDtos)) {
            List roles = cache.getRoles();
        } else {


        }
        Iterator iterator = roleDtos.iterator();
        RoleDto next = (RoleDto) iterator.next();
        filmRelation.setPersonRoleDictionary(new PersonRole(next.getId(), next.getRoleType(), next.getRoleKey()));
    }


    public void update(PersonDTO person) {
        Person personEntity = person.getPerson();
        if (!CollectionUtils.isEmpty(person.getFilmList())) {
            addFilmRelationsToPerson(person, personEntity);
        }
        personDao.update(personEntity);
    }

    public void delete(Person person) {
        personDao.delete(person);
    }

    private boolean isFilmRelationExistsOnPerson(FilmDTO filmDTO, List<FilmDTO> filmRelations) {
        boolean isPersonFilmRelationExists = false;

        if (filmDTO != null && !CollectionUtils.isEmpty(filmRelations)) {
            for (FilmDTO filmRelation : filmRelations) {
                if (filmDTO.getId() != null && filmDTO.getId().equals(filmRelation.getId())
                        && filmDTO.getPeopleRoleMap().equals(filmRelation.getPeopleRoleMap())) {
                    isPersonFilmRelationExists = true;
                }
            }
        }

        return isPersonFilmRelationExists;
    }

}
