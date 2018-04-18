package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.cache.service.CacheService;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class PersonWrapperUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmWrapperUtils.class);
    private static Converter<Person, PersonDTO> personDTOConverter = null;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CacheService cacheService;

    public PersonWrapperUtils() {
        initPersonConverter();
    }

    private static void initPersonConverter() {
        if (personDTOConverter == null) {
            personDTOConverter = mappingContext -> {
                Person src = mappingContext.getSource();
                PersonDTO dsc = mappingContext.getDestination();
                dsc.setId(src.getId());
                dsc.setBornDate(src.getBornDate());
                dsc.setDiedDate(src.getDiedDate());
                dsc.setBiography(src.getBio());
                dsc.setFirstName(src.getFirstName());
                dsc.setLastName(src.getLastName());

                if (!src.getFilmRelations().isEmpty()) {
                    List<Film> list = new ArrayList<>();
                    src.getFilmRelations().forEach(filmRelation -> list.add(filmRelation.getFilm()));
                }

                return dsc;
            };
        }
    }

    public PersonDTO getFullDetailsPersonObject(Person person) {
        modelMapper.addConverter(getPersonConventer());
        if (person != null) {

            List<FilmDTO> filmList = new ArrayList<>();

            PersonDTO map = modelMapper.map(person, PersonDTO.class);
            for (FilmRelation relation : person.getFilmRelations()) {
                if (relation.getPerson().getId() == person.getId()) {
                    //relation.getFilm().setFilmRelations(null);
                    FilmDTO filmDTO = modelMapper.map(relation.getFilm(), FilmDTO.class);
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setRoleDto(RoleWrapper.getRoleFromFilmRelation(relation));
                    filmDTO.getPeopleList().add(personDTO);
                    filmList.add(filmDTO);
                }
            }
            map.setFilmList(filmList);
            return map;
        }
        return null;
    }

    private static Converter<Person, PersonDTO> getPersonConventer() {
        if (personDTOConverter == null) {
            initPersonConverter();
        }
        return personDTOConverter;
    }

}
