package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.commons.CacheConstants;
import com.filmdatabase.filmdb.application.commons.PersonRolesKeys;
import com.filmdatabase.filmdb.application.model.FilmRelation;
import com.filmdatabase.filmdb.application.model.film.Film;
import com.filmdatabase.filmdb.application.model.person.Person;
import org.apache.commons.collections4.CollectionUtils;
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

                    if (!src.getFilmRelations().isEmpty()) {
                        List<Person> list = new ArrayList<>();
                        src.getFilmRelations().forEach(filmRelation -> list.add(filmRelation.getPerson()));
                    }
                    return dsc;
                }
            };
        }
    }

    public static FilmDTO getFullFilmDetails(ModelMapper modelMapper, Film one) {
        FilmDTO map = modelMapper.map(one, FilmDTO.class);
        modelMapper.addConverter(getFilmConverter());
        List<PersonDTO> personDTOList = new ArrayList<>();

        for (FilmRelation relation : one.getFilmRelations()) {
            if (relation.getFilm().getFilmId() == one.getFilmId()) {

                relation.getPerson().setFilmRelations(null);
                PersonDTO newPersonDto = modelMapper.map(relation.getPerson(), PersonDTO.class);
//fixme
                //test1 mapa
                RoleDto roleDto = new RoleDto();
                roleDto.setId(relation.getFilmRelationId());
                roleDto.setRoleKey(relation.getPersonRoleDictionary().getKey());
                roleDto.setRoleType(relation.getPersonRoleDictionary().getType());
                if (roleDto.getRoleKey().equals(PersonRolesKeys.ACTOR.name())) {
                    roleDto.setRoleName(relation.getRole());
                }
                map.getPeopleRoleMap().put(roleDto, newPersonDto);

                //test2 object
                newPersonDto.setRoleDto(roleDto);

                newPersonDto.setRoleType(relation.getPersonRoleDictionary().getType());
                personDTOList.add(newPersonDto);
            }
        }
        map.setPeopleList(personDTOList);
        return map;
    }

    public Film createFilmEntity(ModelMapper modelMapper, FilmDTO film) {
        Film map = modelMapper.map(film, Film.class);
        if(!CollectionUtils.isEmpty(film.getPeopleList())) {
            for(PersonDTO personDTO : film.getPeopleList()) {
                FilmRelation filmRelation = new FilmRelation();
                filmRelation.setPerson(personDTO.getPerson());
                filmRelation.setFilm(film.getFilm());
                if (personDTO.getRoleDto() == null) {
                    continue;
                }
                filmRelation.setPersonRoleDictionary(personDTO.getRoleDto().getPersonRole());
                filmRelation.setRole(personDTO.getRole());
                map.getFilmRelations().add(filmRelation);
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
