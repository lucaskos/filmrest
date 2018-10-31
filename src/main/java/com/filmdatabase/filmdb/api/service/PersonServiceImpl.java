package com.filmdatabase.filmdb.api.service;

import com.filmdatabase.filmdb.api.service.interfaces.PersonService;
import com.filmdatabase.filmdb.application.DTO.*;
import com.filmdatabase.filmdb.application.DTO.utils.CacheUtil;
import com.filmdatabase.filmdb.application.DTO.utils.PersonMapper;
import com.filmdatabase.filmdb.application.DTO.utils.PersonWrapperUtils;
import com.filmdatabase.filmdb.application.commons.PersonRolesKeys;
import com.filmdatabase.filmdb.application.model.FilmRelations;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.person.PersonDao;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    private final PersonDao personDao;


    private final PersonWrapperUtils personWrapperUtils;

    private CacheService cache;

    private ModelMapper modelMapper;

    private Date now = new Date();

    @Autowired
    public PersonServiceImpl(CacheService cache, PersonDao personDao, ModelMapper modelMapper) {
        this.cache = cache;
        this.personDao = personDao;
        this.personWrapperUtils = new PersonWrapperUtils();
        this.modelMapper = modelMapper;
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Transactional
    public PersonDTO getPerson(int id) {
        Person person = personDao.findOne(id);
        PersonDTO person1 = PersonMapper.toPersonDto(person);
        //PersonDTO personDTO1 = personWrapperUtils.getFullDetailsPersonObject(person);
        return person1;
    }

    public PersonDTO getPersonDetails(int id) {
        PersonDTO personDTO1 = personWrapperUtils.getFullDetailsPersonObject(personDao.findOneWithRelations(id, PersonComments.class, FilmRelations.class));
        return personDTO1;
    }


    public PersonDTO create(PersonDTO personWrapper) {
        personWrapper.setCreationDate(now);
        personWrapper.setModificationDate(now);
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
            FilmRelations filmRelations = new FilmRelations();
            filmRelations.setFilmRelationId(null);
            filmRelations.setPerson(personWrapper.getPerson());
            filmRelations.setRole(personWrapper.getRoleType());
            filmRelations.setFilm(filmDTO.getFilm());
            if(personWrapper.getRoleDto() == null) {
                filmRelations.setPersonRoleDictionary((PersonRole) CacheUtil.findCacheByKey(PersonRolesKeys.ACTOR.name(), cache.getRoles()));
            } else {
                filmRelations.setPersonRoleDictionary(modelMapper.map(personWrapper.getRoleDto(), PersonRole.class));
            }
            person.getFilmRelations().add(filmRelations);
        }
    }


    private void setFilmRelationRole(FilmRelations filmRelations, Set<RoleDto> roleDtos) {
        if (CollectionUtils.isEmpty(roleDtos)) {
            List roles = cache.getRoles();
        } else {


        }
        Iterator iterator = roleDtos.iterator();
        RoleDto next = (RoleDto) iterator.next();
        filmRelations.setPersonRoleDictionary(new PersonRole(next.getId(), next.getRoleType(), next.getRoleKey()));
    }


    public PersonDTO update(PersonDTO person) {
//fixme relacje i komentarze

        return modelMapper.map(personDao.update(person.getPerson()), PersonDTO.class);
    }

    @Override
    public PersonDTO updateRelations(PersonDTO person) {
        person.setModificationDate(now);
        Person personEntity = person.getPerson();
        if (!CollectionUtils.isEmpty(person.getFilmList())) {
            addFilmRelationsToPerson(person, personEntity);
        }
        return modelMapper.map(personDao.update(personEntity), PersonDTO.class);
    }

    public void delete(Person person) {
        personDao.delete(person);
    }

    @Override
    public PersonDTO addComment(PersonDTO person) {
        Person personEntity = person.getPerson();
        if (!CollectionUtils.isEmpty(person.getPersonComments())) {
            addComment(person, personEntity);
        }
        Person update = personDao.update(person.getPerson());
        return modelMapper.map(update, PersonDTO.class);
    }

    private void addComment(PersonDTO personWrapper, Person person) {
        Set<PersonCommentDto> personCommentsSet = personWrapper.getPersonComments();
        for (PersonCommentDto comment : personCommentsSet) {
            //person.addPersonComments(comment);
        }
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
