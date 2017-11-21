package com.filmdatabase.filmdb.application.model;

import com.filmdatabase.filmdb.application.model.actor.Person;
import com.filmdatabase.filmdb.application.model.film.Film;

import javax.persistence.*;

/**
 * Created by Luke on 29.10.2017.
 */
@Entity
@Table(name = "FILM_RELATION")
public class FilmRelation {

    @Id
    @Column(name = "ID_FILM_RELATION")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int filmRelationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FILM_ID")
    private Film film;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PERSON_RELATION_ID")
    private Person person;

    public int getFilmRelationId() {
        return filmRelationId;
    }

    public void setFilmRelationId(int filmRelationId) {
        this.filmRelationId = filmRelationId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
