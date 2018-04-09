package com.filmdatabase.filmdb.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmdatabase.filmdb.application.model.cache.dictionaries.PersonRole;
import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.film.Film;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Luke on 29.10.2017.
 */
@Entity
@Table(name = "FILM_RELATION")
public class FilmRelation implements Serializable {

    private static final long serialVersionUID = -3368604415829986784L;
    private int filmRelationId;
    private String role;
    private Film film;
    private Person person;

    public FilmRelation() {

    }

    @Id
    @Column(name = "ID_FILM_RELATION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getFilmRelationId() {
        return filmRelationId;
    }

    public void setFilmRelationId(int filmRelationId) {
        this.filmRelationId = filmRelationId;
    }

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private PersonRole personRoleDictionary;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    public PersonRole getPersonRoleDictionary() {
        return personRoleDictionary;
    }

    public void setPersonRoleDictionary(PersonRole personRoleDictionary) {
        this.personRoleDictionary = personRoleDictionary;
    }
}
