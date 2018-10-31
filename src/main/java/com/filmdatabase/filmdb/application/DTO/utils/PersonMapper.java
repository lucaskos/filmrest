package com.filmdatabase.filmdb.application.DTO.utils;

import com.filmdatabase.filmdb.application.DTO.*;
import com.filmdatabase.filmdb.application.model.FilmRelations;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public PersonMapper() {

            modelMapper.createTypeMap(Person.class, PersonDTO.class)
                    .addMapping(Person::getBio, PersonDTO::setBiography)
            .addMapping(Person::getFirstName, PersonDTO::setFirstName)
            .addMapping(Person::getLastName, PersonDTO::setLastName);
            //filmlist section
            TypeMap<FilmRelations, PersonRelationDto> filmMapper = modelMapper.createTypeMap(FilmRelations.class, PersonRelationDto.class)
                    .addMapping(FilmRelations::getFilm, PersonRelationDto::setFilmDTO)
                    .addMapping(FilmRelations::getPersonRoleDictionary, PersonRelationDto::setRoleDto)
                    .addMapping(FilmRelations::getRole, PersonRelationDto::setRole);

            TypeMap<User, UserDto> typeMap = modelMapper.createTypeMap(User.class, UserDto.class)
                    .addMapping(User::getId, UserDto::setId)
                    .addMapping(User::getUsername, UserDto::setUsername)
                    .addMapping(User::getEmail, UserDto::setEmail);
            typeMap.addMappings(modelMapper -> modelMapper.skip(UserDto::setRoles));
            typeMap.addMappings(modelMapper -> modelMapper.skip(UserDto::setPassword));
            typeMap.addMappings(modelMapper -> modelMapper.skip(UserDto::setPersonCommentsSet));
    }

    public static List getFilmMappings(Person person) {
        List<PersonRelationDto> personRelationDtos = new ArrayList<>();

        if(!CollectionUtils.isEmpty(person.getFilmRelations())) {
            person.getFilmRelations().stream().forEach(filmRelations -> personRelationDtos.add(modelMapper.map(filmRelations, PersonRelationDto.class)));
        }

        return personRelationDtos;
    }

    public static PersonDTO toPersonDto(Person person) {
        PersonDTO map = modelMapper.map(person, PersonDTO.class);
        map.setFilmsList(getFilmMappings(person));
        return map;
    }

    public Person toPerson(PersonDTO personDto) {
        return modelMapper.map(personDto, Person.class);
    }

}
