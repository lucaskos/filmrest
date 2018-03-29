package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;


public class WrapperUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(WrapperUtils.class);

    public FilmDTO getFullDetailsFilmObject(Film film) {
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
    public static PersonDTO getFullDetailsPersonObject(Person person) {
        if (person != null) {
            PersonDTO personDto = new PersonDTO(person);

            if (!CollectionUtils.isEmpty(person.getFilmRelations())) {
                List<FilmRelation> filmRelations = person.getFilmRelations();
                LOGGER.info(String.valueOf(filmRelations));
                for (FilmRelation f : filmRelations) {
                    personDto.getFilmList().put(1, f.getFilm());
                }
            }
            return personDto;
        }
        return null;
    }
}
