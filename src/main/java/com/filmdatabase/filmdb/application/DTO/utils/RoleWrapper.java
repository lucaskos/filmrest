package com.filmdatabase.filmdb.application.DTO.utils;

import com.filmdatabase.filmdb.application.DTO.RoleDto;
import com.filmdatabase.filmdb.application.model.FilmRelations;

public class RoleWrapper {

    protected static RoleDto getRoleFromFilmRelation(FilmRelations relation) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(relation.getFilmRelationId());
//        roleDto.setRoleName(relation.getRole());
        roleDto.setRoleType(relation.getPersonRoleDictionary().getType());
        roleDto.setRoleKey(relation.getPersonRoleDictionary().getKey());
        return  roleDto;
    }

}
