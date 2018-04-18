package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.person.Person;

public class RoleDto {
    private Integer id;
    private String roleType;
    private String roleKey;
    private String roleName;

    private PersonRole personRole;

    public PersonRole getPersonRole() {
        return new PersonRole(getId(), getRoleType(), getRoleKey());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDto roleDto = (RoleDto) o;

        if (id != null ? !id.equals(roleDto.id) : roleDto.id != null) return false;
        if (roleType != null ? !roleType.equals(roleDto.roleType) : roleDto.roleType != null) return false;
        return roleKey != null ? roleKey.equals(roleDto.roleKey) : roleDto.roleKey == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        result = 31 * result + (roleKey != null ? roleKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleType='" + roleType + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
