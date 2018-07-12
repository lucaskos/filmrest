package com.filmdatabase.filmdb.application.DTO;

import com.filmdatabase.filmdb.application.model.comments.PersonComments;
import com.filmdatabase.filmdb.application.model.user.dao.User;
import com.filmdatabase.filmdb.application.model.user.role.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDto {

    public String username;
    public Integer id;
    public String password;
    public boolean enabled;
    public String email;
    public List<Role> roles;
    private User user;
    private Set<PersonComments> personCommentsSet = new HashSet<>();

    public UserDto() {}

    public UserDto(String username, Integer id, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User getUser() {
        user = new User(getUsername(), getId(), getPassword(), isEnabled(), getEmail(), getRoles());
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<PersonComments> getPersonCommentsSet() {
        return personCommentsSet;
    }

    public void setPersonCommentsSet(Set<PersonComments> personCommentsSet) {
        this.personCommentsSet = personCommentsSet;
    }

    public void addPersonComment(PersonComments personComments) {
        this.personCommentsSet.add(personComments);
    }
}
