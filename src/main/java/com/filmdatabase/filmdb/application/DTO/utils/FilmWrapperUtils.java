package com.filmdatabase.filmdb.application.DTO.utils;

import com.filmdatabase.filmdb.application.DTO.FilmDTO;
import com.filmdatabase.filmdb.application.DTO.PersonDTO;
import com.filmdatabase.filmdb.application.DTO.RoleDto;
import com.filmdatabase.filmdb.application.commons.PersonRolesKeys;
import com.filmdatabase.filmdb.application.model.FilmRelations;
import com.filmdatabase.filmdb.application.model.film.Film;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.LazyInitializationException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class FilmWrapperUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmWrapperUtils.class);
    private static Converter<Film, FilmDTO> filmConverter = null;

    private static ModelMapper modelMapper = new ModelMapper();

    public FilmWrapperUtils() {
        initFilmConventer();
    }

    private static void initFilmConventer() {
        if (filmConverter == null) {
            filmConverter = new Converter<Film, FilmDTO>() {
                @Override
                public FilmDTO convert(MappingContext<Film, FilmDTO> mappingContext) {

                    Film src = mappingContext.getSource();
                    FilmDTO dsc = mappingContext.getDestination();
                    dsc.setId(src.getFilmId());
                    dsc.setTitle(src.getTitle());
                    dsc.setDescription(src.getDescription());
                    LOGGER.info("Converting the film : " + src.toString());
                    dsc.setPeopleList(getPeopleList(src));
                    return dsc;
                }
            };
        }
    }

    private static List<PersonDTO> getPeopleList(Film src) {
        List<PersonDTO> list = null;
        try {
            if (src.getFilmRelations() != null || !src.getFilmRelations().isEmpty()) {
                list = new ArrayList<>();
                List<PersonDTO> finalList = list;
                src.getFilmRelations().forEach(filmRelation -> finalList.add(modelMapper.map(filmRelation.getPerson(), PersonDTO.class)));
            }
        } catch (LazyInitializationException e) {
            LOGGER.error("Couldn't retrieve list for : " + src.toString());
        }
        return list;
    }

    public static FilmDTO getFullFilmDetails(ModelMapper modelMapper, Film one) {
        FilmDTO map = modelMapper.map(one, FilmDTO.class);
        modelMapper.addConverter(getFilmConverter());
        List<PersonDTO> personDTOList = new ArrayList<>();
        try {
            for (FilmRelations relation : one.getFilmRelations()) {
                if (relation.getFilm().getFilmId() == one.getFilmId()) {

                    relation.getPerson().setFilmRelations(null);
                    //fixme comments
                    PersonDTO newPersonDto = modelMapper.map(relation.getPerson(), PersonDTO.class);
//fixme
                    RoleDto roleDto = new RoleDto();
                    roleDto.setId(relation.getFilmRelationId());
                    roleDto.setRoleKey(relation.getPersonRoleDictionary().getKey());
                    roleDto.setRoleType(relation.getPersonRoleDictionary().getType());
//                    roleDto.setRoleName(newPersonDto.getRole());
//                    if (roleDto.getRoleKey().equals(PersonRolesKeys.ACTOR.name())) {
//                        roleDto.setRoleName(relation.getRole());
//                    }
                    map.getPeopleRoleMap().put(roleDto, newPersonDto);

                    //test2 object
                    newPersonDto.setRoleDto(roleDto);

                    newPersonDto.setRoleType(relation.getPersonRoleDictionary().getType());
                    personDTOList.add(newPersonDto);
                }
            }
        } catch (LazyInitializationException e) {
            LOGGER.error("Couldn't fetch relations for " + one.toString());
        }
        map.setPeopleList(personDTOList);
        return map;
    }

    public Film createFilmEntity(ModelMapper modelMapper, FilmDTO film) {
        Film map = modelMapper.map(film, Film.class);
        if(!CollectionUtils.isEmpty(film.getPeopleList())) {
            for(PersonDTO personDTO : film.getPeopleList()) {
                FilmRelations filmRelations = new FilmRelations();
                filmRelations.setPerson(personDTO.getPerson());
                filmRelations.setFilm(film.getFilm());
                if (personDTO.getRoleDto() == null) {
                    continue;
                }
                filmRelations.setPersonRoleDictionary(personDTO.getRoleDto().getPersonRole());
                filmRelations.setRole(personDTO.getRole());
                map.getFilmRelations().add(filmRelations);
            }
        }
        return map;
    }

    private static Converter<Film, FilmDTO> getFilmConverter() {
        if (filmConverter == null) {
            initFilmConventer();
        }
        return filmConverter;
    }


}
