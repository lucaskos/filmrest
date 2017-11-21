package com.filmdatabase.filmdb.application.model.film;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Luke on 15.11.2017.
 */
//@Entity
@Table(name = "FILM")
public class FilmItem {

    private int id;
    private Integer year;
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FILM_ID")
    public int getId() {
        return id;
    }

    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }
}
