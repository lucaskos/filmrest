package com.filmdatabase.filmdb.application.model;

import com.filmdatabase.filmdb.application.model.person.Person;
import com.filmdatabase.filmdb.application.model.film.Film;

import javax.persistence.*;

/**
 * Created by Luke on 29.10.2017.
 */
@Entity
@Table(name = "FILM_RELATION")
public class FilmRelation {

    private int filmRelationId;
    private Film film;
    private Person person;

    @Id
    @Column(name = "ID_FILM_RELATION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getFilmRelationId() {
        return filmRelationId;
    }

    public void setFilmRelationId(int filmRelationId) {
        this.filmRelationId = filmRelationId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FILM_ID")
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON_RELATION_ID")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
