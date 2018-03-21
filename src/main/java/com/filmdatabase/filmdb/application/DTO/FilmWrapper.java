package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.PersonRelation;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Set;


public class FilmWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmWrapper.class);

    public FilmDTO populateDetails(Film film) {
        if(film != null) {
            FilmDTO filmDTO = new FilmDTO(film.getFilmId(), film.getYear(), film.getTitle(), film.getDescription());
            if (film.getFilmRelations() != null) {
                for(FilmRelation filmRelation : film.getFilmRelations()) {
                    filmDTO.addPerson(wrapPeopleObject(filmRelation.getPerson()));
                }
            }
                LOGGER.info("Details DTO created from film " + film);
            return filmDTO;
        }
        return null;
    }

    public PersonDTO wrapPeopleObject(Person person) {
        if(person != null) {
            //todo remove test,
            PersonDTO personDTO = new PersonDTO(person.getId(), person.getFirstName(), "test");
            return personDTO;
        }
        return null;
    }
    public PersonDTO wrapFullPersonObject(Person person) {
        if (person != null) {
            PersonDTO personDto = new PersonDTO(person);
            if (!CollectionUtils.isEmpty(person.getPersonRelations())) {
                Set<PersonRelation> personRelations = person.getPersonRelations();
                LOGGER.info(String.valueOf(personRelations.toArray()));
                Set<PersonRelation> personRelations1 = person.getPersonRelations();
                for (PersonRelation r : personRelations) {
                    r.getPersonRole();
                }
            }

            if (!CollectionUtils.isEmpty(person.getFilmRelations())) {
                Set<FilmRelation> filmRelations = person.getFilmRelations();
                LOGGER.info(String.valueOf(filmRelations.toArray()));
                for (FilmRelation f : filmRelations) {
                    personDto.getFilmList().put(1, f.getFilm());
                }
            }
            return personDto;
        }
        return null;
    }
}
