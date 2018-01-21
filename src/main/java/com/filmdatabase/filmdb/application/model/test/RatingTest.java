package com.filmdatabase.filmdb.application.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmdatabase.filmdb.application.model.film.Film;

import javax.persistence.*;

@Entity(name = "rating")
public class RatingTest {

    private Integer ratingId;
    private Integer rating;
    private Film film;

    public RatingTest() {
    }

    @Id
    @Column(name = "ratinId")
    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filmId")
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
