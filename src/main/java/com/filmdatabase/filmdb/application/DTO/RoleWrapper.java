package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.FilmRelation;

public class RoleWrapper {

    protected static RoleDto getRoleFromFilmRelation(FilmRelation relation) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(relation.getFilmRelationId());
        roleDto.setRoleName(relation.getRole());
        roleDto.setRoleType(relation.getPersonRoleDictionary().getType());
        roleDto.setRoleKey(relation.getPersonRoleDictionary().getKey());
        return  roleDto;
    }

}
